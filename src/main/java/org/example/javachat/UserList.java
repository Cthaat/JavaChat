package org.example.javachat;

import HTTP.addFriend;
import HTTP.delFriend;
import HTTP.getAllfriend;
import HTTP.getP2pMessages;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.http.cookie.Cookie;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static HTTP.logInServletReq.cookieStore;


/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:50
 * @Description: TODO
 * @version: 1.0
 **/


public class UserList
{
    // 好友列表
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
    // 添加好友文本框
    @FXML
    public TextField addFriendText;
    // 添加好友按钮
    @FXML
    public Button addFriendButton;

    public UserList(List<Map<String, Object>> list)
    {

        /**
         * @description: 初始化好友列表
         * @param:
         * @param list
         * @return:
         * @author Edge
         * @date: 2024/6/22 13:50
         **/

        this.list = list;
    }

    public void initialize()
    {

        /**
         * @description: 初始化好友列表
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:51
         **/

        // 获取cookie中的用户信息
        List<Cookie> userList = cookieStore.getCookies();
        // 设置用户名
        userList.get(1).getValue();
        Name.setText(userList.get(1).getValue());
        // 判断list是否为空
        if (!list.isEmpty())
        {
            // 遍历list中的map
            for (Map<String, Object> map : list)
            {
                // 创建HBox
                HBox hBox = new HBox();
                // 创建Label，设置文本为map中的friend_name
                Label label = new Label(map.get("friend_name").toString());
                // 创建删除按钮
                Button deleteButton = new Button("删除");
                // 创建聊天按钮
                Button chatButton = new Button("聊天");
                // 设置按钮样式
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
                // 为删除按钮添加点击事件
                deleteButton.setOnAction(event ->
                {
                    // 弹出确认删除对话框
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("提示");
                    alert.setHeaderText("确认删除?");
                    alert.setContentText("删除后将无法恢复，请确认是否删除?");
                    // 等待用户确认
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        // 确认删除，执行删除操作
                        delFriend delFriend = new delFriend();
                        if (delFriend.delFriendByName(map.get("friend_name").toString()))
                        {
                            // 删除成功，从列表中移除该好友
                            list.remove(map);
                            userListBox.getChildren().remove(hBox);
                        }
                        else
                        {
                            // 删除失败，弹出错误提示框
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("错误");
                            alert1.setHeaderText("删除失败");
                            alert1.setContentText("请检查网络连接或联系管理员");
                            alert1.showAndWait();
                        }
                    }
                });
                //给chatButton添加一个事件监听，当chatButton被点击时，执行下面的代码
                chatButton.setOnAction(event ->
                {
                    getP2pMessages call = new getP2pMessages(map.get("friend_name").toString());
                    FutureTask<List<Map<String, Object>>> futureTask = new FutureTask<>(call);
                    new Thread(futureTask).start();
                    //创建一个新的Chat对象，传入好友的名称
                    Chat chat = null;
                    try
                    {
                        chat = new Chat(map.get("friend_name").toString() , futureTask.get() , userList.get(1).getValue());
                    }
                    catch (InterruptedException | ExecutionException e)
                    {
                        throw new RuntimeException(e);
                    }
                    //调用Main类的addView方法，加载Chat.fxml页面，并传入chat对象
                    Main.addView("Chat.fxml" , chat);
                });
                //将label、deleteButton、chatButton添加到hBox中
                hBox.getChildren().addAll(label , deleteButton , chatButton);
                //将hBox添加到userListBox中
                userListBox.getChildren().add(hBox);
            }
        }
        // 创建一个Timer对象
        Timer timer = new Timer();
// 使用Timer对象的schedule方法，创建一个定时任务
        timer.schedule(new TimerTask()
        {
            // 重写run方法，当定时任务执行时，会调用这个方法
            public void run()
            {
                // 使用Platform.runLater方法，在事件队列中执行一个Runnable任务
                Platform.runLater(() ->
                {
                    // 创建一个SimpleDateFormat对象，用于格式化日期
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    // 将格式化后的日期，设置为dataTime的文本
                    dataTime.setText(simpleDateFormat.format(new Date()));
                });
            }
        } , 10 , 10);
    }

    @FXML
    // 关闭函数
    public void Close()
    {

        /**
         * @description: 关闭函数
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:51
         **/

        // 打印出关闭信息
        System.out.println("Close");
        // 退出程序
        System.exit(0);
    }

    @FXML
    public void addFriend()
    {

        /**
         * @description: 添加好友函数
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:51
         **/

        // 获取输入的好友名称
        String friendName = addFriendText.getText();
        addFriend addFriend = new addFriend();
        if (isFriend(friendName))
        {
            // 好友已存在，弹出错误提示框
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("添加失败");
            alert.setContentText("好友已存在");
            alert.showAndWait();
            return;
        }
        if (addFriend.addFriendByName(friendName))
        {
            try
            {
                // 异步获取响应结果
                getAllfriend call = new getAllfriend();
                FutureTask<List<Map<String, Object>>> futureTask = new FutureTask<>(call);
                new Thread(futureTask).start();
                this.list = futureTask.get();
                // 好友添加成功，刷新页面
                initialize();
            }
            catch (InterruptedException | ExecutionException e)
            {
                // 好友添加失败，弹出错误提示框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("添加失败");
                alert.setContentText("用户不存在");
                alert.showAndWait();
            }
        }
        else
        {
            // 好友添加失败，弹出错误提示框
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("添加失败");
            alert.setContentText("用户不存在");
            alert.showAndWait();
        }
    }


    // 判断friendName是否是好友列表中的好友
    public boolean isFriend(String friendName)
    {

        /**
         * @description: 判断friendName是否是好友列表中的好友
         * @param:
         * @param friendName 好友名称
         * @return: boolean
         * @author Edge
         * @date: 2024/6/22 13:51
         **/

        // 遍历好友列表
        for (Map<String, Object> map : list)
        {
            // 如果friendName与map中的friend_name相等
            if (map.get("friend_name").equals(friendName))
            {
                // 返回true
                return true;
            }
        }
        // 否则返回false
        return false;
    }
}
