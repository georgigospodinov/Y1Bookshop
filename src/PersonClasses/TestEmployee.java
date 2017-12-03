package PersonClasses;

import ItemClasses.Frequent;
import StoringClasses.Bookshop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

/**
 * Applicable to the Employee class (v1.1).
 * Covers all 13 methods and 42 lines.
 *
 * @see Employee
 *
 * @author anonymus
 * @version 1.1
 */
//correct order of tests (1,2,3)
//otherwise it sets employeeID to 2
@FixMethodOrder ( MethodSorters.NAME_ASCENDING)
public class TestEmployee {

    Bookshop bookshop = new Bookshop ( "Employee testing bookshop" );
    Employee employee = new Employee ( bookshop, "Mat Cauthon", 300 );

    @Test
    public void test1GetIdAndIdentity () throws Exception {

        System.out.println (employee.getId ());
        assertEquals ( 1, employee.getId () );

        String expected = "ID#: 1 Name: Mat Cauthon";
        String actual = employee.getIdentity ();

        assertTrue ( Frequent.stringsAreEqual ( expected, actual ) );
        System.out.println (employee);

    }

    @Test
    public void test2MonthlySalary () throws Exception {

        assertEquals ( 200, employee.getMonthlySalary (), 200 );

        //invalid salary is ignored
        employee.setMonthlySalary ( -34 );
        assertEquals ( 200, employee.getMonthlySalary (), 200 );

    }

    @Test
    public void test3WorkPlace () throws Exception {

        employee.setWorkPlace ( null );//not accepted

        //give employee some commission
        //change workplace, it is zero again

        employee.receiveCommission ( -23 );//fails as it should
        employee.receiveCommission ( 10 );
        employee.receiveCommission ( 20 );
        employee.receiveCommission ( 30 );

        assertEquals ( 3, employee.getAmountOfSalesMade () );

        Bookshop b = employee.getWorkPlace ();
        assertTrue ( b.equals ( bookshop ) );
        employee.setWorkPlace ( b );//same place

        b = new Bookshop ( "New bookshop", "newbooks.com" );
        employee.setWorkPlace ( b );//sales reset
        assertEquals ( 0, employee.getAmountOfSalesMade () );

    }
}