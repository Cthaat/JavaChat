package org.example.javachat;

import Util.FXUtil;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import static Sql.querySql.isHavaUser;
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


    // 初始化
    @FXML
    public void initialize()
    {
        FXUtil.initLogin(mainPane , closeButton , minimizeButton , avatar);
    }


    @FXML
    public void doLogup()
    {
        System.out.println("Logup");
        logupLabel.setStyle("-fx-text-fill: blue;");
        changeView("signUp.fxml");
    }

    @FXML
    public void doLogin()
    {
        String accountStr = account.getText();
        String passwordStr = password.getText();
        accountEmptyError.setVisible(false);
        passwordEmptyError.setVisible(false);
        if (FXUtil.isEmpty(accountStr))
        {
            accountEmptyError.setVisible(true);
            return;
        }
        if (FXUtil.isEmpty(passwordStr))
        {
            passwordEmptyError.setVisible(true);
            return;
        }
        if(isHavaUser(accountStr, passwordStr) == -1)
        {
            passwordOrAccountError.setVisible(true);
        }
    }

    @FXML
    public void Close()
    {
        System.out.println("Close");
        System.exit(0);
    }

    @FXML
    public void Minimize()
    {
        System.out.println("Minimize");
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void settleUponWindow()
    {
        mainPane.setOnMousePressed(event ->
        {
            offsetX = event.getSceneX();
            offsetY = event.getSceneY();
        });
    }

    @FXML
    public void dragWindow()
    {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        mainPane.setOnMouseDragged(event ->
        {
            stage.setX(event.getScreenX() - offsetX);
            stage.setY(event.getScreenY() - offsetY);
            stage.setOpacity(0.857);
        });
    }

    @FXML
    public void setWindow()
    {
        Stage stage = (Stage) minimizeButton.getScene().getWindow();
        mainPane.setOnMouseReleased(event -> stage.setOpacity(1.0));
    }

    @FXML
    public void hoverCloseButton()
    {
        closeButton.setOnMouseEntered(event -> closeButtonLabelHover.setVisible(true));
    }

    @FXML
    public void exitCloseButton()
    {
        closeButton.setOnMouseExited(event -> closeButtonLabelHover.setVisible(false));
    }

    @FXML
    public void hoverMinimizeButton()
    {
        minimizeButton.setOnMouseEntered(event -> minButtonLabelHover.setVisible(true));
    }

    @FXML
    public void exitMinimizeButton()
    {
        minimizeButton.setOnMouseExited(event -> minButtonLabelHover.setVisible(false));
    }

}
