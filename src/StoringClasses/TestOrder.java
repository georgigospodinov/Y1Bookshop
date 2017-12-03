package StoringClasses;

import ItemClasses.Book;
import ItemClasses.Stationary;
import PersonClasses.Author;
import PersonClasses.Employee;
import PersonClasses.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applicable to the Order class (v1.3).
 * Covers all 16 methods and 79/84 lines (94%).
 * Four of the lines that are not covered are
 * 'return' statements after a condition (if).
 * All conditions are tested and needed for a realistic situation.
 *
 * As Bookshops and Orders are closely connected,
 * more testing of Order can be seen in TestBookshop.
 *
 * @see Order
 * @see Order#equals(Object)
 * @see Order#toString()
 * @see TestBookshop
 *
 * @author anonymus
 * @version 1.2
 */
public class TestOrder {

    Bookshop bookshop = new Bookshop ( "Bookshop" );
    Employee employee = new Employee ( bookshop, "John Snow" );
    Person client = new Person ( "Order Placer" );
    Order order = new Order ( bookshop, client );

    @Before
    public void setUp () throws Exception {

        Author author = new Author ( "Author One" );

        Book book1 = new Book ( author, "Book1" );
        Book book2 = new Book ( author, "Book2" );
        Book book3 = new Book ( author, "Book3" );
        Book book4 = new Book ( author, "Book4" );

        Stationary stationary1 = new Stationary ( "diary", "Stationary1" );
        Stationary stationary2 = new Stationary ( "diary", "Stationary2" );
        Stationary stationary3 = new Stationary ( "notebook", "Stationary3" );
        Stationary stationary4 = new Stationary ( "notebook", "Stationary4" );

        //indexes:
        bookshop.addItem ( book1 );//0
        bookshop.addItem ( book2 );//1
        bookshop.addItem ( book3 );//2
        bookshop.addItem ( book4 );//3
        bookshop.addItem ( stationary1 );//4
        bookshop.addItem ( stationary2 );//5
        bookshop.addItem ( stationary3 );//6
        bookshop.addItem ( stationary4 );//7

        order.addItem ( 0 );
        order.addItem ( 1 );
        order.addItem ( 2 );
        order.addItem ( 4 );
        order.addItem ( 5 );

    }

    @Test
    public void testConfirm () throws Exception {

        order.confirm ();
        assertTrue ( order.isConfirmed () );

    }

    @Test
    public void testCancel () throws Exception {

        order.cancel ();
        assertTrue ( order.isCanceled () );

    }

    @Test
    public void testProcess () throws Exception {

        order.confirm ();
        bookshop.processOrders ();
        assertTrue ( order.isProcessed () );

    }

    @Test
    public void testGetBookshop () throws Exception {

        assertTrue ( bookshop.equals ( order.getBookshop () ) );

    }

    @Test
    public void testGetClient () throws Exception {

        assertTrue ( client.equals ( order.getClient () ) );

    }

    @Test
    public void testListPossibleItems () throws Exception {

        System.out.println (order.listPossibleItems ());//shows the stock in bookshop
        System.out.println (order.listAllItems ());//shows the items in the order

    }

    @Test
    public void testCalculateTotalOrderCost () throws Exception {

        //each item created costs 10.95
        assertEquals ( 10.95*5, order.calculateTotalOrderCost (), 10.95 );

    }

    @Test
    public void testToString () throws Exception {

        System.out.println (order);

    }

    @Test
    public void testEquals () throws Exception {

        Order order1 = new Order ( bookshop, client );

        order1.addItem ( 0 );
        order1.addItem ( 1 );
        order1.addItem ( 2 );
        order1.addItem ( 4 );
        order1.addItem ( 5 );

        assertTrue ( order1.equals ( order ) );

    }

    @Test
    public void illegalValuesConstructor () throws Exception {

        Person p = new Person ( "Person O" );
        Order o = new Order ( null, p );
        //since one of the parameters is null
        //the order gets nulled and is not amendable
        System.out.println (o);
        System.out.println (o.getBookshop ());
        System.out.println (o.getClient ());

        o = new Order ( bookshop, null );
        System.out.println (o);
        System.out.println (o.getBookshop ());
        System.out.println (o.getClient ());

        o = new Order ( null, null );
        System.out.println (o);
        System.out.println (o.getBookshop ());
        System.out.println (o.getClient ());

        o.addItem ( 20 );
        System.out.println (o.listPossibleItems ());
        System.out.println ("\n\n\n");

        //legal definition, illegal addItem
        o = new Order ( bookshop, p );
        o.addItem ( 20 );
        o.addItem ( -20 );

    }

    @Test
    public void testAddItem () throws Exception {

        Author author = new Author ( "The Author" );
        Book eBook1 = new Book ( author, "Electronic 1", "Electronic" );
        Book eBook2 = new Book ( author, "Electronic 2", "Electronic" );
        Book eBook3 = new Book ( author, "Electronic 3", "Electronic" );

        bookshop.addItem ( eBook1 );//8
        bookshop.addItem ( eBook2 );//9
        bookshop.addItem ( eBook3 );//10

        order.addItem ( eBook1 );//fails as it should
        order.addItem ( 8 );
        order.addItem ( 9 );

        //remove the first four orders
        order.removeItem ( 0 );
        order.removeItem ( 0 );
        order.removeItem ( 0 );
        order.removeItem ( 0 );

        //at this point delivery charge is not waived
        assertNotEquals (order.calculateTotalItemCost (), order.calculateTotalOrderCost ());

        //add item with zero quantity
        Stationary diary = new Stationary ( "diary", "Blue diary" );
        diary.setQuantityAvailable ( 0 );
        bookshop.addItem ( diary );//11
        order.addItem ( 11 );//fails as it should

        //add an existing item
        order.addItem ( 8 );//second time, fails as it should

        //confirm an order without enough money
        client.setMoney ( 5 );
        order.confirm ();//fails as it should


        client.setMoney ( 1000 );
        //cancel a processed order
        order.confirm ();
        bookshop.processOrders ();
        assertNotEquals ( 1000, client.getMoney () );
        order.cancel ();//fails as it should

        //process a canceled order - should fail (for TestBookshop)!!!!!!!

    }
}