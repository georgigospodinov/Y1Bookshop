package StoringClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Item;
import ItemClasses.Stationary;
import PersonClasses.Author;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applicable to the Inventory class (v1.2).
 * Covers all 13 methods, 87/92 lines (94%).
 * The five lines that are not covered are
 * 'return false' statements after a condition (if).
 * All conditions are tested and needed for a realistic situation.
 *
 * @see Inventory
 *
 * @author anonyms
 * @version 1.1
 */
public class TestInventory {

    Inventory inventory1, inventory2;
    Author author = new Author ( "" );

    @Before//adds two items to the inventory
    public void setUp () throws Exception {

        inventory1 = new Inventory ();
        inventory2 = new Inventory ();

        //create and add a book
        Book book = new Book ( author, "Joe Rose", "paper" );
        book.addGenre ( "fantasy" );
        inventory1.addItem ( book );
        inventory2.addItem ( book );

        //create and add a stationary
        Stationary stationary = new Stationary (  );
        inventory1.addItem ( stationary );
        inventory2.addItem ( stationary );

    }

    @Test
    public void testGetItem () throws Exception {

        Book book = new Book ( author, "Joe Rose", "paper" );
        book.addGenre ( "fantasy" );
        assertEquals ( book, inventory1.getItemAt ( 0 ) );

        //invalid indexes
        assertNull ( inventory1.getItemAt ( -5 ) );
        assertNull ( inventory1.getItemAt ( 50 ) );

    }

    @Test
    public void general () throws Exception {

        System.out.println (inventory1.itemToString ( 0 ));
        System.out.println ("\n\n\n");
        System.out.println (inventory1.itemToString ( 1 ));
        System.out.println ("\n\n\n");
        //invalid indexes
        System.out.println (inventory1.itemToString ( 50 ));
        System.out.println (inventory1.itemToString ( -53 ));
        System.out.println ("\n\n");

        assertTrue ( inventory1.equals ( inventory2 ) );
        assertFalse ( inventory1.hasItem ( null ) );
        inventory2.addItem ( null );//prints out impossible

    }

    @Test
    public void testRemoveItem () throws Exception {

        inventory1.removeItem ( 0 );
        assertEquals ( new Stationary (  ), inventory1.getItemAt ( 0 ) );

        //ignored indexes
        inventory1.removeItem ( -1 );
        inventory1.removeItem ( 50 );

    }

    @Test
    public void testLists () throws Exception {

        Author author = new Author ( "" );

        assertEquals ( 10.95*2, inventory1.calculateTotalItemCost (), 10);
        inventory1.addItem ( new Book ( author ) );
        inventory1.addItem ( new Stationary (  ) );
        inventory1.addItem ( new Stationary (  ) );
        inventory1.addItem ( new Book ( author ) );
        inventory1.addItem ( new Book ( author ) );
        inventory1.addItem ( new Book ( author ) );

        System.out.println (inventory1.listAllItems ());
        System.out.println (inventory1.listFullItemDetails ());
        System.out.println (inventory1.listBooks ());
        System.out.println (inventory1.listStationaries ());

        Item myItem = inventory1.getItemAt ( 0 );
        if ( myItem instanceof Book )
            ( ( Book ) myItem ).addGenre ( "fantasy" );

        System.out.println (inventory1.listBooksInGenre ( Genre.FANTASY ));
        //the next line should have 0 output
        System.out.println (inventory1.listBooksInGenre ( Genre.stringToGenre ( "dsfk" ) ));

    }

}