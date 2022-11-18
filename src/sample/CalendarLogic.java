package sample;

import javafx.event.ActionEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CalendarLogic
{
    Calendar date;
    HashMap<Calendar, String> meetings;

    public CalendarLogic()
    {
        this.date = Calendar.getInstance();
        date.setFirstDayOfWeek(Calendar.SUNDAY);
        meetings = new HashMap<Calendar, String>();
    }

    public void setMonth(int month)
    {
        this.date.set(Calendar.MONTH, month);
    }

    public void setYear(int year)
    {
        this.date.set(Calendar.YEAR, year);
    }

    public int getYear()
    {
        return this.date.get(Calendar.YEAR);
    }

    public int getMonth()
    {
        return (this.date.get(Calendar.MONTH));
    }

    public int getWeekOfMonth()
    {
        return this.date.get(Calendar.WEEK_OF_MONTH);
    }

    public int getDayOfWeek()
    {
        return this.date.get(Calendar.DAY_OF_WEEK);
    }

    public int getDayOfMonth()
    {
        return this.date.get(Calendar.DAY_OF_MONTH);
    }
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
        System.out.println("temp date is: " + tempC.get(Calendar.DAY_OF_MONTH) +   "." + (tempC.get(Calendar.MONTH)+1) + "." + tempC.get(Calendar.YEAR));
        System.out.println("first week day of the month is: " + tempC.get(Calendar.DAY_OF_WEEK));

        return (tempC.get(Calendar.DAY_OF_WEEK));
    }

    public void addMeeting(ActionEvent actionEvent)
    {
        int currentDay = checkDayNumber(actionEvent);

        if (currentDay != -1)
        {
            /*
            option 0 = Add meeting
            option 1 = close
             */
            String dayMeetings;
            String[] responses = {"Edit", "Close"};

            if (!(meetings.containsKey(date)))
            {
                meetings.put(date, "");
                dayMeetings = "";
            }
            else
            {
                dayMeetings = meetings.get(date);
            }
            int meetingSetOption =JOptionPane.showOptionDialog(null,  returnStringDate(currentDay) + "Meetings on this day:\n"
                    +  dayMeetings + "\n" + "Would you like to edit meetings?\n", "Calendar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    responses,
                    0);
            if (meetingSetOption == 0)
            {
                String appointment = JOptionPane.showInputDialog ("Enter the meeting information");
                meetings.put(date, appointment);
            }
        }
    }

    public int checkDayNumber(ActionEvent actionEvent)
    {
        String fullString = actionEvent.getSource().toString();
        int separatorIndex = fullString.indexOf("'") + 1 ;
        String stringNum = fullString.substring(separatorIndex, fullString.length()-1);

        /*
        If the pressed button is empty we will return error message
         */
        if (stringNum.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Wrong date pressed - Please choose valid day", "Wrong day", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        //int pressedDay = Integer.parseInt(actionEvent.getSource().toString());
        return Integer.parseInt(stringNum);
    }

    public String returnStringDate(int day)
    {
        return ("Date: " + day + "." + this.date.get(Calendar.MONTH) + "." + this.date.get(Calendar.YEAR) + "\n");
    }

}
