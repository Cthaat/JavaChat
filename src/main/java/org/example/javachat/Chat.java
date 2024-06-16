package org.example.javachat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
        System.out.println(messages);
    }

}
