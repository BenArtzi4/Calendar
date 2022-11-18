package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{

    @FXML
    private Canvas cnv;

    @FXML
    ListView<String> monthList = new ListView<String>();
    ObservableList<String> months = FXCollections.observableArrayList (
            "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @FXML
    private Button setMonth;

    @FXML
    private Button setYear;

    @FXML
    private TextField yearText;

    @FXML
    void setMonthPressed(ActionEvent event) {

    }

    @FXML
    void setYearPressed(ActionEvent event) {

    }

    public void initialize() {
        monthList.setItems(months);

    }
}
