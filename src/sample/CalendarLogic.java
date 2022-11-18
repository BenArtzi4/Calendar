package sample;

import java.util.Calendar;

public class CalendarLogic
{
    Calendar date;

    public CalendarLogic()
    {
        this.date = Calendar.getInstance();
        date.setFirstDayOfWeek(Calendar.SUNDAY);
        System.out.println("created object\n" + "The year is: " + date.get(Calendar.YEAR)
        + "\n" + "The month is: " + (date.get(Calendar.MONTH)+1)
                        + "\n" + "The day is: " + date.get(Calendar.DAY_OF_MONTH)
                +"\n" + date.get(Calendar.DAY_OF_MONTH) + "." + (date.get(Calendar.MONTH)+1) + "." +date.get(Calendar.YEAR)
        );
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

    public int getYear()
    {
        System.out.println("year: " + this.date.get(Calendar.YEAR));
        return this.date.get(Calendar.YEAR);
    }

    public int getMonth()
    {
        System.out.println("month: " + (this.date.get(Calendar.MONTH)+1));
        return this.date.get(Calendar.MONTH);
    }

    public int getWeekOfMonth()
    {
        System.out.println("WeekOfMonth: " + this.date.get(Calendar.WEEK_OF_MONTH));
        return this.date.get(Calendar.WEEK_OF_MONTH);
    }

    public int getDayOfWeek()
    {
        System.out.println("DayOfWeek: " + this.date.get(Calendar.DAY_OF_WEEK));
        return this.date.get(Calendar.DAY_OF_WEEK);
    }

    public int getDayOfMonth()
    {
        System.out.println("DayOfMonth: " + this.date.get(Calendar.DAY_OF_MONTH));
        return this.date.get(Calendar.DAY_OF_MONTH);
    }

}
