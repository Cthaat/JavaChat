package org.example.javachat;

import HTTP.signUpSer;
import Util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static org.example.javachat.Main.changeView;


/**
 * @Auther: Edge
 * @Date: 2024/6/22 13:48
 * @Description: TODO
 * @version: 1.0
 **/


public class signUp
{
    // 关于按钮
    @FXML
    public Button aboutButton;
    // 主界面
    @FXML
    private AnchorPane mainPane;
    // 主界面X坐标
    private double offsetX = 0;
    // 主界面Y坐标
    private double offsetY = 0;
    // 账户输入框
    @FXML
    public TextField account;
    // 密码输入框
    @FXML
    public PasswordField password;
    // 注册按钮
    @FXML
    public Button signUpButton;
    // 注册按钮的标签
    @FXML
    private Label accountLabel;
    // 账户为空时的错误提示
    @FXML
    private Label accountEmptyError;
    // 密码为空时的错误提示
    @FXML
    private Label passwordEmptyError;
    // 头像
    @FXML
    public ImageView avatar;
    // 关闭按钮
    @FXML
    private Button closeButton;
    // 关闭按钮的标签
    @FXML
    private Label closeButtonLabel;
    // 最小化按钮
    @FXML
    private Button minimizeButton;
    // 最小化按钮的标签
    @FXML
    private Label minButtonLabel;
    // 关闭按钮的覆盖装饰
    @FXML
    private Label minButtonLabelHover;
    // 最小化按钮的覆盖装饰
    @FXML
    private Label closeButtonLabelHover;
    // 密码标签
    @FXML
    private Label passwordLabel;
    // 右上角Logo
    @FXML
    private Label Logo;
    // 选择头像按钮
    @FXML
    private Button selectAvatar;
    // 选择头像按钮的标签
    @FXML
    private Label selectAvatarLabel;
    // 选择头像按钮的覆盖装饰
    @FXML
    private Label selectAvatarHover;
    // 重复密码输入框
    @FXML
    private PasswordField passwordAgain;
    // 重复密码标签
    @FXML
    private Label passwordAgainLabel;
    // 重复密码为空时的错误提示
    @FXML
    private Label passwordAgainError;
    // 返回按钮
    @FXML
    private Button returnToLogInButton;
    // 返回按钮的标签
    @FXML
    private Label returnToLogInLabel;
    // 返回按钮的覆盖装饰
    @FXML
    private Label returnToLogInLabelHover;

    // 选择头像
    // 初始化
    @FXML
    public void initialize()
    {

        /**
         * @description: 初始化登录界面
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:48
         **/

        // 使用FXUtil工具类初始化登录界面
        FXUtil.initLogin(mainPane , closeButton , minimizeButton , avatar);
        // 移除选择头像的控件
        mainPane.getChildren().remove(selectAvatar);
        // 添加选择头像的控件
        mainPane.getChildren().add(selectAvatar);
        // 移除返回登录按钮
        mainPane.getChildren().remove(returnToLogInButton);
        // 添加返回登录按钮
        mainPane.getChildren().add(returnToLogInButton);
    }

    // 注册
    public void doSignUp()
    {
        String accountText = account.getText();
        String passwordText = password.getText();
        String passwordAgainText = passwordAgain.getText();
        accountEmptyError.setVisible(false);
        passwordEmptyError.setVisible(false);
        if (account.getText().isEmpty())
        {
            accountEmptyError.setVisible(true);
            return;
        }
        if (password.getText().isEmpty())
        {
            passwordEmptyError.setVisible(true);
            return;
        }
        if (passwordAgain.getText().isEmpty())
        {
            passwordAgainError.setVisible(true);
            return;
        }
        if (!passwordText.equals(passwordAgainText))
        {
            passwordAgainError.setVisible(true);
            return;
        }
        // 调用注册接口
        signUpSer signUp = new signUpSer();
        if (signUp.signUp(accountText, passwordText))
        {
            System.out.println("注册成功");
            // 切换到登录界面
            changeView("Login.fxml");
        }
        else
        {
            System.out.println("注册失败");
        }
    }

