package StoringClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Frequent;
import ItemClasses.Stationary;
import PersonClasses.Author;
import PersonClasses.Employee;
import PersonClasses.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applies to the Bookshop class (v1.4).
 * Covers all 45 methods and 173/182 lines (95%).
 * The nine lines that are not covered are
 * 'return' statements after conditions(if).
 * All conditions are tested and needed for a realistic situation.
 *
 * As Bookshops and Orders are closely connected,
 * more testing of Bookshop can be seen in TestOrder.
 *
 * @see Bookshop
 * @see TestOrder
 *
 * @author anonymus
 * @version 2.3
 */
public class TestBookshop {

    Bookshop bookshop;
    String name;

    @Before
    public void setUp () throws Exception {

        name = "books-quickly";
        bookshop = new Bookshop ( name );
        bookshop = new Bookshop ( name, 2, 15 );

    }

    @Test
    public void testDeliveryCharge () throws Exception {

        //delivery charge is set in the constructor
        assertEquals ( 2, bookshop.getDeliveryCharge (), 2 );
        bookshop.setDeliveryCharge ( -1 );//not accepted
        assertEquals ( 2, bookshop.getDeliveryCharge (), 2 );

    }

    @Test
    public void testOrderCostThreshold () throws Exception {

        assertEquals ( 15, bookshop.getOrderCostThreshold (), 15 );
        bookshop.setOrderCostThreshold ( -5 );//not accepted
        assertEquals ( 15, bookshop.getOrderCostThreshold (), 15 );

    }

    @Test
    public void testEmployeeMethods () throws Exception {

        //create some employees, they are automatically associated with the bookshop
        Employee jack = new Employee ( bookshop, "Jack Dan", 50 );
        Employee john = new Employee ( bookshop, "John Smith", 50 );
        System.out.println (bookshop.listEmployees ());
        assertEquals ( 100, bookshop.calculateTotalAmountPayableToEmployees (), 100 );

        //remove an employee
        bookshop.removeEmployee ( 0 );//should delete the employee or sth
        assertEquals ( 1, bookshop.getCurrentNumberOfEmployees () );

    }

    @Test
    public void testAuthorAndEmployeeCommission () throws Exception {

        assertEquals ( 0.15, bookshop.getAuthorCommission (), 0.15 );
        bookshop.setAuthorCommission ( -0.54 );//not accepted
        assertEquals ( 0.15, bookshop.getAuthorCommission (), 0.15 );
        bookshop.setAuthorCommission ( 1.4 );//not accepted
        assertEquals ( 0.15, bookshop.getAuthorCommission (), 0.15 );
        bookshop.setAuthorCommission ( 0.2 );//accepted
        assertEquals ( 0.2, bookshop.getAuthorCommission (), 0.2 );

        bookshop.setEmployeeCommission ( -23 );//not accepted
        assertEquals ( 0.1, bookshop.getEmployeeCommission (), 0.1 );
        bookshop.setEmployeeCommission ( 1.5 );//not accepted
        assertEquals ( 0.1, bookshop.getEmployeeCommission (), 0.1 );
        bookshop.setEmployeeCommission ( 0.09 );//accepted
        assertEquals ( 0.09, bookshop.getEmployeeCommission (), 0.09 );

    }

    @Test
    public void testName () throws Exception {

        bookshop.setName ( "THE bookSHOP" );
        bookshop.setName ( "" );//ignored
        bookshop.setName ( null );//ignored
        assertEquals ( "THE bookSHOP", bookshop.getName () );

    }

    @Test
    public void testShopURL () throws Exception {

        assertEquals ( name, bookshop.getName () );

        String url = name + ".com";
        bookshop.setShopURL ( url );
        assertEquals ( url, bookshop.getShopURL () );

        bookshop.setShopURL ( name + "com" );//this is not accepted
        assertEquals ( url, bookshop.getShopURL () );

    }

    @Test
    public void testGeneral () throws Exception {

        System.out.println (bookshop);
        Bookshop bookshop2 = new Bookshop ( "Quick Delivery", bookshop.getShopURL (), 20, 1 );

        assertTrue ( bookshop2.equals ( bookshop ) );

    }

