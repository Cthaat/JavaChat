package org.example.javachat;

import HTTP.getAllfriend;
import HTTP.p2pSendMessages;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
            HBox messageLabel = new HBox();
            HBox textLabel = new HBox();
            messageLabel.getChildren().add(new Label(message.get("send_user_name") + "  "));
            messageLabel.getChildren().add(new Label(message.get("date") + "  "));
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
            if (!result)
            {
                messageLabel.getChildren().add(new Label("发送失败"));
            }
        }
        catch (InterruptedException | ExecutionException e)
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
}
