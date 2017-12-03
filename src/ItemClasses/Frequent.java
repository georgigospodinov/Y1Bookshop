package ItemClasses;

/**
 * An interface with some common frequently used
 * constants and methods.
 * All methods here are executed when running
 * the Test classes.
 *
 * @author anonymus
 * @version 1.3
 */
public interface Frequent {

    boolean IS_BOOK = true;
    boolean IS_STATIONARY = false;

    String NOT_FILLED = "<not filled>";
    int DEFAULT_QUANTITY = 3;
    double DEFAULT_PRICE = 10.95;

    /**
     * @param index must be more than -1
     * @param upperLimit must be more than index
     * @return true if the conditions are satisfied, false otherwise
     */
    static boolean indexIsWithinRange ( int index, int upperLimit ) {

        //index is in range [0 - upperLimit)
        return ( index >= 0 && index < upperLimit );

    }

    /**
     * @param s that is checked for being empty
     * @return true if s is null or "", false otherwise
     */
    static boolean stringIsEmpty ( String s ) {

        boolean myBool = false;

        try {
            myBool = s.equals ( "" );
        }
        catch ( NullPointerException npe ) {
            return true;
        }

        return myBool;

    }

    /**
     * Compares s1 and s2 to see if they are the same.
     * This method covers cases that are not covered by String.equals()
     * @param s1 string 1 to compare
     * @param s2 string 2 to compare
     * @return true if they are, false otherwise
     */
    static boolean stringsAreEqual ( String s1, String s2 ) {

        //if both are empty -> true
        if ( stringIsEmpty ( s1 ) && stringIsEmpty ( s2 ) )
            return true;

        //else if both have values -> String.equals()
        else if ( !stringIsEmpty ( s1 ) && !stringIsEmpty ( s2 ))
            return s1.equals ( s2 );

        //else one of them is empty and the other one is not
        else return false;

    }

    /**
     * @param object that is checked for being null
     * @return true if it is
     */
    static boolean objectIsEmpty ( Object object ) {

        try {

            //if the object is empty, this throws an exception
            object.toString ();
        }
        catch ( NullPointerException npe ) {
            return true;
        }

        return false;
    }

}
