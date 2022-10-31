package volpe.kennedy.sort_actual;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class HelloController implements Initializable {

    ObservableList<String> sortList = FXCollections.observableArrayList("merge", "insertion", "selection");

    List<String> movieList = new ArrayList<String>();


    String[] movieArray = Management();
    //String[] movieArray = {"Norm Of the North", "Ender's Game", "Bill and Ted's"};
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<String> unsorted;
    @FXML
    private TableView<String> sorted;
    @FXML
    private TableColumn<String, String> unsortedColumn;
    @FXML
    private TableColumn<String, String> sortedColumn;
    @FXML
    private static TextField textField;
    private Stage stage;
    private ComboBox combo;

    private Scene scene;
    private Parent root;


    @FXML

    private ChoiceBox choiceBox;

    public HelloController() throws UnirestException {
    }

    @FXML
    public void switchtoSort(ActionEvent event) throws IOException, UnirestException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //choiceBox = new ChoiceBox<>();
        ((HelloController) loader.getController()).printArrayUnsorted();
    }

    @FXML
    public void switchtoSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void test(ActionEvent event) throws IOException {

        System.out.println(textField.getText());

    }

    @FXML
    public void printArrayUnsorted() throws IOException {
        for (int i = 0; movieArray.length > i; i++) {
            movieList.add(movieArray[i]);
        }
        for (String movie : movieList) {
            unsorted.getItems().add(movie);
        }

    }

    @FXML
    public void printArraySorted(ActionEvent event) throws IOException {
        int selectIndex = choiceBox.getSelectionModel().getSelectedIndex();
        if (selectIndex == 0) {
            callMerge();
        }
        if (selectIndex == 1) {
            insertionSort();
        }
        if (selectIndex == 2) {
            selectionSort();
        }

        //for (int i = 0; movieArray.length > i; i++) {
        //    movieList.add(movieArray[i]);
        //}
//        for (String movie : movieList) {
//            sorted.getItems().add(movie);
//        }
        this.unsorted.getItems().clear();
        System.out.print(movieList);
        this.sorted.getItems().clear();

        String[] newUnsorted = new String[movieArray.length];
        Arrays.stream(movieArray).distinct().collect(Collectors.toList()).toArray(newUnsorted);

        this.sorted.getItems().addAll(newUnsorted);
        this.unsorted.getItems().addAll(movieList);
    }

    @FXML
    public void insertionSort() throws IOException {
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
    public void callMerge() throws IOException {
        mergeSort(movieArray, 0, movieArray.length);
    }

    @FXML
    public void selectionSort() throws IOException {
        int len = movieArray.length;
        for (int i = 0; i < len - 1; i++){
            int y = i;
            String tempString = movieArray[i];
            for(int x = i + 1; x < len; x ++) {
                if (movieArray[x].compareTo(tempString) < 0){
                    tempString = movieArray[x];
                    y = x;
                }
            }
            if(y != i){
                String temp = movieArray[y];
                movieArray[y] = movieArray[i];
                movieArray[i] = temp;
            }
        }

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
//        Scanner input = new Scanner(System.in);
//        System.out.println("What movies would you like to search for: ");
//        String i = input.next();
//        input.close();
        String i = "face";

        HttpResponse<String> response = Unirest.get("https://movie-database-alternative.p.rapidapi.com/?s=" + i + "&r=json&page=1")
                .header("X-RapidAPI-Key", "0da6c9c507msh27d66d057973f0ep13289ajsnc1cd4d8defc8")
                .header("X-RapidAPI-Host", "movie-database-alternative.p.rapidapi.com")
                .asString();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(response.getBody().toString());
        String clean = gson.toJson(element);

        HelloController parse = new HelloController();
        String[] data = parse.Find(clean);
        String[] title = new String[data.length];

        for (int z = 0; z < data.length; z++) {
            char[] temp = data[z].toCharArray();
            char[] arr = new char[data[z].length()];
            for (int y = 0; y < temp.length; y++) {
                if (temp[y] == 'T') {
                    while (temp[y] != ',') {
                        arr[y] = temp[y];
                        y++;

                    }
                    String name = String.valueOf(arr).trim();
                    title[z] = name;
                    System.out.println(title[z]);
                    break;

                }
            }
        }
        return title;
    }

    public String[] Find(String data) {

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
        for (int x = 1; x < len; x++) {
            //if data starts with "{" start to loop
            if (array[x] == '{') {
                x++;
                //if item within array isn't "}" or end of item continue to add arr[x] to item
                while (x < len && array[x] != '}') {
                    item[index] = array[x];
                    x++;
                    index++;
                }
                //Deleting null characters off of item
                String item2 = String.valueOf(item).trim();
                //placing item in item list
                placeList[placeholder] = item2;

                item = new char[len];
                index = 0;

                placeholder++;
            }
        }
        //Creating new String array
        String[] dataList = new String[placeholder];

        //removing null characters from string array
        for (int x = 0; x < placeholder; x++) {
            if (placeList[x] != null) {
                dataList[x] = placeList[x];
            }
        }
        //print items of list;
        for (int x = 0; x < placeholder; x++) {
            System.out.println(dataList[x]);
        }

        return dataList;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(this.unsorted);

        if (choiceBox != null) {
            choiceBox.setItems(sortList);
            choiceBox.setValue("sorts");
        }

        if (this.unsortedColumn != null) {
            this.unsortedColumn.setCellValueFactory(x -> new SimpleObjectProperty<>(x.getValue()));
            this.sortedColumn.setCellValueFactory(x -> new SimpleObjectProperty<>(x.getValue()));
        }

    }
}
