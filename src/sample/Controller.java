package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller{

    @FXML
    private Canvas cnv;

    @FXML
    ListView<String> monthList = new ListView<String>();
    //ObservableList<String> months = FXCollections.observableArrayList (
         //   "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    @FXML
    private ListView<Integer> yearList = new ListView<Integer>();
    ObservableList<Integer> years = FXCollections.observableArrayList();

    @FXML
    private Button setMonth;

    @FXML
    private Button setYear;

    final int INITIAL_YEAR = 1990;
    final int FINAL_YEAR = 2050;
    String currentMonth;
    int currentYear;


    @FXML
    void setMonthPressed(ActionEvent event) {

    }

    @FXML
    void setYearPressed(ActionEvent event) {

    }

    public void initialize()
    {
        monthList.getItems().addAll(months);
        initializeYearsList();
        yearList.getItems().addAll(years);

        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                System.out.println("hello");
            }
        });
    }


        private void initializeYearsList()
    {
        for (int i = INITIAL_YEAR ; i <= FINAL_YEAR ; i++ )
        {
            years.add(i);
        }
    }
}
