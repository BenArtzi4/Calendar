package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    final int MAX_WEEKS = 6;
    final int INITIAL_YEAR = 1990;
    final int FINAL_YEAR = 2050;
    final int BUTTON_SIZE = 70;

    int currentMonth;
    int currentYear;

    @FXML
    private Label dateLabel;

    @FXML
    void setMonthPressed(ActionEvent event) {

    }

    @FXML
    private Label yearLabel;

    @FXML
    private Label monthLabel;



    @FXML
    void setYearPressed(ActionEvent event) {

    }

    Button  [][] dayBtns = new Button[MAX_WEEKS][DAYS_IN_WEEK];

    CalendarLogic calendar = new CalendarLogic();

    public void initialize()
    {
        initializeYearsList();
        initializeMonthsList();
        monthList.getItems().addAll(months);
        yearList.getItems().addAll(years);
        initializeDays();
        updateLabel();




        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentYear =  yearList.getSelectionModel().getSelectedItem();
                calendar.setYear(currentYear);
                updateDays();
                updateLabel();
            }
        });

        /*
        substract 1 in month because calender months start with 0 to 5 (May) will be in index 4 (but still May)
         */
        monthList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentMonth =  monthList.getSelectionModel().getSelectedItem();
                calendar.setMonth(currentMonth - 1);
                updateDays();
                updateLabel();
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
        updateDays();
    }

    public void initializeButtons()
    {
        for (int i = 0; i < dayBtns.length ; i++ )
        {
            for (int j = 0; j < dayBtns[0].length ; j++)
            {
                dayBtns[i][j] = new Button();
                dayBtns[i][j].setPrefSize (100,100);
                dayBtns[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        calendar.addMeeting(actionEvent);
                    }
                });
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
     substract 1 because day number 1 should be in index 0 in the table
     */
    public void updateDays()
    {
        removeDaysFromTable();
        printStatus();
        int row = 0;
        int column = (calendar.getFirstNumberOfDayInMonth()-1);
        for (int i = 1 ; i <= calendar.getNumberOfDays() ; i++)
        {
            dayBtns[row][column].setText(i + "");
            dayBtns[row][column].setDisable(false);
            column ++;
            if (column == DAYS_IN_WEEK)
            {
                column = 0;
                row++;
            }
        }

        for (Button[] dayBtn : dayBtns) {
            for (int j = 0; j < dayBtns[0].length; j++) {
                if (dayBtn[j].getText().equals("")) {
                    dayBtn[j].setDisable(true);
                }
            }
        }
    }

    /*
    Remove the number of days from the table before update it
     */
    public void removeDaysFromTable()
    {
        for (int i = 0; i < dayBtns.length ; i++ )
        {
            for (int j = 0; j < dayBtns[0].length ; j++)
            {
                dayBtns[i][j].setText("");
            }
        }
    }

    public void updateLabel()
    {
        dateLabel.setText("Month: " + (calendar.getMonth()+1) + "    Year: " + calendar.getYear());
    }

    public void printStatus()
    {
        System.out.println(
                "The month is: " + (calendar.getMonth()+1) +
                        "\nThe year is: " + calendar.getYear() +
                "\nThe date is: " + (calendar.getMonth()+1) + "." + calendar.getYear()
                // + "\n the first day week on this month is: " + calendar.getFirstNumberOfDayInMonth()

        );
    }
}
