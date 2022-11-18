package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

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

    final int DAYS_IN_WEEK = 7;
    final int MAx_WEEKS = 6;
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

    Button  [][] dayBtns = new Button[MAx_WEEKS][DAYS_IN_WEEK];

    CalendarLogic calendar = new CalendarLogic();

    public void initialize()
    {
        initializeYearsList();
        initializeMonthsList();
        monthList.getItems().addAll(months);
        yearList.getItems().addAll(years);
        initializeDays();
        updateDays();

        System.out.println("first WEEK-day of th month:");
        System.out.println(calendar.getFirstNumberOfDayInMonth());
        calendar.getYear();
        calendar.getMonth();
        calendar.getWeekOfMonth();
        calendar.getDayOfWeek();
        calendar.getDayOfMonth();



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
        initializeButtons();
        addButtonsToGridPane();

    }

    public void initializeButtons()
    {
        for (int i = 0; i < dayBtns.length ; i++ )
        {
            for (int j = 0; j < dayBtns[0].length ; j++)
            {
                dayBtns[i][j] = new Button();
                dayBtns[i][j].setPrefSize (100,100);
            }
        }
    }

    public void addButtonsToGridPane()
    {
        for (int i = 0; i < dayBtns.length ; i++ )
        {
            for (int j = 0; j < dayBtns[0].length ; j++)
            {
                days.add(dayBtns[i][j], j, i);
            }
        }
    }

    /*
    Update the number of days on the calendar table
     */
    public void updateDays()
    {
        int row = 0;
        int column = calendar.getFirstNumberOfDayInMonth();

        for (int i = 1 ; i <= calendar.getNumberOfDays() ; i++)
        {
            dayBtns[row][column].setText(i + "");
            column ++;
            if (column == DAYS_IN_WEEK)
            {
                column = 0;
                row++;
            }
        }
    }
}
