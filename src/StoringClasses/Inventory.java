package StoringClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Frequent;
import ItemClasses.Item;
import ItemClasses.Stationary;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of any storing class.
 * Filled with Items. It is expected that only inheritors of that class are entered.
 *
 * This class should never be instantiated.
 * It is concrete, not abstract for testing purposes ONLY.
 *
 * @see TestInventory
 * @see Item
 * @see Bookshop
 * @see Order
 *
 * @author anonymus
 * @version 1.2
 */
public class Inventory {

    private List <Item> items = new ArrayList<> ();
    /**
     * Because the List of items is private, this field is provided,
     * so that the number of items in the Inventory is viewable but not amendable.
     *
     * @see Inventory#items
     */
    private int currentNumberOfItems = 0;

    public int getCurrentNumberOfItems () {

        return this.currentNumberOfItems;

    }

    /**
     * It is expected that only existing Items (Books or Stationaries) are added.
     * @param item an existing one
     */
    public void addItem ( Item item ) {

        if ( Frequent.objectIsEmpty ( item ) )
            System.out.println ("You cannot add an empty item.");

        else if ( this.hasItem ( item ) ) {

            System.out.println ("This item is already added. Quantity increased!");

            int i;
            for ( i = 0; i < this.getCurrentNumberOfItems (); i++ )
                if ( this.getItemAt ( i ).equals ( item ) )
                    break;

            this.getItemAt ( i ).setQuantityAvailable ( this.getItemAt ( i ).getQuantityAvailable ()+1 );

        }

        else {

            this.items.add ( item );
            currentNumberOfItems ++;

        }

    }
    public boolean hasItem ( Item item ) {

        if ( Frequent.objectIsEmpty ( item ) ) {

            System.out.println ("You cannot search for empty items.");
            return false;

        }

        boolean bookEquivalent = false;

        if ( item instanceof Book )
            for ( int i = 0; i < this.getCurrentNumberOfItems (); i++ )
                if ( this.getItemAt ( i ) instanceof Book ) {

                    bookEquivalent = item.equals ( this.getItemAt ( i ) );

                    if ( bookEquivalent )
                        return true;

                }

        if ( item instanceof Stationary )
            for ( int i = 0; i < this.getCurrentNumberOfItems (); i++ )
                if ( this.getItemAt ( i ) instanceof Stationary ) {

                    bookEquivalent = item.equals ( this.getItemAt ( i ) );

                    if ( bookEquivalent )
                        return true;

                }

        return false;

    }
    public void removeItem ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, getCurrentNumberOfItems() ) ) {

            this.items.remove ( index );
            currentNumberOfItems --;

        }

        else System.out.println ("No item at that index.");

    }

    //these two methods might be re-written
    public String itemToString ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, getCurrentNumberOfItems () ) ) {

            Item myItem = items.get ( index );
            return myItem.toString ();

        }

        else return "invalid index (Inventory#itemToString)";

    }
    public Item getItemAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, getCurrentNumberOfItems () ) )
            return items.get ( index );

        else {

            System.out.println ( "Invalid index." );
            return null;

        }

    }

    //could not find a way to avoid duplication
    public String listAllItems () {

        String list = "List of all items:\n";

        for ( int i = 0; i < getCurrentNumberOfItems (); i++ )
            list += "Index: " + i + " Info: " + this.items.get ( i ).getStockInfo () +"\n";

        return list;

    }
    public String listFullItemDetails () {

        String s = "List of Items with FULL details:\n";

        for ( int i = 0; i < this.getCurrentNumberOfItems (); i++ )
            s += this.getItemAt ( i ).getFullDetails () + "\n";

        return s;

    }
    public String listBooks () {

        String list = "List of books only:\n";

        for ( int i = 0; i < getCurrentNumberOfItems (); i++ )
            if ( this.items.get ( i ) instanceof Book )
                list += "Index: " + i + " Info: " + this.items.get ( i ).getStockInfo () +"\n";

        return list;

    }
    public String listStationaries () {

        String list = "List of stationaries only:\n";

        for ( int i = 0; i < getCurrentNumberOfItems (); i++ )
            if ( this.items.get ( i ) instanceof Stationary )
                list += "Index: " + i + " Info: " + this.items.get ( i ).getStockInfo () +"\n";

        return list;

    }
    public String listBooksInGenre ( Genre genre ) {

        if ( Frequent.objectIsEmpty ( genre ) )
            return "Invalid genre";

        String list = "List of " + genre.genreToString () + " books:\n";

        for ( int i = 0; i < getCurrentNumberOfItems (); i++ )
            if ( this.items.get ( i ) instanceof Book )
                if ( ( ( Book ) this.items.get ( i ) ).hasGenre ( genre ) )
                    list += "Index: " + i + " Info: " + this.items.get ( i ).getStockInfo () +"\n";

        return list;

    }

    public double calculateTotalItemCost () {

        double totalCost = 0;

        for ( int i = 0; i < getCurrentNumberOfItems (); i++ )
            totalCost += this.items.get ( i ).getPrice ();

        return totalCost;

    }

    @Override
    public boolean equals ( Object obj ) {

        boolean objIsNull = Frequent.objectIsEmpty ( obj );
        boolean thisIsNull = Frequent.objectIsEmpty ( this );

        if ( objIsNull && thisIsNull )
            return true;

        else if ( ( objIsNull && !thisIsNull ) || ( !objIsNull && thisIsNull) )
            return false;

        else if ( ! (obj instanceof Inventory) )
            return false;

        //two inventories are the same if they have the same items

        if ( this.getCurrentNumberOfItems () != ( ( Inventory ) obj ).getCurrentNumberOfItems () )
            return false;

        int numberOfItems = this.getCurrentNumberOfItems ();

        for ( int i = 0; i < numberOfItems; i++ )
            //if this does NOT have an Item in obj
            if ( ( !( this.hasItem ( ( ( Inventory ) obj ).getItemAt ( i ) ) ) )
                    //or obj does NOT have an Item in this
                    || ( !( ( ( Inventory ) obj ).hasItem ( this.getItemAt ( i ) ) ) ) )
                return false;

        return true;

    }

}
