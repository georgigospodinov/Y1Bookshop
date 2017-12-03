package ItemClasses;

/**
 * Represent a stationary item.
 * Inherits Item.
 *
 * @see Item
 * @see StoringClasses.Inventory
 *
 * @author anonymus
 * @version 1.2
 */
public class Stationary extends Item {

    private String stationaryType = NOT_FILLED;

    public void setStationaryType ( String stationaryType ) {

        if ( !Frequent.stringIsEmpty ( stationaryType ))
            this.stationaryType = stationaryType;

        else this.stationaryType = NOT_FILLED;

    }
    public String getStationaryType () {

        return this.stationaryType;

    }

    public String getDetails () {

        return "Stationary Item: " + getStationaryType ();

    }

    public Stationary () {

        super (IS_STATIONARY, NOT_FILLED);
        this.setStationaryType ( NOT_FILLED );

    }
    public Stationary (String stationaryType ) {

        this();
        this.setStationaryType ( stationaryType );

    }
    public Stationary ( String stationaryType, String name ) {

        this ( stationaryType );
        this.setName ( name );

    }

    /**
     * @return more details than item.getFullDetails()
     *
     * @see Item#getFullDetails()
     */
    @Override
    public String getFullDetails () {

        return super.getFullDetails () +
                "\nStationary type: " + this.getStationaryType () + "\n";
    }

    /**
     * @return more details than item.toString()
     *
     * @see Item#toString()
     */
    @Override
    public String toString () {

        return super.toString () + "\n" + getStationaryType ();

    }

    /**
     * Additional condition to those of item.equals(obj)
     * @param obj that is compared to this
     * @return true if the objects are the same, false otherwise
     */
    @Override
    public boolean equals ( Object obj ) {

        if ( !super.equals ( obj ) )
            return false;

        else if ( !(obj instanceof Stationary) )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.getStationaryType (), ( ( Stationary ) obj ).getStationaryType () ) )
            return false;

        else return true;
    }
}