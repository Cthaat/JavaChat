package Util;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class FXUtil
{
    private FXUtil()
    {
    }
    public static boolean isEmpty(String str)
    {
        return str == null || str.trim().isEmpty();
    }

    public static void initLogin(AnchorPane mainPane , Button closeButton , Button minimizeButton , ImageView avatar)
    {
        mainPane.getChildren().remove(closeButton);
        mainPane.getChildren().remove(minimizeButton);
        mainPane.getChildren().add(closeButton);
        mainPane.getChildren().add(minimizeButton);
        Circle clip = new Circle(avatar.getFitWidth() / 2 , avatar.getFitHeight() / 2 , avatar.getFitWidth() / 2);
        avatar.setClip(clip);
    }
}
