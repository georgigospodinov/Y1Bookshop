package PersonClasses;

import StoringClasses.Bookshop;
import StoringClasses.Order;
import ItemClasses.Frequent;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person.
 * Anyone should be able to buy Items from Bookshops.
 * Places Orders
 *
 * @see ItemClasses.Item
 * @see Order
 * @see Bookshop
 *
 * @author anonymus
 * @version 1.15
 */
public class Person implements Frequent {

    private static final double DEFAULT_AMOUNT_OF_MONEY = 200;//the money each person starts with

    private String name = NOT_FILLED;
    private String physicalAddress = NOT_FILLED;
    private String email = NOT_FILLED;
    private String contactTelephoneNumber = NOT_FILLED;
    private double money = DEFAULT_AMOUNT_OF_MONEY;
    /**
     * Because the List of orders is private, this field is provided,
     * so that the number of orders is viewable but not amendable.
     *
     * @see Person#orders
     */
    private int currentNumberOfOrders = 0;
    /**
     * Each person can make multiple orders.
     */
    private List<Order> orders = new ArrayList<> (  );

    public void setName ( String name ) {

        if ( !Frequent.stringIsEmpty ( name ) ){

            //only two names should be entered
            //more than one ' ' (space) means that there are more names than 2
            int spaceCount = 0;

            int i;
            for ( i = 0; i < name.length (); i++ ) {

                if ( name.charAt ( i ) == ' ' )
                    spaceCount ++;

                if ( spaceCount > 1 )
                    break;

                //a name should consist only of lower and UPPER case letters
                //and/or dashes(-) and/or hyphens(') and/or spaces
                if ( ( name.charAt ( i ) < 'a' || 'z' < name.charAt ( i ) ) && //character is not [a-z]
                        ( name.charAt ( i ) < 'A' || 'Z' < name.charAt ( i ) ) && //character is not [A-Z]
                        name.charAt ( i ) != '-' && name.charAt ( i ) != '\'' //character is not - or '
                        && name.charAt ( i ) != ' ' )//or space
                    break;

            }

            if ( spaceCount != 1 )
                System.out.println ("Incorrect name format, illegal number of spaces/names.\n" +
                        "Please, enter two names separated by a space.");

            else if ( i < name.length () )
                System.out.println ("Illegal characters in name. Legal ones are:\n" +
                        "small and UPPER case letters, dashes and hyphens.");

            else this.name = name;

        }

        else System.out.println ("You cannot set an empty name.");

    }
    public String getName () {

        return this.name;

    }

    public void setPhysicalAddress ( String physicalAddress ) {

        if ( !Frequent.stringIsEmpty ( physicalAddress ))
            this.physicalAddress = physicalAddress;

        else System.out.println ("You cannot set an empty physical address.");

    }
    public String getPhysicalAddress () {

        return this.physicalAddress;

    }

    public void setEmail ( String email ) {

        if ( Frequent.stringIsEmpty ( email ) )
            System.out.println ("You cannot set an empty email.");

        else {
            //an email must have exactly one '@' and after that at least one period ('.') (before the end)

            int indexOfMonkey = 0;
            int monkeyCounter = 0;
            for ( int i = 0; i < email.length (); i++ )
                if ( email.charAt ( i ) == '@' ) {

                    indexOfMonkey = i;
                    monkeyCounter ++;

                    if ( monkeyCounter > 1 )
                        break;

                }

            if ( monkeyCounter != 1 )
                System.out.println ("Invalid email address, incorrect number of monkey signs.");

            else {

                int i;
                for ( i = indexOfMonkey+1; i < email.length (); i++ )
                    if ( email.charAt ( i ) == '.' )
                        break;

                if ( i < email.length ()-1 )
                    this.email = email;

            }

        }
    }
    public String getEmail () {

        return this.email;

    }

    public void setContactTelephoneNumber ( String contactTelephoneNumber ) {

        if ( Frequent.stringIsEmpty ( contactTelephoneNumber ) )
            System.out.println ("You cannot set an empty telephone number.");

        else {

            if ( contactTelephoneNumber.length () == 11 ) {

                if ( contactTelephoneNumber.charAt ( 0 ) == '0' && contactTelephoneNumber.charAt ( 1 ) == '7' )
                    this.contactTelephoneNumber = contactTelephoneNumber;

                else System.out.println ("This is not a valid UK telephone number.");

            }

            else System.out.println ("This is not a valid UK telephone number.");

        }

    }
    public String getContactTelephoneNumber () {

        return this.contactTelephoneNumber;

    }

    public void setMoney ( double money ) {

        if ( money >= 0 )
            this.money = money;

        else System.out.println ("A person cannot have a negative amount of money.");

    }
    public double getMoney () {

        return this.money;

    }

    public int getCurrentNumberOfOrders () {

        return this.currentNumberOfOrders;

    }
    /**
     * An empty order can be added because it will at first always be created empty.
     * (E.G. you visit an online shop and it says that your shopping basket is empty)
     *
     * @param order - the order (usually a just created one)
     *
     * @see Order#Order(Bookshop, Person)
     */
    public void addOrder ( Order order ) {

        orders.add ( order );
        currentNumberOfOrders ++;

    }
    public void removeOrder ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfOrders() ) ) {

            orders.remove ( index );
            currentNumberOfOrders --;

        }

        else System.out.println ("There is no order at that index.");

    }
    public Order getOrderAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfOrders() ) )
            return orders.get ( index );

        else {

            System.out.println ("There is no order at that index.");
            return null;
        }

    }

    public Person ( String name ) {

        this.setName ( name );

    }
    public Person ( String name, String contactTelephoneNumber ) {

        this (name);
        this.setContactTelephoneNumber ( contactTelephoneNumber );

    }
    public Person ( String name, String physicalAddress, String email, String contactTelephoneNumber ) {

        this (name, contactTelephoneNumber);
        this.setPhysicalAddress(physicalAddress);
        this.setEmail (email);
    }

    @Override
    public String toString () {

        return "Person name: " + this.getName () + ", physicalAddress: " + this.getPhysicalAddress () + '\n' +
                "email: " + this.getEmail () + ", contactTelephoneNumber: " + this.getContactTelephoneNumber () + '\n';
    }

    @Override
    public boolean equals ( Object obj ) {

        boolean objIsNull = Frequent.objectIsEmpty ( obj );
        boolean thisIsNull = Frequent.objectIsEmpty ( this );

        if ( objIsNull && thisIsNull )
            return true;

        else if ( ( objIsNull && !thisIsNull ) || ( !objIsNull && thisIsNull) )
            return false;

        else if ( ! (obj instanceof Person) )
            return false;

        //two people are the same if they have the same name
        //and contact telephone number
        //and address
        else if ( !Frequent.stringsAreEqual ( this.getName (), ( ( Person ) obj ).getName () ) )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.getContactTelephoneNumber (),
                ( ( Person ) obj ).getContactTelephoneNumber () ) )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.getPhysicalAddress (),
                ( ( Person ) obj ).getPhysicalAddress ()))
            return false;

        else return true;

    }

}
