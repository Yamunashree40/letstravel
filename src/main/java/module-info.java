module com.example.letstravel {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.letstravel to javafx.fxml;
    exports com.example.letstravel;
}