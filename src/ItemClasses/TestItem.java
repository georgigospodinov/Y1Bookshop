package ItemClasses;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Applicable to the Item class (v1.2).
 * Covers all 16 methods, 53/60 lines (88%).
 * Six of the lines that are not covered are
 * 'return false' statements after a condition (if).
 * All conditions are tested and needed for a realistic situation.
 *
 * The last one is a return statement in getType.
 * That statement is never reached.
 * But it is need so that IntelliJ does not complain.
 *
 * NOTE:
 * The number in the comment after each @Test annotation
 * denotes the Item id(s) used in the method.
 * (Applies if all Tests are ran at the same time)
 *
 * @see Item
 * @see Item#getType()
 *
 * @author anonymus
 * @version 1.1
 */
public class TestItem implements Frequent {

    @Test//1
    public void testName () throws Exception {

        Item item1 = new Item (  );
        item1.setName ( null );
        assertEquals ( NOT_FILLED, item1.getName () );

        String name = "nice book";
        item1.setName ( name );
        assertEquals ( name, item1.getName () );

        //changing 'name' does not change 'item.name'
        name = "not so nice book";
        assertEquals ( "nice book", item1.getName () );

        item1 = new Item (  );
        item1.setName ( "" );
        assertEquals ( NOT_FILLED, item1.getName () );

    }

    @Test//2
    public void testQuantity () throws Exception {

        Item item2 = new Item (  );
        item2.setQuantityAvailable ( -15 );
        assertEquals ( DEFAULT_QUANTITY, item2.getQuantityAvailable () );

        item2.setQuantityAvailable ( 10 );
        assertEquals ( 10, item2.getQuantityAvailable () );
        item2.setQuantityAvailable ( item2.getQuantityAvailable ()-3 );
        assertEquals ( 7, item2.getQuantityAvailable () );

    }

    @Test//3
    public void testPrice () throws Exception {

        Item item3 = new Item (  );
        item3.setPrice ( -14.6 );//not acceptable
        assertEquals ( DEFAULT_PRICE, item3.getPrice (), DEFAULT_PRICE);

        item3.setPrice ( 50.0 );
        assertEquals ( 50.0, item3.getPrice (), DEFAULT_PRICE );

    }

    @Test//4 ...&5
    public void general () throws Exception {

        Item item4 = new Item ( IS_BOOK, "history" );

        System.out.println ( item4.getStockInfo () );
        System.out.println ( item4.getFullDetails () );

        Item item5 = new Item ( IS_STATIONARY, "napoleon's journal" );
        System.out.println ( item5.getFullDetails ());

    }

    @Test//6&7
    public void overriddenMethods () throws Exception {

        Item item6 = new Item ( IS_BOOK, "Equals & toString" );
        Item item7 = new Item ( IS_BOOK, "Equals & toString" );
        System.out.println (item6);//tests 'toString()'
        assertTrue ( item6.equals ( item7 ) );

    }

}