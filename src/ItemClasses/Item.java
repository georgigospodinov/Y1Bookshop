package ItemClasses;

/**
 * Represents an Item in an Inventory.
 *
 * @see Book
 * @see Stationary
 * @see StoringClasses.Inventory
 *
 * @author anonymus
 * @version 1.2
 */
public class Item implements Frequent {

    /**
     * Each Item has its own, unique code.
     * It doesn't matter in which bookshop it is.
     */
    private int code;
    /**
     * Used to provide the next available ID.
     * @see Item#getNextItemCode()
     */
    private static int nextItemCode = 0;
    private String name = NOT_FILLED;
    private int quantityAvailable = DEFAULT_QUANTITY;
    private double price = DEFAULT_PRICE;
    /**
     * This field is used to determine whether an item is book or stationary.
     * It might be removed in the future.
     *
     * @see Frequent#IS_BOOK
     * @see Frequent#IS_STATIONARY
     */
    private boolean type;

    /**
     * The item's unique code should be read-only after the item is created.
     * @return code
     * @see Item#code
     */
    public int getCode () {
        return this.code;
    }
    /**
     * @return the next available item code
     * @see Item#nextItemCode
     */
    public static int getNextItemCode () {

        nextItemCode++;
        return nextItemCode;

    }

    public void setName ( String name ) {

        //name should not be empty
        if ( !Frequent.stringIsEmpty ( name ) )
            this.name = name;

    }
    public String getName () {

        return this.name;

    }

    public void setQuantityAvailable ( int quantityAvailable ) {

        if ( quantityAvailable < 0 )
            System.out.println ("You cannot set a negative quantity.");

        else this.quantityAvailable = quantityAvailable;

    }
    public int getQuantityAvailable () {

        return this.quantityAvailable;

    }

    public void setPrice ( double price ) {

        if ( price <= 0 )
            System.out.println ("You cannot set a negative price.");

        else this.price = price;
    }
    public double getPrice () {

        return this.price;

    }

    /**
     * Converts the type from the initial boolean.
     * @return either 'book' or 'stationary'
     * @see Item#type
     */
    public String getType () {

        //if return type is String:
        if ( type == Frequent.IS_BOOK )
            return "book";

        else if ( type == Frequent.IS_STATIONARY )
            return "stationary";

        //this line is never reached.
        //IS_BOOK and IS_STATIONARY have opposite values
        else return "ERROR in ItemClasses.Item.getType()";

    }

    /**
     * by default only an item code is generated
     */
    public Item () {

        this.code = getNextItemCode ();

    }
    public Item ( boolean type, String name ) {

        this ();
        this.setName ( name );
        this.type = type;

    }

    /**
     * a bit of info for listing inventory
     * @return code, name, quantity available
     */
    public String getStockInfo () {

        String stockInfo = "";

        stockInfo += getCode () + "  ";//yes, two spaces
        stockInfo += getName () + "  ";//yes, two spaces
        stockInfo += "x" + getQuantityAvailable ();

        return stockInfo;

    }

    /**
     * This method is overridden and called as super.getFullDetails()
     * @return all the details of the item
     */
    public String getFullDetails () {

        String fullDetails = "";

        fullDetails += getCode () + "  ";//yes, two spaces
        fullDetails += getName () + " ("+getType ()+")  ";//yes, two spaces
        fullDetails += "costs " + getPrice () + "GBP  ";
        fullDetails += "in stock: " + getQuantityAvailable ();

        return fullDetails;
    }
    @Override
    public String toString () {

        return getCode() + "  " + getName () + "  " + getPrice ();

    }

    /**
     * Conditions which define whether two items are the same
     * @param obj that is compared to this
     * @return true if the objects are the same, false otherwise
     */
    @Override
    public boolean equals ( Object obj ) {

        boolean objIsNull = Frequent.objectIsEmpty ( obj );
        boolean thisIsNull = Frequent.objectIsEmpty ( this );
        if ( objIsNull && thisIsNull )
            return true;

        else if ( ( objIsNull && !thisIsNull ) || ( !objIsNull && thisIsNull ) )
            return false;

        else if ( !(obj instanceof Item ) )
            return false;

        //two items are the same if  they have the same name, price and type
        else if ( !Frequent.stringsAreEqual ( this.getName (), ( ( Item ) obj ).getName () ) )
            return false;

        else if ( this.getPrice () != ( ( Item ) obj ).getPrice () )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.getType (), ( ( Item ) obj ).getType () ) )
            return false;

        else return true;

    }

}