    // 返回登录
    public void returnToLogIn()
    {

        /**
         * @description: 返回登录界面
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        System.out.println("Login");
        // 切换到登录界面
        changeView("Login.fxml");
    }

    // 关闭
    @FXML
    public void Close()
    {

        /**
         * @description: 关闭程序
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        System.out.println("Close");
        // 关闭程序
        System.exit(0);
    }

    // 最小化窗口
    @FXML
    public void Minimize()
    {

        /**
         * @description: 最小化窗口
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        System.out.println("Minimize");
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    // 设置鼠标按下时的坐标
    @FXML
    public void settleUponWindow()
    {

        /**
         * @description: 设置鼠标按下时的坐标
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        mainPane.setOnMousePressed(event ->
        {
            offsetX = event.getSceneX();
            offsetY = event.getSceneY();
        });
    }

    // 拖动窗口
    // 定义一个函数，用于拖动窗口
    @FXML
    public void dragWindow()
    {

        /**
         * @description: 拖动窗口
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        // 获取当前窗口
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        // 设置鼠标拖动事件
        mainPane.setOnMouseDragged(event ->
        {
            // 设置窗口X坐标
            stage.setX(event.getScreenX() - offsetX);
            // 设置窗口Y坐标
            stage.setY(event.getScreenY() - offsetY);
            // 设置窗口透明度
            stage.setOpacity(0.857);
        });
    }

    // 设置窗口
    @FXML
    public void setWindow()
    {

        /**
         * @description: 设置窗口
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        // 获取当前场景的窗口
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        // 当鼠标释放时，设置窗口的不透明度为1.0
        mainPane.setOnMouseReleased(event -> stage.setOpacity(1.0));
    }

    // 当鼠标悬停在关闭按钮上时
    @FXML
    public void hoverCloseButton()
    {

        /**
         * @description: 当鼠标悬停在关闭按钮上时
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        // 设置鼠标进入事件，显示关闭按钮的鼠标悬停提示
        closeButton.setOnMouseEntered(event -> closeButtonLabelHover.setVisible(true));
    }

    // 当鼠标离开关闭按钮时
    @FXML
    public void exitCloseButton()
    {

        /**
         * @description: 当鼠标离开关闭按钮时
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        // 设置鼠标离开事件，隐藏关闭按钮的鼠标悬停提示
        closeButton.setOnMouseExited(event -> closeButtonLabelHover.setVisible(false));
    }

    // 当鼠标悬停在最小化按钮上时
    @FXML
    public void hoverMinimizeButton()
    {

        /**
         * @description: 当鼠标悬停在最小化按钮上时
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        // 设置鼠标进入事件，显示最小化按钮的鼠标悬停提示
        minimizeButton.setOnMouseEntered(event -> minButtonLabelHover.setVisible(true));
    }

    // 当鼠标离开最小化按钮时，设置minButtonLabelHover的可见性为false
    @FXML
    public void exitMinimizeButton()
    {

        /**
         * @description: 当鼠标离开最小化按钮时，设置minButtonLabelHover的可见性为false
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        minimizeButton.setOnMouseExited(event -> minButtonLabelHover.setVisible(false));
    }

    // 当鼠标进入选择头像按钮时，设置selectAvatarHover的可见性为true
    @FXML
    public void hoverselectAvatarButton()
    {

        /**
         * @description: 当鼠标进入选择头像按钮时，设置selectAvatarHover的可见性为true
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:49
         **/

        selectAvatar.setOnMouseEntered(event -> selectAvatarHover.setVisible(true));
    }

    // 当鼠标离开选择头像按钮时，设置selectAvatarHover的可见性为false
    @FXML
    public void exitselectAvatarButton()
    {

        /**
         * @description: 当鼠标离开选择头像按钮时，设置selectAvatarHover的可见性为false
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:50
         **/

        selectAvatar.setOnMouseExited(event -> selectAvatarHover.setVisible(false));
    }

    // 当鼠标悬停在returnToLogInButton按钮上时，调用hoverToLogInButton方法
    @FXML
    public void hoverToLogInButton()
    {

        /**
         * @description: 当鼠标悬停在returnToLogInButton按钮上时，调用hoverToLogInButton方法
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:50
         **/

        // 当鼠标进入returnToLogInButton按钮时，将returnToLogInLabelHover设置为可见
        returnToLogInButton.setOnMouseEntered(event -> returnToLogInLabelHover.setVisible(true));
    }

    // 当鼠标离开returnToLogInButton按钮时，调用exitToLogInButton方法
    @FXML
    public void exitToLogInButton()
    {

        /**
         * @description: 当鼠标离开returnToLogInButton按钮时，调用exitToLogInButton方法
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:50
         **/

        // 当鼠标离开returnToLogInButton按钮时，将returnToLogInLabelHover设置为不可见
        returnToLogInButton.setOnMouseExited(event -> returnToLogInLabelHover.setVisible(false));
    }

    // 关于按钮的事件
    @FXML
    public void showAbout()
    {

        /**
         * @description: 关于按钮的事件
         * @param:
         * @return: void
         * @author Edge
         * @date: 2024/6/22 13:50
         **/

        System.out.println("About");
        Main.addView("about.fxml" , "About");
    }

    // 注册按钮的事件
    @FXML
    public void signUpButtonAction(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
        {
            this.doSignUp();
        }
    }
}
