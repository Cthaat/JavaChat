package org.example.javachat;

import HTTP.delFriend;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import org.apache.http.cookie.Cookie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static HTTP.logInServletReq.cookieStore;

public class UserList
{
    List<Map<String, Object>> list;

    // 用户列表
    @FXML
    public VBox userListBox;
    // 关闭按钮
    @FXML
    public Button closeButton;
    // 用户名
    @FXML
    public Label Name;
    // 时间
    @FXML
    public Label dataTime;

    public UserList(List<Map<String, Object>> list)
    {
        this.list = list;
    }

    public void initialize()
    {
        List<Cookie> userList = cookieStore.getCookies();
        userList.get(1).getValue();
        Name.setText(userList.get(1).getValue());
        if (!list.isEmpty())
        {
            for (Map<String, Object> map : list)
            {
                HBox hBox = new HBox();
                Label label = new Label(map.get("friend_name").toString());
                Button deleteButton = new Button("删除");
                Button chatButton = new Button("聊天");
                // 按钮样式
                deleteButton.setStyle("-fx-text-alignment: center;" +
                                      "-fx-pref-height: 38px;" +
                                      "-fx-padding: 0 18px;" +
                                      "-fx-border-style: none;" +
                                      "-fx-cursor: pointer;" +
                                      "-fx-background-color: #009688;" +
                                      "-fx-text-fill: #fff;" +
                                      "-fx-border-radius: 2px;" +
                                      "-fx-background-radius: 2px;" +
                                      "-fx-font-weight: bold;" +
                                      "-fx-opacity:0.8;" +
                                      "-fx-font-size: 12px;"
                );
                chatButton.setStyle("-fx-text-alignment: center;" +
                                    "-fx-pref-height: 38px;" +
                                    "-fx-padding: 0 18px;" +
                                    "-fx-border-style: none;" +
                                    "-fx-cursor: pointer;" +
                                    "-fx-background-color: #009688;" +
                                    "-fx-text-fill: #fff;" +
                                    "-fx-border-radius: 2px;" +
                                    "-fx-background-radius: 2px;" +
                                    "-fx-font-weight: bold;" +
                                    "-fx-opacity:0.8;" +
                                    "-fx-font-size: 12px;"
                );
                label.setStyle("-fx-text-alignment: center;" +
                               "-fx-pref-height: 38px;" +
                               "-fx-padding: 0 18px;");
                deleteButton.setOnAction(event ->
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText("确认删除?");
                    alert.setContentText("删除后将无法恢复，请确认是否删除?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        delFriend delFriend = new delFriend();
                        if (delFriend.delFriendByName(map.get("friend_name").toString()))
                        {
                            list.remove(map);
                            userListBox.getChildren().remove(hBox);
                        }
                        else
                        {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("错误");
                            alert1.setHeaderText("删除失败");
                            alert1.setContentText("请检查网络连接或联系管理员");
                            alert1.showAndWait();
                        }
                    }
                });
                chatButton.setOnAction(event ->
                {
                    Chat chat = new Chat(map.get("friend_name").toString());
                    Main.addView("Chat.fxml" , chat);
                });
                hBox.getChildren().addAll(label , deleteButton , chatButton);
                userListBox.getChildren().add(hBox);
            }
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                Platform.runLater(() ->
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dataTime.setText(simpleDateFormat.format(new Date()));
                });
            }
        } , 10 , 10);
    }

    @FXML
    public void Close()
    {
        System.out.println("Close");
        System.exit(0);
    }
}
