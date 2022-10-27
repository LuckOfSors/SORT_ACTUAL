module com.example.sort_actual {
    requires javafx.controls;
    requires javafx.fxml;
    requires unirest.java;
    requires com.google.gson;


    opens volpe.kennedy.sort_actual to javafx.fxml;
    exports volpe.kennedy.sort_actual;
}