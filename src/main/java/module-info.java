module com.example.sort_actual {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sort_actual to javafx.fxml;
    exports com.example.sort_actual;
}