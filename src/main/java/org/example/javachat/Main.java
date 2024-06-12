package org.example.javachat;

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
//        Client.startClient();
        primaryStage.show();
    }

    public static void changeView(String viewName)
    {
        Parent root;
        try
        {
            root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(viewName)));
            stage.setScene(new Scene(root));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void addView(String viewName ,Object controller)
    {
        Parent root;
        Stage newStage = new Stage();
        try
        {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource(viewName)));
            loader.setControllerFactory(param -> controller);
            root = loader.load();
            newStage.setScene(new Scene(root));
            newStage.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
