package ItemClasses;

import ItemClasses.BookProperties.BookFormat;
import ItemClasses.BookProperties.Day;
import ItemClasses.BookProperties.Genre;
import PersonClasses.Author;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Applicable to the Book class (v1.4).
 * Covers all 31 methods and 142/147 lines (96%).
 * The five lines that are not covered are
 * 'return false' statements after a condition (if).
 * All conditions are tested and needed for a realistic situation.
 *
 * Also covers all methods in the BookFormat and Genre enumerators,
 * and all methods in Day.
 * For further testing of the classes in the BookProperties package
 * use TestDay.
 *
 * @see Book
 * @see Book#equals(Object)
 * @see ItemClasses.BookProperties.TestDay
 *
 * @author anonymus
 * @version 2.2
 */
public class TestBook {

    Book theBook;
    Author theAuthor;

    @Before
    public void setUp () throws Exception {

        theAuthor = new Author ( "Brandon Sanderson" );
        theBook = new Book ( theAuthor, "Mistborn", "paper", "17 Jul 2006" );
        theBook.addGenre ( "fantasy" );

    }

    @Test
    public void testPublisher () throws Exception {

        theBook.setPublisher ( "" );
        assertEquals ( Frequent.NOT_FILLED, theBook.getPublisher () );
        theBook.setPublisher ( "Tor Books" );//valid
        assertEquals ( "Tor Books", theBook.getPublisher () );

    }

    @Test
    public void testIsbn () throws Exception {

        theBook.setIsbn ( "" );
        assertEquals ( Frequent.NOT_FILLED, theBook.getIsbn () );
        theBook.setIsbn ( "985-856-7468" );//valid
        theBook.setIsbn ( "j239j49g42" );//does not start with a number
        theBook.setIsbn ( "234---2384-234" );//cannot have a succession of dashes
        theBook.setIsbn ( "945-sk-35" );//invalid characters
        theBook.setIsbn ( "584695874589651254" );//too many numbers
        theBook.setIsbn ( "1254-8569" );//inappropriate amount of numbers
        assertEquals ( "985-856-7468", theBook.getIsbn () );

    }

    @Test
    public void testGenres () throws Exception {

        theBook.addGenre ( "fiction" );
        theBook.addGenre ( "romance" );
        assertEquals ( 3, theBook.getCurrentNumberOfGenres () );
        assertEquals ( Genre.FANTASY.genreToString (), theBook.getGenreAt ( 0 ));
        theBook.removeGenre ( 0 );//removes fantasy, fiction should become first
        assertEquals ( Genre.FICTION.genreToString (), theBook.getGenreAt ( 0 ) );
        theBook.addGenre ( "romance" );//already added
        theBook.addGenre ( "3kf03" );//should not be added.
        theBook.addGenre ( "" );//should not be added.
        theBook.addGenre ( "thriller" );//should be added
        assertEquals ( Genre.THRILLER.genreToString (), theBook.getGenreAt ( 2 ) );
        assertEquals ( "fiction, romance, thriller", theBook.listAllGenres () );
        theBook.addGenre ( "fantasy" );
        theBook.addGenre ( "SCIENCE FICTION" );
        theBook.addGenre ( "Biography" );
        theBook.addGenre ( "mystery" );
        theBook.addGenre ( "historical" );
        theBook.addGenre ( "western" );
        theBook.addGenre ( "travel" );
        theBook.addGenre ( "memoir" );
        assertEquals ( Genre.values ().length, theBook.getCurrentNumberOfGenres () );
        theBook.addGenre ( "memoir" );//again

        //should be invalid index
        theBook.getGenreAt ( 20 );
        theBook.removeGenre ( 11 );

    }

    @Test
    public void testFormat () throws Exception {

        assertEquals ( BookFormat.PAPERBACK.bookFormatToString (), theBook.getFormat () );
        theBook.setFormat ( "hard" );
        theBook.setFormat ( "0i4g4" );//ignored
        theBook.setFormat ( null );//ignored
        theBook.setFormat ( "" );//ignored
        assertEquals ( BookFormat.HARDBACK.bookFormatToString (), theBook.getFormat () );

    }

    @Test
    public void testPublicationDate () throws Exception {

        Day day = new Day ( "17 Jul 2006" );
        assertEquals ( day.toString (), theBook.getPublicationDate () );

        day = new Day ( 20, "Aug", 2006 );//accepted
        theBook.setPublicationDate ( day.getDate () );
        assertEquals ( day.toString (), theBook.getPublicationDate () );

        day = new Day (  );//the default date is created
        //the default date is set in the following cases
        theBook.setPublicationDate ( "49j944 sdfo0 dklsf" );
        theBook.setPublicationDate ( "" );
        theBook.setPublicationDate ( null );
        assertEquals ( day.toString (), theBook.getPublicationDate () );

    }

    @Test
    public void testAuthors () throws Exception {

        Author author = new Author ( "Robert Jordan" );
        theBook.addAuthor ( author );
        theBook.addAuthor ( theAuthor );
        assertEquals ( "List of all authors of this book:\n"
                +theAuthor.getName () + ", " + author.getName ()+ "\n",
                theBook.listAllAuthors () );

        theBook.removeAuthor ( 1 );
        assertEquals ( "List of all authors of this book:\n"
                +theAuthor.getName () + "\n",
                theBook.listAllAuthors () );

        //is invalid index:
        theBook.removeAuthor ( 10 );
        theBook.getAuthorAt ( -10 );

        //is ignored
        theBook.addAuthor ( null );

    }

    @Test
    public void testToString () throws Exception {

        System.out.println (theBook);

    }

    @Test
    public void testEquals () throws Exception {

        Book nextBook = new Book ( theAuthor, "Mistborn", "paperback", 10.95 );
        nextBook.addGenre ( "fantasy" );
        nextBook.setPublicationDate ( theBook.getPublicationDate () );
        assertTrue ( nextBook.equals ( theBook ) );

    }

    @Test
    public void testGetStockInfo () throws Exception {

        System.out.println (theBook.getStockInfo ());

    }

    @Test
    public void testGetFullDetails () throws ExecutionException {

        System.out.println (theBook.getFullDetails ());

    }
}