module com.example.sort_actual {
    requires javafx.controls;
    requires javafx.fxml;


    opens volpe.kennedy.sort_actual to javafx.fxml;
    exports volpe.kennedy.sort_actual;
}