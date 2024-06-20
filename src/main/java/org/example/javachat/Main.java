package org.example.javachat;

import HTTP.chatClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application
{
    private static Stage stage;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Main.stage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED); // 设置窗口样式为无边框
        changeView("Login.fxml");
        primaryStage.show();
    }

    public static void changeView(String viewName)
    {
        // 加载指定视图
        Parent root;
        try
        {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(viewName)));
            // 设置场景
            stage.setScene(new Scene(root));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void addView(String viewName ,Object controller)
    {
        // 加载指定视图
        Parent root;
        // 创建新舞台
        Stage newStage = new Stage();
        try
        {
            // 加载指定视图
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(viewName)));
            // 设置控制器
            loader.setControllerFactory(param -> controller);
            root = loader.load();
            // 设置场景
            newStage.setScene(new Scene(root));
            // 显示新舞台
            newStage.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void changeView(String viewName , Object controller)
    {
        // 加载指定视图
        Parent root;
        try
        {
            // 加载指定视图
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(viewName)));
            // 设置控制器
            loader.setControllerFactory(param -> controller);
            root = loader.load();
            // 设置场景
            stage.setScene(new Scene(root));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