    @Test
    public void testOrders () throws Exception {

        //For the purpose of this large testing a new bookshop is created
        Bookshop b = new Bookshop ( "Bookshop for testing orders" );
        Employee e1 = new Employee ( b, "Employee One" );
        Employee e2 = new Employee ( b, "Employee Two" );

        Author a = new Author ( "AuthorFor TestingOrders" );
        Book book1 = new Book ( a, "Book 1" );
        Book book2 = new Book ( a, "Book 2" );
        Stationary stationary1 = new Stationary ( "notebook", "Red notebook" );
        Stationary stationary2 = new Stationary ( "diary", "Yellow diary" );

        //indexes in b:
        b.addItem ( book1 );//0
        b.addItem ( book2 );//1
        b.addItem ( stationary1 );//2
        b.addItem ( stationary2 );//3


        //some people and orders
        Person p1 = new Person ( "Person One" );
        Person p2 = new Person ( "Person Two" );
        Order o1 = new Order ( b, p1 );
        Order o2 = new Order ( b, p2 );

        //p1 orders the two books
        o1.addItem ( 0 );
        o1.addItem ( 1 );

        //p2 orders the two stationaries
        o2.addItem ( 2 );
        o2.addItem ( 3 );


        //show all orders
        System.out.println (b.listAllOrders ());

        //confirm one order, cancel the other,
        //than process to see that only the first has been processed
        o1.confirm ();
        o2.cancel ();
        b.processOrders ();
        System.out.println (b.listSalesMade ());
        //check to see who bought stationaries and who bought books
        System.out.println (b.listCustomersWhoBought ( book1 ));//one person
        System.out.println (b.listCustomersWhoBought ( stationary1 ));//no one


        //b has two employees: e1 and e2
        //one order has been processed -> one employee has made a sale
        System.out.println (b.listEmployeesWhoseSalesAreAtLeast ( 1 ));
        b.passOneMonth ();

    }

    @Test
    public void testProfitAndPaying () throws Exception {

        //For the purpose of this large testing a new bookshop is created
        Bookshop b = new Bookshop ( "Bookshop for testing money in bookshop" );
        Employee e1 = new Employee ( b, "Employee One" );
        Employee e2 = new Employee ( b, "Employee Two" );

        //loop adds items
        final int numberOfOrders = 10;
        for ( int i = 0; i < 10*numberOfOrders; i++ ) {

            Stationary stationary = new Stationary ( "diary", "Dairy "+i );
            b.addItem ( stationary );

        }

        //loop adds orders
        Order[] orders = new Order[numberOfOrders];
        Person[] clients = new Person[numberOfOrders];

        for ( int i = 0; i < numberOfOrders; i++ ) {

            clients[i] = new Person ( "John Snow" );
            //each client has enough money to pay for his order
            clients[i].setMoney ( 10000 );
            //the telephone number here is used to identify individual clients
            orders[i] = new Order ( b, clients[i] );

            //each order gets 10 items
            for ( int j = 10*(i+1) -1 ; j >= 10*i; j-- )
                orders[ i ].addItem ( j );

            //confirm the order
            orders[i].confirm ();

        }

        b.processOrders ();
        //10 items per order
        //10 percent of the money goes to employee commission (no authors here)
        double profitShouldBe = Frequent.DEFAULT_PRICE*numberOfOrders*10*0.9;
        //this sout statement is for
        //showing the difference before and after paying salaries
        System.out.println ("Current profits: " + b.getProfit ());
        assertEquals ( profitShouldBe, b.getProfit (), 10.95 );

        //profit should be enough to pay employees
        b.passOneMonth ();
        System.out.println ("Current profits: " + b.getProfit ());

    }

    @Test
    public void testOverriddenLists () throws Exception {

        Author author = new Author ( "Test Author" );
        Book book1 = new Book ( author, "Book One" );
        book1.addGenre ( "fantasy" );
        Book book2 = new Book ( author, "Book Two" );
        book2.addGenre ( "fiction" );
        Stationary stationary1 = new Stationary ( "notebook", "Green notebook" );
        Stationary stationary2 = new Stationary ( "diary", "Purple diary" );

        bookshop.addItem ( book1 );
        bookshop.addItem ( book2 );
        bookshop.addItem ( stationary1 );
        bookshop.addItem ( stationary2 );

        System.out.println (bookshop.listAllItems ());
        System.out.println (bookshop.listFullItemDetails ());
        System.out.println (bookshop.listBooks ());
        System.out.println (bookshop.listStationaries ());
        System.out.println (bookshop.listBooksInGenre ( Genre.FANTASY ));

    }

}