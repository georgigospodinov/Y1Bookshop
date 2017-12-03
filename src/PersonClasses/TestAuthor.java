package PersonClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Frequent;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Applies to the Author class (v1.1).
 * Covers all 18 methods and 75 lines.
 *
 * @see Author
 *
 * @author anonymus
 * @version 1.05
 */
public class TestAuthor {

    Author author;

    @Before
    public void setUp () throws Exception {

        author = new Author ( "Test Author",
                "10 Small Alley", "test_author@mail.com", "07951463985" );

    }

    @Test
    public void testAddAndRemoveGenre () throws Exception {

        assertEquals ( 0, author.getCurrentNumberOfGenres () );
        author.addGenre ( "Fantasy" );
        author.addGenre ( "slkdf" );//ignored
        assertEquals ( 1, author.getCurrentNumberOfGenres () );

        assertTrue ( Frequent.stringsAreEqual ( "fantasy", author.getGenreAt ( 0 ) ) );

        author.removeGenre ( -5 );//fails as it should
        author.removeGenre ( 0 );
        assertEquals ( 0, author.getCurrentNumberOfGenres () );


        //add many genres
        author.addGenre ( "fantasy" );
        assertEquals ( 1, author.getCurrentNumberOfGenres () );
        author.addGenre ( "fantasy" );//already in
        assertEquals ( 1, author.getCurrentNumberOfGenres () );

        author.addGenre ( "biography" );
        author.addGenre ( "fiction" );
        author.addGenre ( "science fiction" );
        author.addGenre ( "romance" );
        author.addGenre ( "historical" );
        author.addGenre ( "memoir" );
        author.addGenre ( "mystery" );
        author.addGenre ( "thriller" );
        author.addGenre ( "travel" );
        author.addGenre ( "western" );
        assertEquals ( 11, author.getCurrentNumberOfGenres () );
        //maximum number of genres is now reached

        author.addGenre ( "whatever" );
        assertEquals ( 11, author.getCurrentNumberOfGenres () );

    }

    @Test
    public void testHasGenre () throws Exception {

        author.addGenre ( "fantasy" );
        assertTrue ( author.hasGenre ( Genre.FANTASY ) );

    }

    @Test
    public void testGetGenreAt () throws Exception {

        author.addGenre ( "fantasy" );
        author.addGenre ( "fiction" );

        assertTrue ( Frequent.stringsAreEqual ( "fiction", author.getGenreAt ( 1 ) ) );
        //invalid indexes
        System.out.println ( author.getGenreAt ( -1 ) );
        System.out.println ( author.getGenreAt ( 5 ) );

    }

    @Test
    public void testListAllGenres () throws Exception {

        author.addGenre ( "fantasy" );
        author.addGenre ( "romance" );
        author.addGenre ( "science fiction" );
        System.out.println (author.listAllGenres ());

    }

    @Test
    public void testAddBook () throws Exception {

        assertEquals ( 0, author.getCurrentNumberOfBooks () );

        Book book1 = new Book ( author, "Book One" );
        assertEquals ( 1, author.getCurrentNumberOfBooks () );
        assertEquals ( 1, book1.getCurrentNumberOfAuthors () );

        Book book2 = new Book ( author, "Book Two" );
        assertEquals ( 2, author.getCurrentNumberOfBooks () );

        Author author1 = new Author ( "Author One" );
        book1.addAuthor ( author1 );
        assertEquals ( 1, author1.getCurrentNumberOfBooks () );

        author1.addBook ( book2 );
        assertEquals ( 2, book2.getCurrentNumberOfAuthors () );

    }

    @Test
    public void testRemoveBook () throws Exception {

        Book book1 = new Book ( author, "Book One" );
        Book book2 = new Book ( author, "Book Two" );

        assertEquals ( 2, author.getCurrentNumberOfBooks () );

        author.removeBook ( 0 );
        assertTrue ( book2.equals ( author.getBookAt ( 0 ) ) );

        //invalid indexes
        author.removeBook ( -1 );
        author.removeBook ( 10 );
        assertEquals ( 1, author.getCurrentNumberOfBooks () );

    }

    @Test
    public void testHasBook () throws Exception {

        Book book = new Book ( author );
        assertTrue ( author.hasBook ( book ) );

    }

    @Test
    public void testGetBookAt () throws Exception {

        Book book = new Book ( author, "Book" );
        assertTrue ( Frequent.stringsAreEqual
                ( book.getName (), author.getBookAt ( 0 ).getName () ) );

        //invalid indexes
        assertTrue ( Frequent.objectIsEmpty ( author.getBookAt ( -1 ) ) );
        assertTrue ( Frequent.objectIsEmpty ( author.getBookAt ( 5 ) ) );

    }

    @Test
    public void testListAllBooks () throws Exception {

        Book book1 = new Book ( author, "Book One" );
        Book book2 = new Book ( author, "Book Two" );
        System.out.println (author.listAllBooks ());

    }

    @Test
    public void testCreditAuthor () throws Exception {

        author.creditAuthor ( 10 );
        assertEquals ( 210, author.getMoney (), 210 );

    }

    @Test
    public void testToString () throws Exception {

        System.out.println (author);

    }

}