package StoringClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.BookFormat;
import ItemClasses.Frequent;
import ItemClasses.Item;
import PersonClasses.Person;

/**
 * Represents an Order for Items.
 * One Person can have many orders.
 * Each order is associated with one Bookshop and one client.
 *
 * @see Item
 * @see Inventory
 * @see Bookshop
 *
 * @author anonymus
 * @version 1.3
 */
public class Order extends Inventory {

    private boolean confirmed = false;
    private boolean canceled = false;
    private boolean processed = false;
    /**
     * This is the bookshop from where the order is to come.
     * An order should always have a bookshop.
     *
     * @see Order#Order(Bookshop, Person)
     * @see Bookshop
     */
    private Bookshop bookshop;
    /**
     * This is the person who places the order.
     * An order should always have a placer/client.
     *
     * @see Order#Order(Bookshop, Person)
     * @see Person
     */
    private Person client;

    public void confirm () {

        if ( this.getClient ().getMoney () >= this.calculateTotalOrderCost () ) {

            this.confirmed = true;
            this.canceled = false;

        }

        else System.out.println ("This person does not have enough money to make the order.");

    }
    public boolean isConfirmed () {
        return this.confirmed;
    }

    public void cancel () {

        if ( !this.isProcessed () ) {

            this.canceled = true;
            this.confirmed = false;

            System.out.println
            (this.getClient ().getName () +" canceled the order!");

        }

        else System.out.println ("Sorry, " + this.getClient ().getName ()
                + "'s order is already processed.");

    }
    public boolean isCanceled () {
        return this.canceled;
    }

    public void process () {

        this.processed = true;
        this.getClient ().setMoney ( this.getClient ().getMoney () - this.calculateTotalOrderCost () );

    }
    public boolean isProcessed () {
        return this.processed;
    }

    public Bookshop getBookshop () {

        return this.bookshop;

    }
    public Person getClient () {

        return this.client;

    }

    /**
     * Lists the Items that can be added to the order.
     * @return the list of Items in the bookshop
     *
     * @see Bookshop#listAllItems()
     */
    public String listPossibleItems () {

        if ( Frequent.objectIsEmpty ( this.getClient () ) ||
                Frequent.objectIsEmpty ( this.bookshop ) )
            return "The order is not valid.\n" +
                    "Please, create a new order.";

        else return this.getClient ().getName () + " can order from "
                + bookshop.listAllItems ();

    }

    /**
     * Adds an item from the bookshop's stock.
     * It checks to see if there is an Item to add.
     * Adds one using super.addItem(Item)
     *
     * @param indexInBookshopStock - from the bookshop's unique item indexing.
     *
     * @see Bookshop#items
     * @see Bookshop#listAllItems()
     * @see Inventory#addItem(Item)
     * @see Order#addItem(Item)
     */
    public void addItem ( int indexInBookshopStock ) {

        if ( Frequent.objectIsEmpty ( this.bookshop ) )
            System.out.println ("The order is not associated with a bookshop.\n" +
                    "Please, create a new order.");

        else {

            if ( !Frequent.indexIsWithinRange ( indexInBookshopStock, bookshop.getCurrentNumberOfItems () ) )
                System.out.println ( "There is no item at that index." );

            else {

                Item itemToAdd = this.getBookshop ().getItemAt ( indexInBookshopStock );

                if ( itemToAdd.getQuantityAvailable () == 0 )
                    System.out.println ( "There are no copies of this item left." );

                else if ( this.hasItem ( itemToAdd ) )
                    System.out.println ( "This item has already been added to the order." );

                else {

                    super.addItem ( itemToAdd );
                    //If an item is added to a confirmed order, it is no longer confirmed
                    confirmed = false;

                }

            }
        }

    }

    /**
     * This method is used to calculate the total order.
     * The amount returned by this method is subtracted from the total cost of the items in the order
     * to determine whether a delivery charge must be applied.
     *
     * @return cost of electronic books in order
     * @see Order#calculateTotalOrderCost()
     */
    private double calculateCostOfElectronicBooks () {

        double cost = 0;

        for ( int i = 0; i < this.getCurrentNumberOfItems (); i++ ) {

            Item myItem = this.getItemAt ( i );

            if ( myItem instanceof Book )
                if ( ( ( Book ) myItem ).getFormat ().equals ( BookFormat.ELECTRONIC.bookFormatToString () ) )
                    cost += myItem.getPrice ();

        }

        return cost;

    }
    /**
     * @return the total cost of the order (includes delivery charge if needed)
     * @see Bookshop#deliveryCharge
     */
    public double calculateTotalOrderCost () {

        double totalAmount = this.calculateTotalItemCost ();
        double costOfNonElectronicItems =  totalAmount - calculateCostOfElectronicBooks ();

        if ( costOfNonElectronicItems < bookshop.getOrderCostThreshold () )
            totalAmount += bookshop.getDeliveryCharge ();

        return totalAmount;

    }

    /**
     * An order should always be associated with a bookshop.
     * @param bookshop where the order is made
     * @param client who places the order
     *
     * @see Order#bookshop
     * @see Bookshop
     */
    public Order ( Bookshop bookshop, Person client ) {

        //orders need a bookshop and a client
        if ( Frequent.objectIsEmpty ( bookshop ) || Frequent.objectIsEmpty ( client ) )
            return;

        this.bookshop = bookshop;
        //once the order is created it is automatically added to the respective bookshop's list of orders
        bookshop.addOrder ( this );

        this.client = client;
        //and to the respective person's list of orders
        client.addOrder ( this );

    }

    @Override
    public String toString () {

        if ( Frequent.objectIsEmpty ( bookshop ) ||
                Frequent.objectIsEmpty ( client ) )
            return "invalid order";

        String s = this.getClient ().getName () + " - ";
        if ( this.getCurrentNumberOfItems () == 0 )
            return s + "No items have been added to the order";

        s += " Order: ";

        for ( int i = 0; i < this.getCurrentNumberOfItems ()-1; i++ )
            s += this.getItemAt ( i ).getName () + ", ";

        s += this.getItemAt ( this.getCurrentNumberOfItems ()-1 ).getName () + "\n";
        s += "total item worth: " + this.calculateTotalItemCost () + "\n";
        s += "total with delivery charge: " + this.calculateTotalOrderCost () + "\n";

        return s;
    }

    @Override
    public boolean equals ( Object obj ) {

        if ( !super.equals ( obj ) )
            return false;

        else if ( !(obj instanceof Order ) )
            return false;

        //two orders are the same if they have the same bookshop and same Person
        else if ( !this.getClient ().equals ( ( ( Order ) obj ).getClient () ) )
            return false;

        else if ( !this.getBookshop ().equals ( ( ( Order ) obj ).getBookshop () ) )
            return false;

        else return true;

    }

    /**
     * Items should be added to orders from their respective bookshop.
     * This method only displays a notice to the user.
     *
     * @see Order#addItem(int)
     */
    @Override
    public void addItem ( Item item ) {

        System.out.println ("Sorry, this functionality is disabled.\n" +
                "Please try Order.addItem(indexInBookshop)");
    }
}
