package ItemClasses.BookProperties;

/**
 * Possible genres a book might have.
 * More can be added if needed.
 *
 * @see ItemClasses.Book#genres
 *
 * @author anonymus
 * @version 1.0
 */
public enum Genre {

    FANTASY, FICTION, SCIENCE_FICTION, BIOGRAPHY, ROMANCE,
    MYSTERY, THRILLER, HISTORICAL, WESTERN, TRAVEL, MEMOIR;


    /**
     * @param genre string that is translated to enum value
     * @return the enum value
     */
    public static Genre stringToGenre ( String genre ) {

        switch ( genre ) {

            case "fantasy":
            case "FANTASY":
            case "Fantasy":
                return FANTASY;

            case "fiction":
            case "FICTION":
            case "Fiction":
                return FICTION;

            case "science fiction":
            case "SCIENCE FICTION":
            case "Science fiction":
                return SCIENCE_FICTION;

            case "biography":
            case "BIOGRAPHY":
            case "Biography":
                return BIOGRAPHY;

            case "romance":
            case "ROMANCE":
            case "Romance":
                return ROMANCE;

            case "mystery":
            case "MYSTERY":
            case "Mystery":
                return MYSTERY;

            case "thriller":
            case "THRILLER":
            case "Thriller":
                return THRILLER;

            case "historical":
            case "HISTORICAL":
            case "Historical":
                return HISTORICAL;

            case "western":
            case "WESTERN":
            case "Western":
                return WESTERN;

            case "travel":
            case "TRAVEL":
            case "Travel":
                return TRAVEL;

            case "memoir":
            case "MEMOIR":
            case "Memoir":
                return MEMOIR;

            default:
                return null;

        }

    }

    /**
     * @return string representing THIS enum value
     */
    public String genreToString () {

        switch ( this ) {

            case FANTASY: return "fantasy";
            case FICTION: return "fiction";
            case SCIENCE_FICTION: return "science fiction";
            case BIOGRAPHY: return "biography";
            case ROMANCE: return "romance";
            case MYSTERY: return "mystery";
            case THRILLER: return "thriller";
            case HISTORICAL: return "historical";
            case WESTERN: return "western";
            case TRAVEL: return "travel";
            case MEMOIR: return "memoir";

            //this indicates that the program is failing
            default: return "something else (ItemClasses.BookProperties.Genre#genreToString)";

        }
    }
}
