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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HelloController {

    ObservableList<String> sortList = FXCollections.observableArrayList("merge","insertion", "selection");

    String[] movieArray = {"Goodfellas", "Bermuda", "XX", "Rtx", "Wsa"};

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

    @FXML
    public void insertionSort(ActionEvent event) throws IOException {
        int i;
        int j;
        String key;

        System.out.println(Arrays.toString(movieArray));

        for (j = 1; j < movieArray.length; j++) {
            key = movieArray[j];
            i = j - 1;
            while (i >= 0) {
                if (key.compareTo(movieArray[i]) > 0) {
                    break;
                }
                movieArray[i + 1] = movieArray[i];
                i--;
            }
            movieArray[i + 1] = key;
            System.out.println(Arrays.toString(movieArray));
        }
        System.out.println(Arrays.toString(movieArray));
    }
    private static void merge(String[] array, int first, int numLe1, int numLe2)
    {
        String[] tempArr = new String[numLe1 + numLe2];
        int clone1 = 0;
        int clone2 = 0;
        int clone3 = 0;

        while ((clone2 < numLe1) && (clone3 < numLe2))
        {
            if (array[first + clone2].compareTo(array[first + numLe1 + clone3]) < 0)
                tempArr[clone1++] = array[first + (clone2++)];
            else
                tempArr[clone1++] = array[first + numLe1 + (clone3++)];
        }

        while (clone2 < numLe1)
            tempArr[clone1++] = array[first + (clone2++)];
        while (clone3 < numLe2)
            tempArr[clone1++] = array[first + numLe1 + (clone3++)];

        for (int i = 0; i < clone1; i++)
            array[first + i] = tempArr[i];

    }

    public static void mergesort(String[] array, int first, int length) {
        int len1 = 0;
        int len2 = 0;

        if(length > 1) {
            len1 = length/2;
            len2 = length-len1;

            mergesort(array, first, len1);
            mergesort(array, first + len1, len2);
        }

        merge(array, first, len1, len2);

        for(String element: array)
            System.out.print(element +" ");
    }
}

