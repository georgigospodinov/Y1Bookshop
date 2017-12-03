package ItemClasses;

import ItemClasses.BookProperties.BookFormat;
import ItemClasses.BookProperties.Day;
import ItemClasses.BookProperties.Genre;
import PersonClasses.Author;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a Book.
 * Inherits Item.
 *
 * @see Item
 * @see StoringClasses.Bookshop
 * @see StoringClasses.Order
 *
 * @author anonymus
 * @version 1.4
 */
public class Book extends Item {

    /**
     * This is the number of different genres that are defined.
     * A book can belong to any genre a maximum of one time.
     * (There is no point in adding the same genre again.)
     *
     * @see Book#currentNumberOfGenres
     * @see ItemClasses.BookProperties.Genre
     */
    private static final int MAXIMUM_NUMBER_OF_GENRES = Genre.values ().length;

    private String publisher = NOT_FILLED;
    /**
     * International Standard Book Number.
     * Consists of 10 or 13 characters (not counting dashes '-').
     * All non-dash characters are numbers between 0 and 9, including both.
     * Start and End characters are always numbers.
     *
     * @see Book#setIsbn(String)
     */
    private String isbn = NOT_FILLED;//by default
    /**
     * Since the List of genres is private, this field is provided,
     * so that the number of genres is viewable but not amendable.
     *
     * @see Book#genres
     */
    private int currentNumberOfGenres = 0;
    private List<Genre> genres = new ArrayList<> ();
    private BookFormat format = BookFormat.PAPERBACK;//by default books are paperback
    private Day publicationDate = new Day ();//default value
    /**
     * Since the List of Authors is private, this field is provided,
     * so that the number of authors is visible but not amendable.
     *
     * @see Book#authors
     */
    private int currentNumberOfAuthors = 0;
    private List<Author> authors = new ArrayList<> (  );

    //some restriction could be provided
    public void setPublisher ( String publisher ) {

        if ( !Frequent.stringIsEmpty ( publisher ))
            this.publisher = publisher;

    }
    public String getPublisher () {

        return this.publisher;

    }

    public void setIsbn ( String isbn ) {

        int isbnLength = isbn.length ();

        if ( isbnLength < 1 ) {

            System.out.println ("You cannot enter empty ISBNs");
            return;

        }

        if ( isbn.charAt ( 0 ) < '0' || isbn.charAt ( 0 ) > '9' ) {

            System.out.println ("The ISBN must begin with a number.");
            return;

        }

        int numberCount = 1;

        for ( int i = 1; i < isbnLength; i++ ) {

            //characters are numbers between 0 and 9 OR dashes
            if ( ( isbn.charAt ( i ) < '0' || isbn.charAt ( i ) > '9' ) && isbn.charAt ( i ) != '-' ) {

                System.out.println ( "Invalid ISBN. Illegal character(s)." );
                return;

            }

            if ( isbn.charAt ( i ) != '-' )
                numberCount++;

            else if ( isbn.charAt ( i-1 ) == '-' ) {

                System.out.println ("You cannot have two consecutive dashes (-)");
                return;

            }

            if ( numberCount > 13 ) {

                System.out.println ( "Invalid ISBN. Too many numbers." );
                return;

            }

        }

        //ISBNs have either 10 or 13 numbers
        if ( numberCount != 10 && numberCount != 13 ) {

            System.out.println ( "Invalid ISBN. Inappropriate amount of numbers." );
            return;

        }

        this.isbn = isbn;

    }
    public String getIsbn () {

        return this.isbn;

    }

