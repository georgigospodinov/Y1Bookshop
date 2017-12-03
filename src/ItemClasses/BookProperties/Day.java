package ItemClasses.BookProperties;

import ItemClasses.Frequent;

/**
 * Class meant for Book's release day.
 *
 * After an object of class Day is created,
 * its attributes can only be read but not edited.
 * This is to avoid creating a valid date and than
 * setting the month to February while the dayOfMonth is still 30. (i.e.)
 *
 * A better functionality to solve this problem can be implemented
 * in Day v2.0
 *
 * @see Day#dayOfMonth
 * @see Day#month
 * @see Day#year
 * @see Day#Day(int, String, int)
 * @see Day#Day(String)
 *
 * @see ItemClasses.Book
 *
 * @author anonymus
 * @version 1.2
 */
public class Day {

    //default Day value is 17 Nov 2015 (Tuesday)
    /**
     * Date in format: DD Mmm YYYY
     * example: 17 Nov 2015
     *
     * @see Day#setDate()
     */
    private String date = "17 Nov 2015";
    /**
     * This field is determined by the object,
     * depending on the rest of the fields.
     *
     * @see Day#setDayOfWeek()
     */
    private String dayOfWeek = "Tuesday";
    private int dayOfMonth = 17;//1-31
    private Month month = Month.NOVEMBER;
    private int year = 2015;//1-2100 (limit to year 2100)

    private boolean isLeapYear () {

        //years divisible by 400 are leap years
        //otherwise years divisible by 100 are NOT leap years
        //otherwise years divisible by 4 are leap years
        return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));

    }

    private void setDayOfMonth ( int dayOfMonth ) {

        if ( dayOfMonth < 1 || dayOfMonth > 31 )
            System.out.println ("Entered dayOfMonth is not valid. Range is [1-31].");

        else if ( dayOfMonth == 31 && !month.has31Days () )
            System.out.println ("The month does not have 31 days.");

        else if ( month == Month.FEBRUARY && dayOfMonth == 30 )
            System.out.println ("February does not have so many days.");

        else if ( month == Month.FEBRUARY && dayOfMonth == 29 && !isLeapYear () )
            System.out.println ("This year is not a leap year (Feb has 28 days).");

        else this.dayOfMonth = dayOfMonth;

    }
    public int getDayOfMonth () {
        return this.dayOfMonth;
    }

    private void setMonth ( String month ) {

        this.month = Month.stringToMonth ( month );

    }
    public String getMonth () {

        return this.month.monthToString ();

    }

    private void setYear ( int year ) {

        if ( year < 1 || year > 2100 )
            System.out.println ("Year value is invalid.");

        else this.year = year;

    }
    public int getYear () {
        return year;
    }

    private void setDate () {

        this.date = getDayOfMonth () + " " + getMonth () + " " + getYear ();

    }
    public String getDate () {

        return this.date;

    }

    /**
     * This method uses the year, month and dayOfMonth
     * to determine the day of the week.
     * The algorithm used is taken from the week 4 tutorial.
     * https://studres.cs.st-andrews.ac.uk/CS1002/Tutorials/W04/W04-Tutorial.pdf
     *
     * @see Day#year
     * @see Day#month
     * @see Day#dayOfMonth
     */
    private void setDayOfWeek () {

        //This algorithm is taken from the week 4 tutorial

        int a, b, c;

        if ( month.monthToInteger () < 3 ) {

            a = month.monthToInteger () + 10;
            b = (getYear ()-1)%100;
            c = (getYear ()-1)/100;

        }

        else {

            a = month.monthToInteger ()-2;
            b = getYear ()%100;
            c = getYear ()/100;

        }

        int w = ( 700 + (((26*a)-2)/10) + getDayOfMonth () + b + b/4 + c/4 - (2*c))%7;

        switch ( w ) {

            case 0: dayOfWeek = "Sunday"; break;
            case 1: dayOfWeek = "Monday"; break;
            case 2: dayOfWeek = "Tuesday"; break;
            case 3: dayOfWeek = "Wednesday"; break;
            case 4: dayOfWeek = "Thursday"; break;
            case 5: dayOfWeek = "Friday"; break;
            case 6: dayOfWeek = "Saturday"; break;

        }

    }
    public String getDayOfWeek () {
        return this.dayOfWeek;
    }

    @Override
    public String toString () {

        return getDate () + " (" + getDayOfWeek () + ")";

    }

    /**
     * The empty default constructor is provided mainly for testing purposes.
     * With it a Day can quickly be instantiated with the default values.
     */
    public Day (){}
    public Day ( int dayOfMonth, String month, int year ) {

        this();
        this.setYear ( year );
        this.setMonth ( month );
        this.setDayOfMonth ( dayOfMonth );
        this.setDate ();
        this.setDayOfWeek ();

    }
    /**
     * @param date should be in correct format
     * @see Day#date
     */
    public Day ( String date ) {

        this();

        if ( Frequent.stringIsEmpty (date) || date.length () < 11 ) {

            System.out.println ("Not enough characters to set date.");
            System.out.println ("Default date has been created.");
            return;

        }

        int dayOfMonth;
        String month = date.substring ( 3, 6 );
        int year;

        try {
            dayOfMonth = Integer.parseInt ( date.substring ( 0, 2 ) );
        }
        catch ( NumberFormatException nfe ) {

            System.out.println ("The first two characters must be a number.");
            System.out.println ("Default date has been initialised.");
            return;

        }

        try {
            year = Integer.parseInt ( date.substring ( 7, 11 ) );
        }
        catch ( NumberFormatException nfe ) {

            System.out.println ("The seventh through tenth characters must be a number.");
            System.out.println ("Default date has been initialised.");
            return;

        }

        this.setYear ( year );
        this.setMonth ( month );
        this.setDayOfMonth ( dayOfMonth );
        this.setDate ();
        this.setDayOfWeek ();

    }

}
