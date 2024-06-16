package org.example.javachat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class Chat
{
    private final String username;
    private List<Map<String, Object>> messages;

    // 聊天框
    @FXML
    public VBox chatVBox;
    // 用户名
    @FXML
    public Label userName;

    public Chat(String username , List<Map<String, Object>> messages)
    {
        this.username = username;
        this.messages = messages;
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
            设置好看的样式
            背景半透明，边框，圆角，字体颜色
            */
            messageLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000;");
            textLabel.setStyle("-fx-background-color: rgba(255, 0.5, 0.5, 0.1); -fx-border-color: #000000; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #000000; -fx-padding: 5px;");
            chatVBox.getChildren().addAll(messageLabel , textLabel);
        }
    }
}
