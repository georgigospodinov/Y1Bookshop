package PersonClasses;

import ItemClasses.Frequent;
import StoringClasses.Bookshop;
import StoringClasses.Order;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applicable to the Person Class (v1.15).
 * Covers all 19 methods and 102/108 lines (94%).
 * The six lines that are not covered are
 * 'return false' statements after if conditions.
 * All conditions are tested and needed for a realistic situation.
 *
 * @see Person
 * @see Person#equals(Object)
 *
 * @author anonymus
 * @version 1.1
 */
public class TestPerson {

    Person person;
    String telNum = "07856954752";
    String email = "jsmith@mail.com";
    String address = "13 Pivot drive";
    String name = "John Smith";

    @Before
    public void setUp () throws Exception {

        person = new Person ( name, address, email, telNum );

    }

    @Test
    public void testName () throws Exception {

        person.setName ( "" );//not accepted
        assertTrue ( Frequent.stringsAreEqual ( name, person.getName () ) );

        person.setName ( "sdlkfl" );//not accepted
        assertTrue ( Frequent.stringsAreEqual ( name, person.getName () ) );

        person.setName ( "John" );//Two names, at least
        assertTrue ( Frequent.stringsAreEqual ( name, person.getName () ) );

        person.setName ( "John 13" );//numbers not allowed
        assertTrue ( Frequent.stringsAreEqual ( name, person.getName () ) );

        person.setName ( "John Kohn Smith" );//too much spaces
        assertTrue ( Frequent.stringsAreEqual ( name, person.getName () ) );

        person.setName ( "Ben Ten" );//accepted
        assertTrue ( Frequent.stringsAreEqual ( "Ben Ten", person.getName () ) );

    }

    @Test
    public void testPhysicalAddress () throws Exception {

        assertTrue ( Frequent.stringsAreEqual
                ( address, person.getPhysicalAddress () ) );

        person.setPhysicalAddress ( "This can be pretty much anything" );
        assertTrue ( Frequent.stringsAreEqual
                ( "This can be pretty much anything", person.getPhysicalAddress ()) );

        person.setPhysicalAddress ( "" );//but not an empty string
        assertTrue ( Frequent.stringsAreEqual
                ( "This can be pretty much anything", person.getPhysicalAddress ()) );

    }

    @Test
    public void testEmail () throws Exception {

        //must have @ and .
        person.setEmail ( "johnSmith" );
        assertTrue ( Frequent.stringsAreEqual ( email, person.getEmail () ) );

        person.setEmail ( "" );
        assertTrue ( Frequent.stringsAreEqual ( email, person.getEmail () ) );

        person.setEmail ( "johnS@mail" );
        assertTrue ( Frequent.stringsAreEqual ( email, person.getEmail () ) );

        person.setEmail ( "js@mail@com" );
        assertTrue ( Frequent.stringsAreEqual ( email, person.getEmail () ) );

        person.setEmail ( "johnsmith@mail.com" );
        assertTrue ( Frequent.stringsAreEqual
                ( "johnsmith@mail.com", person.getEmail () ) );

    }

    @Test
    public void testContactTelephoneNumber () throws Exception {

        assertTrue ( Frequent.stringsAreEqual
                ( telNum, person.getContactTelephoneNumber () ) );

        //must be numbers
        person.setContactTelephoneNumber ( "" );
        assertTrue ( Frequent.stringsAreEqual
                ( telNum, person.getContactTelephoneNumber () ) );

        //has 11 digits
        person.setContactTelephoneNumber ( "9576656898" );
        assertTrue ( Frequent.stringsAreEqual
                ( telNum, person.getContactTelephoneNumber () ) );

        //start with 07
        person.setContactTelephoneNumber ( "65576654689" );
        assertTrue ( Frequent.stringsAreEqual
                ( telNum, person.getContactTelephoneNumber () ) );

        person.setContactTelephoneNumber ( "07985426587" );
        assertTrue ( Frequent.stringsAreEqual
                ( "07985426587", person.getContactTelephoneNumber () ) );

    }

    @Test
    public void testMoney () throws Exception {

        person.setMoney ( -14 );//not accepted
        assertEquals ( 200, person.getMoney (), 200 );

        person.setMoney ( 435 );
        assertEquals ( 435, person.getMoney (), 435 );

    }

    @Test
    public void testToString () throws Exception {

        System.out.println (person);

    }

    @Test
    public void testEquals () throws Exception {

        Person person2 = new Person ( "John Smith", address, email, telNum );
        assertTrue ( person2.equals ( person ) );

    }

    @Test
    public void testOrders () throws Exception {

        //creating new orders automatically adds them to the person's list of orders
        Bookshop b1 = new Bookshop ( "Bookshop 1" );
        Bookshop b2 = new Bookshop ( "Bookshop 2" );
        Order o1 = new Order ( b1, person );
        Order o2 = new Order ( b2, person );
        Order o3 = new Order ( b2, person );

        assertEquals ( 3, person.getCurrentNumberOfOrders () );
        assertTrue ( o2.equals ( person.getOrderAt ( 1 ) ) );
        person.removeOrder ( 1 );
        assertTrue ( o3.equals ( person.getOrderAt ( 1 ) ) );

        //invalid indexes
        person.removeOrder ( 10 );
        assertEquals ( null, person.getOrderAt ( -1 ) );

    }

}