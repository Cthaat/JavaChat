package org.example.javachat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import org.apache.http.cookie.Cookie;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static HTTP.logInServletReq.cookieStore;

public class UserList
{
    List<Map<String, Object>> list = new ArrayList<>();

    // 用户列表
    @FXML
    public VBox userList;
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
