package org.example.javachat;

import Util.FXUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static org.example.javachat.Main.changeView;

public class signUp
{
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


    // 初始化
    // 初始化
@FXML
    public void initialize()
    {
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

    }

    // 返回登录
    public void returnToLogIn()
    {
        System.out.println("Login");
        // 切换到登录界面
        changeView("Login.fxml");
    }

    // 关闭
    @FXML
    public void Close()
    {
        System.out.println("Close");
        // 关闭程序
        System.exit(0);
    }

    // 最小化窗口
    @FXML
    public void Minimize()
    {
        System.out.println("Minimize");
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

// 设置鼠标按下时的坐标
    @FXML
    public void settleUponWindow()
    {
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
        // 获取当前场景的窗口
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        // 当鼠标释放时，设置窗口的不透明度为1.0
        mainPane.setOnMouseReleased(event -> stage.setOpacity(1.0));
    }

    // 当鼠标悬停在关闭按钮上时
    @FXML
    public void hoverCloseButton()
    {
        // 设置鼠标进入事件，显示关闭按钮的鼠标悬停提示
        closeButton.setOnMouseEntered(event -> closeButtonLabelHover.setVisible(true));
    }

    // 当鼠标离开关闭按钮时
    @FXML
    public void exitCloseButton()
    {
        // 设置鼠标离开事件，隐藏关闭按钮的鼠标悬停提示
        closeButton.setOnMouseExited(event -> closeButtonLabelHover.setVisible(false));
    }

    // 当鼠标悬停在最小化按钮上时
    @FXML
    public void hoverMinimizeButton()
    {
        // 设置鼠标进入事件，显示最小化按钮的鼠标悬停提示
        minimizeButton.setOnMouseEntered(event -> minButtonLabelHover.setVisible(true));
    }

    // 当鼠标离开最小化按钮时，设置minButtonLabelHover的可见性为false
    @FXML
    public void exitMinimizeButton()
    {
        minimizeButton.setOnMouseExited(event -> minButtonLabelHover.setVisible(false));
    }

// 当鼠标进入选择头像按钮时，设置selectAvatarHover的可见性为true
    @FXML
    public void hoverselectAvatarButton()
    {
        selectAvatar.setOnMouseEntered(event -> selectAvatarHover.setVisible(true));
    }

// 当鼠标离开选择头像按钮时，设置selectAvatarHover的可见性为false
    @FXML
    public void exitselectAvatarButton()
    {
        selectAvatar.setOnMouseExited(event -> selectAvatarHover.setVisible(false));
    }

    // 当鼠标悬停在returnToLogInButton按钮上时，调用hoverToLogInButton方法
    @FXML
    public void hoverToLogInButton()
    {
        // 当鼠标进入returnToLogInButton按钮时，将returnToLogInLabelHover设置为可见
        returnToLogInButton.setOnMouseEntered(event -> returnToLogInLabelHover.setVisible(true));
    }

    // 当鼠标离开returnToLogInButton按钮时，调用exitToLogInButton方法
    @FXML
    public void exitToLogInButton()
    {
        // 当鼠标离开returnToLogInButton按钮时，将returnToLogInLabelHover设置为不可见
        returnToLogInButton.setOnMouseExited(event -> returnToLogInLabelHover.setVisible(false));
    }
}
