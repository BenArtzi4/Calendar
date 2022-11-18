package sample;

import javafx.event.ActionEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


/*
The logic of calendar with the option to set the month, year and day.
In addition, appointments can be added to the calendar, viewed and edited
 */
public class CalendarLogic
{
    // A calendar type object that represents a specific day
    Calendar date;

    // Generic object to save the meetings
    HashMap<Calendar, String> meetings;

    /*
    A constructor that initializes the day to be the current day
     */
    public CalendarLogic()
    {
        this.date = Calendar.getInstance();
        date.setFirstDayOfWeek(Calendar.SUNDAY);
        meetings = new HashMap<Calendar, String>();
    }

    /*
    Change the displayed year
     */
    public void setYear(int year)
    {
        this.date.set(Calendar.YEAR, year);
    }

    /*
    Change the displayed month
     */
    public void setMonth(int month)
    {
        this.date.set(Calendar.MONTH, month);
    }

    /*
    Get the displayed year
     */
    public int getYear()
    {
        return this.date.get(Calendar.YEAR);
    }

    /*
    Change the displayed month
     */
    public int getMonth()
    {
        return (this.date.get(Calendar.MONTH));
    }

    /*
    Get the number of days in the displayed month
     */
    public int getNumberOfDays()
    {
        return date.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /*
    Returns the week day of the first day in month.
    Example: the first day of 10.2022 is Tuesday so it will return 3
     Added one
     */
    public int getFirstNumberOfDayInMonth()
    {
        Calendar tempC = Calendar.getInstance();
        tempC.set(Calendar.YEAR, date.get(Calendar.YEAR));
        tempC.set(Calendar.MONTH, date.get(Calendar.MONTH));
        tempC.set(Calendar.DAY_OF_MONTH, 1);
        return (tempC.get(Calendar.DAY_OF_WEEK));
    }

    /*
    Adding or editing a meeting that takes place on the day you clicked
     */
    public void addMeeting(ActionEvent actionEvent)
    {

        //Returns the day number in the month of the selected day

        int currentDay = checkDayNumber(actionEvent);

        // Enter the day that is the current date
        date.set(Calendar.DAY_OF_MONTH, currentDay);

        // A variable containing the daily meeting details
        String dayMeetings;


        /*
        Checking whether a meeting has been scheduled on the chosen day
         */
        if (!(meetings.containsKey(date)))
        {
            meetings.put(date, "");
            dayMeetings = "";
        }
        else
        {
            dayMeetings = meetings.get(date);
        }

        // Showing the meetings to the user and giving the possibility to edit or add
        String[] responses = {"Edit", "Close"};
        int meetingSetOption =JOptionPane.showOptionDialog(null,  returnStringDate(currentDay) + "Meetings on this day:\n"
                +  dayMeetings + "\n" + "Would you like to edit meetings?\n", "Calendar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                responses,
                0);

        // Option to edit the meetings if the user chooses you
        if (meetingSetOption == 0)
        {
            String appointment = JOptionPane.showInputDialog ("Enter the meeting information");
            meetings.put(date, appointment);
        }

    }

    /*
    A method that returns the number of the day in the month of the selected day
     */
    public int checkDayNumber(ActionEvent actionEvent)
    {
        String fullString = actionEvent.getSource().toString();
        int separatorIndex = fullString.indexOf("'") + 1 ;
        String stringNum = fullString.substring(separatorIndex, fullString.length()-1);


        // If the pressed button is empty we will return error message
        if (stringNum.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Wrong date pressed - Please choose valid day", "Wrong day", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return Integer.parseInt(stringNum);
    }

    /*
    A method that returns as a string the month and year displayed to the user
     */
    public String returnStringDate(int day)
    {
        return ("Date: " + day + "." + this.date.get(Calendar.MONTH) + "." + this.date.get(Calendar.YEAR) + "\n");
    }
}
