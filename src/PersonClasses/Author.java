package PersonClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Frequent;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an author of a Book.
 * Inherits Person because anyone should be able to buy items from a bookshop.
 * NOTE: the author's contact telephone number is actually
 * the phone number of his/her agent.
 *
 * @see Person
 * @see StoringClasses.Bookshop
 * @see ItemClasses.Book
 *
 * @author anonymus
 * @version 1.1
 */
public class Author extends Person {

    /**
     * This is the number of different genres that are defined.
     * An author can in any genre a maximum of one time.
     * (There is no point in adding the same genre again.)
     *
     * @see Author#currentNumberOfGenres
     * @see ItemClasses.BookProperties.Genre
     */
    private static final int MAXIMUM_NUMBER_OF_GENRES = Genre.values ().length;

    /**
     * Since the List of genres is private, this field is provided,
     * so that the number of genres is viewable but not amendable.
     *
     * @see Author#genres
     */
    private int currentNumberOfGenres = 0;
    private List <Genre> genres = new ArrayList<> (  );
    /**
     * Since the List of books is private, this field is provided,
     * so that the number of books is viewable but not amendable.
     *
     * @see Author#books
     */
    private int currentNumberOfBooks = 0;
    private List <Book> books = new ArrayList<> (  );

    public int getCurrentNumberOfGenres () {

        return this.currentNumberOfGenres;

    }
    public void addGenre ( String genre ) {

        if ( this.getCurrentNumberOfGenres () == MAXIMUM_NUMBER_OF_GENRES ) {

            System.out.println ( "The maximum number of genres has been reached." );
            return;

        }

        Genre g = Genre.stringToGenre ( genre );

        if ( this.hasGenre ( g ) ) {

            System.out.println ("This genre is already added.");
            return;

        }

        this.genres.add ( g );

        //a valid genre has been entered
        if ( !Frequent.objectIsEmpty
                (this.genres.get ( this.getCurrentNumberOfGenres () ) )
                )
            currentNumberOfGenres ++;

            //otherwise the List of Genres keeps growing
        else this.genres.remove ( this.getCurrentNumberOfGenres () );

    }
    public boolean hasGenre ( Genre genre ) {

        for ( int i = 0; i < this.getCurrentNumberOfGenres (); i++ )
            if ( genres.get ( i ) == genre )
                return true;

        return false;

    }
    public void removeGenre ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfGenres () ) ){

            currentNumberOfGenres--;
            this.genres.remove ( index );

        }


        else System.out.println ("Invalid index.(Author#removeGenre)");

    }
    public String getGenreAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfGenres () ) )
            return genres.get ( index ).genreToString ();

        else {

            System.out.println ("There is no genre at this index.");
            return "invalid index (Author#getGenreAt)";

        }

    }
    public String listAllGenres () {

        String allGenres = "";

        for ( int i = 0 ; i < this.getCurrentNumberOfGenres ()-1 ; i++ )
            allGenres += getGenreAt ( i ) + ", ";

        allGenres += getGenreAt ( this.getCurrentNumberOfGenres ()-1 );

        return allGenres;

    }

    public int getCurrentNumberOfBooks () {

        return this.currentNumberOfBooks;

    }
    /**
     * When a book is created it should be associated with at least one author.
     * Therefore it is acceptable that null books are added but it is expected that
     * their fields will be filled.
     *
     * @param book that is added
     *
     * @see Author#books
     *
     */
    public void addBook ( Book book ) {

        if ( !this.hasBook ( book ) ) {

            this.books.add ( book );
            currentNumberOfBooks++;
            //add this author to the books authors
            book.addAuthor ( this );

        }

    }
    public void removeBook ( int index ) {

        if ( !Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfBooks () ) )
            System.out.println ("No book at that index.");

        else {

            books.remove ( index );
            currentNumberOfBooks --;

        }

    }
    public boolean hasBook ( Book book ) {

        for ( int i = 0; i < this.getCurrentNumberOfBooks (); i++ )
            if ( books.get(i).equals ( book ) )
                return true;

        return false;

    }
    public Book getBookAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfBooks () ) )
            return this.books.get ( index );

        else {

            System.out.println ("No book at this index. (Author#getBookAt)");
            return null;

        }

    }
    public String listAllBooks () {

        String list = "List of books by author: " + this.getName () + "\n";

        for ( int i = 0; i < this.getCurrentNumberOfBooks (); i++ )
            list += this.books.get ( i ).getName () + "\n";

        return list;

    }

    public void creditAuthor ( double commission ) {

        if ( commission > 0 )
            this.setMoney ( this.getMoney () + commission );

    }

    public Author ( String name ) {

        super ( name );

    }
    public Author ( String name, String contactTelephoneNumber ) {

        this ( name);
        this.setContactTelephoneNumber ( contactTelephoneNumber );

    }
    public Author ( String name, String physicalAddress, String email, String contactTelephoneNumber ) {

        this (name, contactTelephoneNumber);
        this.setPhysicalAddress ( physicalAddress );
        this.setEmail ( email );

    }

    /**
     * @return more details than person.toString()
     *
     * @see Person#toString()
     */
    @Override
    public String toString () {

        return super.toString () +
                "Writes in " + this.getCurrentNumberOfGenres () + " genre(s)\n" +
                "Has written " + this.getCurrentNumberOfBooks () + " book(s)\n";

    }
}
