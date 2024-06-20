package org.example.javachat;

import HTTP.getAllfriend;
import HTTP.p2pSendMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static HTTP.chatClient.dataOutputStream;
import static HTTP.logInServletReq.MAPPER;

public class Chat
{
    private final String username;
    private final String chatName;

    private List<Map<String, Object>> messages;

    // 聊天框
    @FXML
    public VBox chatVBox;
    // 用户名
    @FXML
    public Label userName;
    // 发送按钮
    @FXML
    public Button sendButton;
    // 输入框
    @FXML
    public TextField messageTextField;
    // panel
    @FXML
    public ScrollPane chatScrollPane;

    public Chat(String username , List<Map<String, Object>> messages , String chatName)
    {
        this.username = username;
        this.messages = messages;
        this.chatName = chatName;
    }

    @FXML
    public void initialize()
    {
        userName.setText(username);
        for (Map<String, Object> message : messages)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            HBox messageLabel = new HBox();
            HBox textLabel = new HBox();
            messageLabel.getChildren().add(new Label(message.get("send_user_name") + "  "));
            messageLabel.getChildren().add(new Label(simpleDateFormat.format(message.get("date")) + "  "));
            textLabel.getChildren().add(new Label(message.get("text") + "  "));
            /*
            设置样式
            背景半透明，边框，圆角，字体颜色
            */
            messageLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000;");
            textLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000; -fx-padding: 5px;");
            chatVBox.getChildren().addAll(messageLabel , textLabel);
        }
        // 让VBox在打开时自动滚动到底部
        DoubleBinding height = Bindings.createDoubleBinding(() -> chatVBox.getHeight(), chatVBox.heightProperty());
        chatScrollPane.vvalueProperty().bind(height);
        Platform.runLater(() -> messageTextField.requestFocus());
    }

    @FXML
    public void sendMessage()
    {
        String message = messageTextField.getText();
        // 如果聊天框为空，则不发送消息 , 并提醒用户
        if (message.isEmpty())
        {
            Platform.runLater(() -> {
                messageTextField.clear();
                messageTextField.setPromptText("消息不能为空");
            });
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HBox messageLabel = new HBox();
        HBox textLabel = new HBox();
        messageLabel.getChildren().add(new Label(this.chatName + "  "));
        messageLabel.getChildren().add(new Label(simpleDateFormat.format(new Date()) + "  "));
        textLabel.getChildren().add(new Label(message + "  "));
            /*
            设置好看的样式
            背景半透明，边框，圆角，字体颜色
            */
        messageLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000;");
        textLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000; -fx-padding: 5px;");
        chatVBox.getChildren().addAll(messageLabel , textLabel);

        // 发送消息到服务器
        // 异步获取响应结果
        try
        {
            p2pSendMessages call = new p2pSendMessages(message.toString() , this.username);
            FutureTask<Boolean> futureTask = new FutureTask<>(call);
            new Thread(futureTask).start();
            Boolean result = futureTask.get();
            Map<String, String> response = new HashMap<>();
            response.put("username" , this.username);
            response.put("message" , message.toString());
            String json = MAPPER.writeValueAsString(response);
            dataOutputStream.writeUTF(json);
            if (!result)
            {
                messageLabel.getChildren().add(new Label("发送失败"));
            }
        }
        catch (InterruptedException | ExecutionException | IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            DoubleBinding height = Bindings.createDoubleBinding(() -> chatVBox.getHeight(), chatVBox.heightProperty());
            chatScrollPane.vvalueProperty().bind(height);
            messageTextField.clear();
        }
    }

    @FXML
    public void sendButtonAction(KeyEvent event)
    {
        // 当按下回车键时触发
        if (event.getCode() == KeyCode.ENTER)
        {
            // 执行登录操作
            sendMessage();
        }
    }

    public void addMessage(Map<String, String> message)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        HBox messageLabel = new HBox();
        HBox textLabel = new HBox();
        messageLabel.getChildren().add(new Label(message.get("username") + "  "));
        messageLabel.getChildren().add(new Label(simpleDateFormat.format(new Date()) + "  "));
        textLabel.getChildren().add(new Label(message.get("message") + "  "));
            /*
            设置好看的样式
            背景半透明，边框，圆角，字体颜色
            */
        messageLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000;");
        textLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000; -fx-padding: 5px;");
        chatVBox.getChildren().addAll(messageLabel , textLabel);
    }
}
