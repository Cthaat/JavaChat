package org.example.javachat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:50
 * @Description: TODO
 * @version: 1.0
 **/


public class signUpApp extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException
    {

        /**
         * @description: 启动JavaFX应用程序
         * @param:
         * @param primaryStage 主窗口
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:50
         **/

        // 设置窗口样式为无边框
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // 通过FXMLLoader加载signUp.fxml文件
        FXMLLoader fxmlLoader = new FXMLLoader(signUpApp.class.getResource("signUp.fxml"));
        // 通过FXMLLoader加载signUp.fxml文件，获取场景
        Scene scene = new Scene(fxmlLoader.load());
        // 设置场景
        primaryStage.setScene(scene);
        // 显示窗口
        primaryStage.show();
    }
}
