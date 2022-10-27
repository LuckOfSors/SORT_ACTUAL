module com.example.sort_actual {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;


    opens volpe.kennedy.sort_actual to javafx.fxml;
    exports volpe.kennedy.sort_actual;
}