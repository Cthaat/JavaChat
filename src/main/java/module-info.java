module org.example.javachat {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javachat to javafx.fxml;
    exports org.example.javachat;
}