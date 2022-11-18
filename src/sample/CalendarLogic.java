package sample;

import java.util.Calendar;

public class CalendarLogic
{
    Calendar date;

    public CalendarLogic()
    {
        this.date = Calendar.getInstance();
        date.setFirstDayOfWeek(Calendar.SUNDAY);
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

}
