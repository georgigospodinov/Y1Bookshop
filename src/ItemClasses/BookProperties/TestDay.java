package ItemClasses.BookProperties;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applicable to the Day class (v1.2).
 * Covers all 15 methods and 76/79 lines (96%).
 * The thee lines that are not covered are
 * return statements in determining the day of the week.
 *
 * Note:
 * Methods are tested through the creation of Day objects.
 *
 * @see Day
 * @see Day#setDayOfWeek()
 *
 * @author anonymus
 * @version 1.1
 */
public class TestDay {

    Day d1;

    @Test
    public void testWithCorrectFormat () throws Exception {

        d1 = new Day (  );
        assertEquals ( "17 Nov 2015", d1.getDate () );

        d1 = new Day ( 20, "Nov", 2015 );
        assertEquals ( "20 Nov 2015", d1.getDate () );

        assertEquals ( "20 Nov 2015 (Friday)", d1.toString () );

        Day d2 = new Day ( "20 Nov 2015" );
        assertEquals ( "20 Nov 2015 (Friday)", d2.toString () );

        Day d3 = new Day ( "29 Feb 2005" );//not accepted. Creates a close date
        assertEquals ( "17 Feb 2005 (Thursday)", d3.toString () );

        Day d4 = new Day ( "29 Feb 2004" );//accepted, 2004 is a leap year
        assertEquals ( "29 Feb 2004 (Sunday)", d4.toString () );

        Day d5 = new Day ( "30 Feb 2004" );//not accepted. Creates a close date
        assertEquals ( "17 Feb 2004 (Tuesday)", d5.toString () );

        Day d6 = new Day ( 31, "Apr", 2015 );//not accepted. Creates a close date
        assertEquals ( "17 Apr 2015 (Friday)", d6.toString () );

    }

    @Test
    public void testWithWrongFormat () throws Exception {

        //Wrongly formatted days are created with default values

        d1 = new Day ( "29fj 04f ksdlfk03" );
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

        d1 = new Day ( "jkfjg 04930 kgfg" );
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

        d1 = new Day ( "2014 Apr 5" );
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

        d1 = new Day ( "May 4 2015" );
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

        d1 = new Day ( "4 Jul 2016" );//should be '04 Jul 2016'
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

        d1 = new Day ( "KO 03kf 04jf" );
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

        d1 = new Day ( -1, "sdf", -346 );
        assertEquals ( "17 Nov 2015 (Tuesday)", d1.toString () );

    }
}