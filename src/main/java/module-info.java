module org.example.javachat {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.jdbc;
    requires druid;
    requires java.sql;
    requires java.naming;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;


    opens org.example.javachat to javafx.fxml;
    exports org.example.javachat;
}