    public int getCurrentNumberOfGenres () {

        return this.currentNumberOfGenres;

    }
    public void addGenre ( String genre ) {

        if ( this.getCurrentNumberOfGenres() == MAXIMUM_NUMBER_OF_GENRES ) {

            System.out.println ( "The maximum number of genres has been reached." );
            return;

        }

        Genre g = Genre.stringToGenre ( genre );

        //a valid genre has been entered
        if ( Frequent.objectIsEmpty ( g ) )
            System.out.println ("Invalid genre.");

        else if ( this.hasGenre ( g ) ) {

            System.out.println ("This genre is already added.");
            return;

        }

        else {

            this.genres.add ( g );
            currentNumberOfGenres ++;

        }

    }
    public boolean hasGenre ( Genre genre ) {

        for ( int i = 0; i < getCurrentNumberOfGenres(); i++ )
            if ( genres.get ( i ) == genre )
                return true;

        return false;

    }
    public void removeGenre ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, getCurrentNumberOfGenres() ) ){

            currentNumberOfGenres--;
            this.genres.remove ( index );

        }


        else System.out.println ("Invalid index.(Book#removeGenre)");

    }
    public String getGenreAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, getCurrentNumberOfGenres() ) )
            return genres.get ( index ).genreToString ();

        else return "There is no genre at this index.";

    }
    public String listAllGenres () {

        String allGenres = "";

        for ( int i = 0 ; i < getCurrentNumberOfGenres()-1 ; i++ )
            allGenres += getGenreAt ( i ) + ", ";

        allGenres += getGenreAt ( getCurrentNumberOfGenres()-1 );

        return allGenres;

    }

    public void setFormat ( String format ) {

        if ( Frequent.stringIsEmpty ( format ) )
            System.out.println ("You cannot set an empty format.");

        BookFormat myFormat = BookFormat.stringToBookFormat ( format );
        if ( Frequent.objectIsEmpty ( myFormat ))
            System.out.println ("Invalid Book Format.");

        else this.format = BookFormat.stringToBookFormat ( format );

    }
    public String getFormat () {

        return this.format.bookFormatToString ();

    }

    public void setPublicationDate ( String publicationDate ) {

        this.publicationDate = new Day ( publicationDate );

    }
    public String getPublicationDate () {

        return this.publicationDate.toString ();

    }

    public int getCurrentNumberOfAuthors () {

        return this.currentNumberOfAuthors;

    }
    public void addAuthor ( Author author ) {

        if ( Frequent.objectIsEmpty ( author ) )
            System.out.println ("You cannot add an empty author.");


        else if ( !this.hasAuthor ( author ) ) {

            authors.add ( author );
            currentNumberOfAuthors++;
            //add this book to the author's list of books
            author.addBook ( this );

        }

    }
    public boolean hasAuthor ( Author author ) {

        for ( int i = 0; i < this.getCurrentNumberOfAuthors (); i++ )
            if ( this.getAuthorAt ( i ).equals ( author ) )
                return true;

        return false;

    }
    public void removeAuthor ( int index ) {

        if ( !Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfAuthors () ) )
            System.out.println ("No Author at that index.");

        else {

            this.authors.remove ( index );
            this.currentNumberOfAuthors --;

        }

    }
    public Author getAuthorAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfAuthors () ))
            return this.authors.get ( index );

        else {

            System.out.println ("Index is not in range. (Book#getAuthorAt)");
            return null;

        }

    }

    /**
     * @return names of all authors
     *
     * @see Book#authors
     */
    public String listAllAuthors () {

        String list = "List of all authors of this book:\n";

        for ( int i = 0; i < this.getCurrentNumberOfAuthors ()-1; i++ )
            list += this.getAuthorAt ( i ).getName () + ", ";

        list += this.getAuthorAt ( this.getCurrentNumberOfAuthors ()-1 ).getName () + "\n";

        return list;

    }

    public Book ( Author author ) {

        super(IS_BOOK, NOT_FILLED);
        this.addAuthor ( author );
        author.addBook ( this );

    }
    public Book ( Author author, String  name ) {

        this (author);
        this.setName ( name );

    }
    public Book ( Author author, String name, double price ) {

        this (author, name);
        this.setPrice ( price );

    }
    public Book ( Author author, String name, String format ) {

        this ( author, name );
        this.setFormat ( format );

    }
    public Book ( Author author, String name, String format, String publicationDate ) {

        this ( author, name, format );
        this.setPublicationDate ( publicationDate );

    }
    public Book ( Author author, String name, String format, double price ) {

        this ( author, name, price );
        this.setFormat ( format );
    }

    /**
     * @return more details than item.toString()
     *
     * @see Item#toString()
     */
    @Override
    public String toString () {

        //to be continued
        return super.toString () + "\n" + getFormat () + "  " + listAllGenres ();

    }

    /**
     * Additional conditions to what item.equals(obj) has
     * @param obj that is compared to this
     * @return true if the two objects are the same, false otherwise
     *
     * @see Item#equals(Object)
     */
    @Override
    public boolean equals ( Object obj ) {

        if ( !super.equals ( obj ) )
            return false;

        else if ( !(obj instanceof Book) )
            return false;

        //two books are the same if they have the same publisher, format, release date and genres

        else if ( !Frequent.stringsAreEqual ( this.getPublisher (), ( ( Book ) obj ).getPublisher () ) )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.getFormat (), ( ( Book ) obj ).getFormat () ) )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.getPublicationDate (), ( ( Book ) obj ).getPublicationDate () ) )
            return false;

        else if ( !Frequent.stringsAreEqual ( this.listAllGenres (), ( ( Book ) obj ).listAllGenres () ))
            return false;

        else return true;
    }

    /**
     * Adds more details to what item.getStockInfo would return.
     * @return super.getStockInfo() + more
     *
     * @see Item#getStockInfo()
     */
    @Override
    public String getStockInfo () {

        return super.getStockInfo () + " (" + this.getFormat () + ")";
    }

    /**
     * Adds more to what item.getFullDetails would return
     * @return super.getFullDetails() + more
     *
     * @see Item#getFullDetails()
     */
    @Override
    public String getFullDetails () {

        return super.getFullDetails () +
                "\nGenres: " + this.listAllGenres () +
                "; ISBN: " + this.getIsbn () +
                "\nPublished on: " + this.getPublicationDate () +
                "; by: " + this.getPublisher () +
                "\nFormat: " + this.getFormat () + "\n" +
                this.listAllAuthors ();
    }
}