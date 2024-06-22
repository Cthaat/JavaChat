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


/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:48
 * @Description: TODO
 * @version: 1.0
 **/


public class Main extends Application
{
    private static Stage stage;

    public static void main(String[] args)
    {

        /**
         * @description: 启动客户端
         * @param:
         * @param args 启动参数
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:48
         **/

        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {

        /**
         * @description: 初始化客户端界面
         * @param:
         * @param primaryStage 客户端窗口
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:48
         **/

        Main.stage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED); // 设置窗口样式为无边框
        changeView("Login.fxml");
        primaryStage.show();
    }

    public static void changeView(String viewName)
    {

        /**
         * @description: 切换客户端界面
         * @param:
         * @param viewName 视图名称
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:48
         **/

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

    public static void addView(String viewName , Object controller)
    {

        /**
         * @description: 添加新视图
         * @param:
         * @param viewName
         * @param controller 控制器
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:48
         **/

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

        /**
         * @description: 切换客户端界面
         * @param:
         * @param viewName
         * @param controller 控制器
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:48
         **/

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
