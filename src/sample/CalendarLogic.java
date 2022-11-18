package sample;

import java.util.Calendar;

public class CalendarLogic
{
    Calendar date;

    public CalendarLogic()
    {
        this.date = Calendar.getInstance();
        System.out.println(date.get(Calendar.HOUR));
    }

    public void setMonth(int month)
    {
        this.date.set(Calendar.MONTH, month);
        refreshTable();
    }

    public void setYear(int year)
    {
        this.date.set(Calendar.YEAR, year);
        refreshTable();
    }

    public void refreshTable()
    {
        System.out.println("refreshing calendar");
    }
}
