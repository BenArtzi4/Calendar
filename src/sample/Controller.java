package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller{

    @FXML
    ListView<Integer> monthList = new ListView<Integer>();
    ObservableList<Integer> months = FXCollections.observableArrayList();


    @FXML
    private ListView<Integer> yearList = new ListView<Integer>();
    ObservableList<Integer> years = FXCollections.observableArrayList();

    @FXML
    private Button setMonth;

    @FXML
    private Button setYear;

    @FXML
    private GridPane days;

    final int INITIAL_YEAR = 1990;
    final int FINAL_YEAR = 2050;
    int currentMonth;
    int currentYear;


    @FXML
    void setMonthPressed(ActionEvent event) {

    }

    @FXML
    void setYearPressed(ActionEvent event) {

    }

    Button  [][] dayBtn = new Button[6][7];

    CalendarLogic calendar = new CalendarLogic();

    public void initialize()
    {
        initializeYearsList();
        initializeMonthsList();
        monthList.getItems().addAll(months);
        yearList.getItems().addAll(years);
        initializeDays();


        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentYear =  yearList.getSelectionModel().getSelectedItem();
                System.out.println(currentYear);
                calendar.setYear(currentYear);
            }
        });

        monthList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentMonth =  monthList.getSelectionModel().getSelectedItem();
                System.out.println(currentMonth);
                calendar.setMonth(currentMonth);
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

    private void initializeMonthsList()
    {
        for (int i = 1 ; i <= 12 ; i++ )
        {
            months.add(i);
        }
    }

    public void initializeDays()
    {
        for (int i = 0 ; i < dayBtn.length ; i++ )
        {
            for (int j = 0 ; j < dayBtn[0].length ; j++)
            {
                dayBtn[i][j] = new Button();
            }
        }
    }
}
