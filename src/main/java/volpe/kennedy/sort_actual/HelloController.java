package volpe.kennedy.sort_actual;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {

    ObservableList<String> sortList = FXCollections.observableArrayList("merge","insertion", "selection");
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML

    private ChoiceBox choiceBox;

    @FXML
    public void initialize() {
        if (choiceBox != null) {
            choiceBox.setItems(sortList);
            choiceBox.setValue("sorts");
        }
    }

    @FXML
    public void switchtoSort(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void switchtoSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}