package org.example.javachat;

import HTTP.logInServletReq;
import HTTP.logInServletReqImp;
import Util.FXUtil;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import static org.example.javachat.Main.addView;
import static org.example.javachat.Main.changeView;

public class Login
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
    // 登录按钮
    @FXML
    public Button loginButton;
    // 注册按钮
    @FXML
    public Button logUpButton;
    // 注册按钮的标签
    @FXML
    private Label logupLabel;
    // 账户为空时的错误提示
    @FXML
    private Label accountEmptyError;
    // 密码为空时的错误提示
    @FXML
    private Label passwordEmptyError;
    // 账户或密码错误时的错误提示
    @FXML
    public Label passwordOrAccountError;
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
    // 登录成功标签
    @FXML
    private Label logupSuccessLabel;


    // 初始化
    @FXML
    public void initialize()
    {
        FXUtil.initLogin(mainPane , closeButton , minimizeButton , avatar);
    }


    // 打印Logup
    @FXML
    public void doLogup()
    {
        System.out.println("Logup");
        // 设置logupLabel文本颜色为蓝色
        logupLabel.setStyle("-fx-text-fill: blue;");
        // 切换到signUp.fxml页面
        changeView("signUp.fxml");
    }

    // 执行登录操作
    @FXML
    public void doLogin()
    {
        // 获取账号和密码
        String accountStr = account.getText();
        String passwordStr = password.getText();
        // 隐藏账号和密码为空的错误提示
        accountEmptyError.setVisible(false);
        passwordEmptyError.setVisible(false);
        passwordOrAccountError.setVisible(false);
        // 如果账号为空
        if (FXUtil.isEmpty(accountStr))
        {
            // 显示账号为空的错误提示
            accountEmptyError.setVisible(true);
            return;
        }
        // 如果密码为空
        if (FXUtil.isEmpty(passwordStr))
        {
            // 显示密码为空的错误提示
            passwordEmptyError.setVisible(true);
            return;
        }
        // 创建logInServletReq对象
        logInServletReq loginServlet = new logInServletReq();
        // 如果登录成功
        if (loginServlet.logIn(accountStr , passwordStr))
        {
            System.out.println("Login success");
        }
        // 如果登录失败
        else
        {
            // 显示账号或密码错误的错误提示
            passwordOrAccountError.setVisible(true);
        }
    }

    // 当按下登录按钮时触发
    @FXML
    public void loginButtonAction(KeyEvent event)
    {
        // 当按下回车键时触发
        if (event.getCode() == KeyCode.ENTER)
        {
            // 执行登录操作
            doLogin();
        }
    }

    // 当点击关闭按钮时触发
    @FXML
    public void Close()
    {
        System.out.println("Close");
        // 退出程序
        System.exit(0);
    }

    // 当点击最小化按钮时触发
    @FXML
    public void Minimize()
    {
        System.out.println("Minimize");
        // 获取当前窗口
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        // 将窗口最小化
        stage.setIconified(true);
    }

    // 鼠标按下时触发
    @FXML
    public void settleUponWindow()
    {
        // 获取主面板
        mainPane.setOnMousePressed(event ->
        {
            // 记录鼠标按下的位置
            offsetX = event.getSceneX();
            offsetY = event.getSceneY();
        });
    }

    // 鼠标拖动时触发
    @FXML
    public void dragWindow()
    {
        // 获取舞台
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        // 设置鼠标拖动事件
        mainPane.setOnMouseDragged(event ->
        {
            // 设置舞台的位置
            stage.setX(event.getScreenX() - offsetX);
            stage.setY(event.getScreenY() - offsetY);
            // 设置舞台的透明度
            stage.setOpacity(0.857);
        });
    }

    @FXML
    public void setWindow()
    {
        // 设置窗口透明度为1.0
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        mainPane.setOnMouseReleased(event -> stage.setOpacity(1.0));
    }

    @FXML
    public void hoverCloseButton()
    {
        // 当鼠标进入关闭按钮时显示关闭按钮标签
        closeButton.setOnMouseEntered(event -> closeButtonLabelHover.setVisible(true));
    }

    @FXML
    public void exitCloseButton()
    {
        // 当鼠标离开关闭按钮时隐藏关闭按钮标签
        closeButton.setOnMouseExited(event -> closeButtonLabelHover.setVisible(false));
    }

    @FXML
    public void hoverMinimizeButton()
    {
        // 当鼠标进入最小化按钮时显示最小化按钮标签
        minimizeButton.setOnMouseEntered(event -> minButtonLabelHover.setVisible(true));
    }

    @FXML
    public void exitMinimizeButton()
    {
        // 当鼠标离开最小化按钮时隐藏最小化按钮标签
        minimizeButton.setOnMouseExited(event -> minButtonLabelHover.setVisible(false));
    }


}
