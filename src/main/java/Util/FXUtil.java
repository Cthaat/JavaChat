package Util;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * 这是一个工具类，用于处理一些FXML相关的操作
 */


/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:52
 * @Description: TODO
 * @version: 1.0
 **/


public class FXUtil
{
    private FXUtil()
    {

        /**
         * @description: 私有构造方法，防止实例化
         * @param:
         * @return:
         * @author Edge
         * @date: 2024/6/22 13:52
         **/

    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 返回true如果字符串为空，否则返回false
     */
    public static boolean isEmpty(String str)
    {

        /**
         * @description: 判断字符串是否为空
         * @param:
         * @param str 字符串
         * @return: boolean
         * @author Edge
         * @date: 2024/6/22 13:52
         **/

        return str == null || str.trim().isEmpty();
    }

    /**
     * 初始化登录界面
     *
     * @param mainPane       锚点板
     * @param closeButton    关闭按钮
     * @param minimizeButton 最小化按钮
     * @param avatar         头像
     */
    public static void initLogin(AnchorPane mainPane , Button closeButton , Button minimizeButton , ImageView avatar)
    {

        /**
         * @description: 初始化登录界面
         * @param:
         * @param mainPane
         * @param closeButton
         * @param minimizeButton
         * @param avatar 头像
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:52
         **/

        mainPane.getChildren().remove(closeButton);
        mainPane.getChildren().remove(minimizeButton);
        mainPane.getChildren().add(closeButton);
        mainPane.getChildren().add(minimizeButton);
        Circle clip = new Circle(avatar.getFitWidth() / 2 , avatar.getFitHeight() / 2 , avatar.getFitWidth() / 2);
        avatar.setClip(clip);
    }
}
