package ItemClasses.BookProperties;

/**
 * The months for class Day.
 *
 * @see Day#month
 *
 * @author anonymus
 * @version 1.0
 */
public enum Month {

    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    /**
     * @param month string that is translated to enum value
     * @return the enum value
     */
    public static Month stringToMonth ( String month ) {

        switch ( month ) {

            case "january":
            case "January":
            case "JANUARY":
            case "jan":
            case "Jan":
            case "JAN":
                return JANUARY;

            case "february":
            case "February":
            case "FEBRUARY":
            case "feb":
            case "Feb":
            case "FEB":
                return FEBRUARY;

            case "march":
            case "March":
            case "MARCH":
            case "mar":
            case "Mar":
            case "MAR":
                return MARCH;

            case "april":
            case "April":
            case "APRIL":
            case "apr":
            case "Apr":
            case "APR":
                return APRIL;

            case "may":
            case "May":
            case "MAY":
                return MAY;

            case "june":
            case "June":
            case "JUNE":
            case "jun":
            case "Jun":
            case "JUN":
                return JUNE;

            case "july":
            case "July":
            case "JULY":
            case "jul":
            case "Jul":
            case "JUL":
                return JULY;

            case "august":
            case "August":
            case "AUGUST":
            case "aug":
            case "Aug":
            case "AUG":
                return AUGUST;

            case "september":
            case "September":
            case "SEPTEMBER":
            case "sep":
            case "Sep":
            case "SEP":
                return SEPTEMBER;

            case "october":
            case "October":
            case "OCTOBER":
            case "oct":
            case "Oct":
            case "OCT":
                return OCTOBER;

            case "november":
            case "November":
            case "NOVEMBER":
            case "nov":
            case "Nov":
            case "NOV":
                return NOVEMBER;

            case "december":
            case "December":
            case "DECEMBER":
            case "dec":
            case "Dec":
            case "DEC":
                return DECEMBER;

            default:
                System.out.println ("Invalid month. Value set to NOVEMBER.");
                return NOVEMBER;

        }

    }

    /**
     * @return string representing THIS enum value
     */
    public String monthToString () {

        switch ( this ) {

            case JANUARY: return "Jan";
            case FEBRUARY: return "Feb";
            case MARCH: return "Mar";
            case APRIL: return "Apr";
            case MAY: return "May";
            case JUNE: return "Jun";
            case JULY: return "Jul";
            case AUGUST: return "Aug";
            case SEPTEMBER: return "Sep";
            case OCTOBER: return "Oct";
            case NOVEMBER: return "Nov";
            case DECEMBER: return "Dec";

            //this indicates that the program is failing
            default: return "some other error (ItemClasses.BookProperties.Month#monthToStirng)";

        }

    }

    /**
     * Integer value of each month.
     * Used in the formula that determines the day of the week
     * @return 1-12 for Jan-Dec
     */
    public int monthToInteger () {

        switch ( this ) {

            case JANUARY: return 1;
            case FEBRUARY: return 2;
            case MARCH: return 3;
            case APRIL: return 4;
            case MAY: return 5;
            case JUNE: return 6;
            case JULY: return 7;
            case AUGUST: return 8;
            case SEPTEMBER: return 9;
            case OCTOBER: return 10;
            case NOVEMBER: return 11;
            case DECEMBER: return 12;

            default: return -10;//means an error has occurred
        }

    }

    /**
     * @return true if the month HAS 31 days, false otherwise
     */
    protected boolean has31Days () {

        switch ( this ) {

            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                return true;

            default: return false;

        }

    }
}
