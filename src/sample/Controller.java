package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class Controller{

    /*
    Generics variables containing the list of years and months
     */
    @FXML
    ListView<Integer> monthList = new ListView<Integer>();
    ObservableList<Integer> months = FXCollections.observableArrayList();


    @FXML
    ListView<Integer> yearList = new ListView<Integer>();
    ObservableList<Integer> years = FXCollections.observableArrayList();


    @FXML
    private GridPane days;

    /*
    Final variables for use later in the class and making the class clearer
     */
    final int DAYS_IN_WEEK = 7;
    final int MAX_WEEKS = 6;
    final int NUMBER_OF_MONTH = 12;
    final int INITIAL_YEAR = 1990;
    final int FINAL_YEAR = 2050;
    final int BUTTON_SIZE = 80;

    /*
    Variables that hold the current year and
     */
    int currentMonth;
    int currentYear;

    @FXML
    private Label dateLabel;

    // An array of all the buttons
    Button  [][] dayBtns = new Button[MAX_WEEKS][DAYS_IN_WEEK];

    // Creating a variable from a class of calendar logic
    CalendarLogic calendar = new CalendarLogic();

    /*
    Initialize the calendar and the required components accordingly
     */
    public void initialize()
    {
        initializeYearsList();
        initializeMonthsList();
        monthList.getItems().addAll(months);
        yearList.getItems().addAll(years);
        initializeDays();
        updateLabel();

        /*
        Adding actions that will be triggered when the year or month changes
         */
        yearList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentYear =  yearList.getSelectionModel().getSelectedItem();
                calendar.setYear(currentYear);
                updateDays();
                updateLabel();
            }
        });

        monthList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                currentMonth =  monthList.getSelectionModel().getSelectedItem();
                // Subtract 1 in month because calender months start with 0 to 5 (May) will be in index 4 (but still May)
                calendar.setMonth(currentMonth - 1);
                updateDays();
                updateLabel();
            }
        });
    }

    /*
    Initialize the possible years of the calendar
     */
    private void initializeYearsList()
    {
        for (int i = INITIAL_YEAR ; i <= FINAL_YEAR ; i++ )
        {
            years.add(i);
        }
    }

    /*
    Initialize the possible months of the calendar
     */
    private void initializeMonthsList()
    {
        for (int i = 1 ; i <= NUMBER_OF_MONTH ; i++ )
        {
            months.add(i);
        }
    }

    /*
    Initialization of the relevant days according to the displayed date
     */
    public void initializeDays()
    {
        initializeButtons();
        addButtonsToGridPane();
        updateDays();
    }

    /*
    Initialize a button representing the relevant days of the month
     */
    public void initializeButtons()
    {
        for (int i = 0; i < dayBtns.length ; i++ )
        {
            for (int j = 0; j < dayBtns[0].length ; j++)
            {
                dayBtns[i][j] = new Button();
                dayBtns[i][j].setPrefSize (BUTTON_SIZE,BUTTON_SIZE);
                dayBtns[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        calendar.addMeeting(actionEvent);
                    }
                });
            }
        }
    }

    /*
    Adding the buttons to display to the user
     */
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
        removeDaysFromTable();
        int row = 0;
        // Subtract 1 because day number 1 should be in index 0 in the table
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

        // Disable all the non relevant buttons
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
        for (Button[] dayBtn : dayBtns) {
            for (int j = 0; j < dayBtns[0].length; j++) {
                dayBtn[j].setText("");
            }
        }
    }

    /*
    Update the title of the displayed month and year
     */
    public void updateLabel()
    {
        dateLabel.setText("Month: " + (calendar.getMonth()+1) + "    Year: " + calendar.getYear());
    }
}
