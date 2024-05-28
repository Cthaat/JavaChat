package org.example.javachat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class LoginAPP extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException
    {
        primaryStage.initStyle(StageStyle.UNDECORATED); // 设置窗口样式为无边框
        FXMLLoader fxmlLoader = new FXMLLoader(LoginAPP.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
