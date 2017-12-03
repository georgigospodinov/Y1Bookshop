package ItemClasses;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applicable to the Stationary class (v1.2).
 * Covers all methods, 23/26 lines (88%).
 * The three lines that are not covered are
 * 'return false' statements after a condition (if).
 * All conditions are tested and needed for a realistic situation.
 *
 * @see Stationary
 * @see Stationary#equals(Object)
 *
 * @author anonymus
 * @version 1.1
 */
public class TestStationary {

    Stationary stationary1;

    @Before
    public void setUp () throws Exception {

        stationary1 = new Stationary (  );

    }

    @Test
    public void testDescription () throws Exception {

        assertEquals ( Frequent.NOT_FILLED, stationary1.getStationaryType () );
        stationary1 = new Stationary ( "empty", "Nameless" );
        assertEquals (  "empty", stationary1.getStationaryType () );
        assertEquals ( "Stationary Item: empty",stationary1.getDetails () );

    }

    @Test
    public void testOverridden () throws Exception {

        Stationary stationary2 = new Stationary ( "" );
        System.out.println (stationary2);//test toString

        assertTrue ( stationary2.equals ( stationary1 ) );

        System.out.println (stationary1.getFullDetails ());

    }

}