package volpe.kennedy.sort_actual;

import com.mashape.unirest.http.exceptions.UnirestException;
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
import java.util.Scanner;


import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
//JSON PARSING IMPORTS

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class HelloController {

    ObservableList<String> sortList = FXCollections.observableArrayList("merge", "insertion", "selection");

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void switchtoSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //hdudhgf

    }

    @FXML
    public void printArray(ActionEvent event) throws IOException {

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

    @FXML
    public void callMerge(ActionEvent event) throws IOException{
        mergeSort(movieArray, 0, movieArray.length);
    }


    private static void merge(String[] array, int first, int numLe1, int numLe2) {
        String[] tempArr = new String[numLe1 + numLe2];
        int clone1 = 0;
        int clone2 = 0;
        int clone3 = 0;

        while ((clone2 < numLe1) && (clone3 < numLe2)) {
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

    public static void mergeSort(String[] array, int first, int length) {
        int len1 = 0;
        int len2 = 0;

        if (length > 1) {
            len1 = length / 2;
            len2 = length - len1;

            mergeSort(array, first, len1);
            mergeSort(array, first + len1, len2);
        }

        merge(array, first, len1, len2);

        for (String element : array)
            System.out.print(element + " ");
    }


    public static String[] Management() throws UnirestException {
        Scanner input = new Scanner(System.in);
        System.out.println("What movies would you like to search for: ");
        String x = input.next();
        input.close();

        HttpResponse<String> response = Unirest.get("https://movie-database-alternative.p.rapidapi.com/?s=" + x + "&r=json&page=1")
                .header("X-RapidAPI-Key", "0da6c9c507msh27d66d057973f0ep13289ajsnc1cd4d8defc8")
                .header("X-RapidAPI-Host", "movie-database-alternative.p.rapidapi.com")
                .asString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.getBody().toString());
        String clean = gson.toJson(element);

        HelloController parse = new HelloController();
        String[] data = parse.Find(clean);
        String[] title= new String[data.length];

        for(int z = 0; z < data.length; z++){
            char[] temp = data[z].toCharArray();
            char[] arr = new char[data[z].length()];
            for(int y= 0;y<temp.length;y++){
                if(temp[y]=='T'){
                    while(temp[y]!=','){
                        arr[y] = temp[y];
                        y++;

                    }
                    String name = String.valueOf(arr).trim();
                    title[z] = name;
                    System.out.println(title[z]);
                    z++;
                    break;

                }
            }
        }
        return title;
    }
    public String[] Find(String data){

        //converting json into char array
        char[] array = data.toCharArray();

        //defining loop variables
        int index = 0;
        int len = array.length;
        int placeholder = 0;

        //creating char arr for item & creating string array to store items
        char[] item = new char[len];
        String[] placeList = new String[len];

        //loop through data
        for(int x=1;x<len;x++)
        {
            //if data starts with "{" start to loop
            if(array[x]=='{'){
                x++;
                //if item within array isn't "}" or end of item continue to add arr[x] to item
                while(x<len && array[x]!='}'){
                    item[index] = array[x];
                    x++;
                    index++;
                }
                //Deleting null characters off of item
                String item2 = String.valueOf(item).trim();
                //placing item in item list
                placeList[placeholder] = item2;

                item  = new char[len];
                index = 0;

                placeholder++;
            }
        }
        //Creating new String array
        String[] dataList = new String[placeholder];

        //removing null characters from string array
        for(int x=0;x<placeholder;x++) {
            if(placeList[x]!= null){
                dataList[x] = placeList[x];
            }
        }
        //print items of list;
        for(int x=0;x<placeholder;x++) {
            System.out.println(dataList[x]);
        }

        return dataList;
    }
}



























