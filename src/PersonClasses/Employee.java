package PersonClasses;

import ItemClasses.Frequent;
import StoringClasses.Bookshop;

/**
 * Represent an Employee in a Bookshop.
 * Inherits Person because anyone should be able to buy items from a bookshop.
 * It must always be associated with a workplace.
 *
 * @see Person
 * @see Bookshop
 *
 * @author anonymus
 * @version 1.1
 */
public class Employee extends Person {

    /**
     * Each employee has its own unique id, independent of the Bookshop where they work.
     *
     * @see Bookshop#employees
     * @see Employee#nextID
     * @see Employee#getNextID()
     */
    private int id;
    /**
     * The last ID given to an Employee.
     * The method getNextID is used when assigning ID.
     *
     * @see Employee#getNextID()
     * @see Employee#id
     */
    private static int nextID = 0;
    private double monthlySalary = 250;//default monthly salary
    private Bookshop workPlace;
    private int amountOfSalesMade = 0;

    public int getId () {

        return this.id;

    }
    private static int getNextID () {

        nextID ++;
        return nextID;

    }

    /**
     * Used when listing Employees.
     * @return id + name
     */
    public String getIdentity () {

        return "ID#: " + this.getId() + " Name: " + this.getName ();

    }

    public void setMonthlySalary ( double monthlySalary ) {

        if ( monthlySalary > 0 )
            this.monthlySalary = monthlySalary;

        else System.out.println ("The monthly salary must be positive.");

    }
    public double getMonthlySalary () {

        return this.monthlySalary;

    }

    public void setWorkPlace ( Bookshop workPlace ) {

        if ( Frequent.objectIsEmpty ( workPlace ) )
            System.out.println ( "You cannot set an empty Bookshop as the employee's workplace." );

        else if ( workPlace.equals ( this.workPlace ) )
            System.out.println ("This employee is already associated with that bookshop.");

        else {

            //remove employee from old work place
            Bookshop oldWorkPlace = this.workPlace;

            if ( !Frequent.objectIsEmpty ( oldWorkPlace ) )
            for ( int i = 0; i < oldWorkPlace.getCurrentNumberOfEmployees (); i++ )
                if ( oldWorkPlace.getEmployeeAt ( i ).equals ( this ) ) {

                    oldWorkPlace.removeEmployee ( i );
                    break;

                }

            this.workPlace = workPlace;
            workPlace.addEmployee ( this );
            //this amount is reset when an employee goes to a new working place
            this.amountOfSalesMade = 0;
        }

    }
    public Bookshop getWorkPlace () {

        return this.workPlace;

    }

    public int getAmountOfSalesMade () {

        return this.amountOfSalesMade;

    }

    /**
     * This method is called when the employee processes an order in the bookshop.
     *
     * @param amount which he is paid
     *
     * @see StoringClasses.Bookshop#processOneOrder(int)
     * @see Employee#money
     */
    public void receiveCommission ( double amount ) {

        if ( amount < 0 )
            System.out.println ("You cannot give an employee negative commission.");

        else {

            this.setMoney ( this.getMoney () + amount );
            amountOfSalesMade ++;
        }

    }

    public Employee ( Bookshop workPlace, String name ) {

        super ( name );
        this.id = getNextID ();
        this.setWorkPlace ( workPlace );

    }
    public Employee ( Bookshop workPlace, String name, double monthlySalary ) {

        this (workPlace, name);
        this.setMonthlySalary ( monthlySalary );

    }

    /**
     * @return more details than person.toString()
     *
     * @see Person#toString()
     */
    @Override
    public String toString () {

        return "ID: " + this.getId () + " " + super.toString () +
                "Works at: \"" + this.workPlace.getName () + "\"\n";

    }
}
