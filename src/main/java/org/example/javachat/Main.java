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
    public void start(Stage primaryStage) throws IOException
    {
        Main.stage = primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED); // 设置窗口样式为无边框
        changeView("Login.fxml");
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
}
