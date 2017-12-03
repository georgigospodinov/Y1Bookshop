package ItemClasses.BookProperties;

/**
 * The three possible book formats.
 *
 * @see ItemClasses.Book#format
 *
 * @author anonymus
 * @version 1.0
 */
public enum BookFormat {

    PAPERBACK, HARDBACK, ELECTRONIC;

    /**
     * @param bookFormat string that is translated to enum value
     * @return the enum value
     */
    public static BookFormat stringToBookFormat ( String bookFormat ) {

        try {
            switch ( bookFormat ) {

                case "paper":
                case "PAPER":
                case "Paper":
                case "paperback":
                case "PAPERBACK":
                case "Paperback":
                    return PAPERBACK;

                case "hard":
                case "HARD":
                case "Hard":
                case "hardback":
                case "HARDBACK":
                case "Hardback":
                    return HARDBACK;

                case "electronic":
                case "Electronic":
                case "ELECTRONIC":
                    return ELECTRONIC;

                default: return null;

            }
        }
        catch ( NullPointerException npe ) {
            return null;
        }

    }

    /**
     * @return string representing THIS enum value
     */
    public String bookFormatToString () {

        switch ( this ) {

            case PAPERBACK: return "paperback";
            case HARDBACK: return "hardback";
            case ELECTRONIC: return "electronic";

            default: return "something went wrong (ItemClasses.BookProperties.BookFormat#bookFormatToString)";

        }

    }
}
