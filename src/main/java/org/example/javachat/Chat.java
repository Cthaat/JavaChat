package org.example.javachat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Chat
{
    private final String username;

    // 聊天框
    @FXML
    public VBox chatVBox;
    // 用户名
    @FXML
    public Label userName;

    public Chat(String username)
    {
        this.username = username;
    }

    public void initialize()
    {
        userName.setText(username);
    }
}
