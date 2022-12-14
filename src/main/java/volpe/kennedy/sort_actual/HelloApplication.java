/*
Joe Volpe and Colin Kennedy
Sort Project
10/31/2022
Schenk
*/
package volpe.kennedy.sort_actual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 450);
        stage.setTitle("Search");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {launch();}





}
