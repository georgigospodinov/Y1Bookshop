import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Stationary;
import PersonClasses.Author;
import PersonClasses.Employee;
import PersonClasses.Person;
import StoringClasses.Bookshop;
import StoringClasses.Order;

/**
 * In this class, for the purpose of demonstrating
 * I have used real bookshops, authors and books.
 *
 * I have read most of the books.
 * (I might have gotten some of the genres wrong.)
 *
 * I have visited the bookshops. (websites might be wrong)
 *
 * Stationary items are entirely made up.
 *      Pattern for naming them is "color stationary type".
 * Employees and Peoples are entirely made up.
 * Any real cases are purely coincidental.
 *
 * @author anonymus
 * @version 1.2
 */
public class DemonstrateFunctionality {

    //variables declarations
    //bookshops
    static Bookshop waterstones = new Bookshop
            ( "Waterstones", "waterstones.com", 20, 2 );

    static Bookshop ciela = new Bookshop
            ( "Ciela", "ciela.com", 25, 1 );

    //I came up with this one
    static Bookshop johnsonBooks = new Bookshop
            ( "Johnson Books", "johnson-books.com" );

    //authors
    static Author brandon = new Author ( "Brandon Sanderson" );
    static Author robert = new Author ( "Robert Jordan" );
    static Author stephen = new Author ( "Stephen King" );

    //books
    static Book mistborn;
    static Book steelHeart;
    static Book legion;

    static Book lordOfChaos;
    static Book theGatheringStorm;

    static Book findersKeepers;
    static Book endOfWatch;

    //stationaries
    static Stationary blueDiary;
    static Stationary pinkDiary;
    static Stationary redNotebook;
    static Stationary greenNotebook;
    static Stationary grayFolder;

    //employees
    static Employee wade;
    static Employee wilson;
    static Employee wally;

    static Employee caitlin;
    static Employee connor;
    static Employee chloe;
    static Employee connie;

    static Employee arthur;
    static Employee tomas;

    //person (clients)
    static Person alexander;
    static Person jane;
    static Person fred;

    //orders
    //name pattern: first name + "sOrder"
    //as in his/her order
    static Order alexandersOrder;
    static Order janesOrder;
    static Order fredsOrder;
    static Order brandonsOrder;
    static Order wallysOrder;
    static Order conniesOrder;
    static Order tomassOrder;

    static void printBookshops () {

        System.out.println ("BOOKSHOPS");
        //additional new lines for better readability
        System.out.println (waterstones+"\n");
        System.out.println (ciela+"\n");
        System.out.println (johnsonBooks+"\n");

    }

    static void defineAuthors () {

        System.out.println ("CREATE SOME AUTHORS");

        brandon.addGenre ( "fantasy" );
        brandon.addGenre ( "science fiction" );
        System.out.println (brandon);


        robert.addGenre ( "fantasy" );
        robert.addGenre ( "travel" );
        System.out.println (robert);


        stephen.addGenre ( "fiction" );
        stephen.addGenre ( "thriller" );
        System.out.println (stephen);

    }

    static void booksByBrandon () {

        System.out.println ("\nCREATE SOME BOOKS FOR BRANDON");

        mistborn = new Book ( brandon, "Mistborn" );
        mistborn.addGenre ( "fantasy" );

        steelHeart = new Book ( brandon, "Steel Heart", "hard");
        steelHeart.addGenre ( "fantasy" );

        legion = new Book ( brandon, "Legion", "electronic" );
        legion.addGenre ( "science fiction" );

        System.out.println (brandon.listAllBooks ());

    }

    static void booksByRobert () {

        System.out.println ("CREATE SOME BOOKS FOR ROBERT");

        lordOfChaos = new Book ( robert, "Lord of Chaos", "hard" );
        lordOfChaos.addGenre ( "fantasy" );
        lordOfChaos.addGenre ( "travel" );

        theGatheringStorm = new Book ( robert, "The Gathering Storm", "hard" );
        theGatheringStorm.addAuthor ( brandon );
        theGatheringStorm.addGenre ( "fantasy" );
        theGatheringStorm.addGenre ( "travel" );

        System.out.println (robert.listAllBooks ());

    }

    static void booksByStephen () {

        System.out.println ("CREATE SOME BOOKS FOR STEPHEN");

        findersKeepers = new Book ( stephen, "Finders Keepers" );
        findersKeepers.addGenre ( "thriller" );

        endOfWatch = new Book ( stephen, "End of Watch" );
        endOfWatch.addGenre ( "thriller" );
        endOfWatch.addGenre ( "fiction" );

        System.out.println (stephen.listAllBooks ());

    }

    static void defineItems () {

        booksByBrandon ();
        booksByRobert ();
        booksByStephen ();

        System.out.println ("CREATE SOME STATIONARY ITEMS");

        blueDiary = new Stationary ( "diary", "Blue Diary" );
        System.out.println (blueDiary);

        pinkDiary = new Stationary ( "diary", "Pink Diary" );
        System.out.println (pinkDiary);

        redNotebook = new Stationary ( "notebook", "Red Notebook" );
        System.out.println (redNotebook);

        greenNotebook = new Stationary ( "notebook", "Green Notebook" );
        System.out.println (greenNotebook);

        grayFolder = new Stationary ( "folder", "Gray Folder" );
        System.out.println (grayFolder);

    }

    static void addItemsToBookshops () {

        System.out.println ("\nADD ITEMS TO BOOKSHOPS");

        //books
        waterstones.addItem ( mistborn );//0
        waterstones.addItem ( steelHeart );//1
        waterstones.addItem ( findersKeepers );//2
        waterstones.addItem ( endOfWatch );//3
        //stationaries
        waterstones.addItem ( blueDiary );//4
        waterstones.addItem ( pinkDiary );//5
        System.out.println (waterstones.listAllItems () +
                "Total worth: " +  waterstones.calculateTotalItemCost ());


        //books
        ciela.addItem ( legion );//0
        ciela.addItem ( lordOfChaos );//1
        ciela.addItem ( theGatheringStorm );//2
        ciela.addItem ( findersKeepers );//3
        //stationaries
        ciela.addItem ( redNotebook );//4
        ciela.addItem ( greenNotebook );//5
        System.out.println (ciela.listAllItems () +
                "Total worth: " +  ciela.calculateTotalItemCost ());


        //books
        johnsonBooks.addItem ( legion );//0
        johnsonBooks.addItem ( endOfWatch );//1
        johnsonBooks.addItem ( steelHeart );//2
        johnsonBooks.addItem ( theGatheringStorm );//3
        //stationaries
        johnsonBooks.addItem ( grayFolder );//4
        System.out.println (johnsonBooks.listAllItems () +
                "Total worth: " +  johnsonBooks.calculateTotalItemCost ());

    }

    static void waterstonesEmployees () {

        wade = new Employee ( waterstones, "Wade Smith" );
        System.out.println (wade);

        wilson = new Employee ( waterstones, "Wilson Baker" );
        System.out.println (wilson);

        wally = new Employee ( waterstones, "Wally Creevy" );
        System.out.println (wally);

    }

    static void cielaEmployees () {

        caitlin = new Employee ( ciela, "Caitlin Tariel" );
        System.out.println (caitlin);

        connor = new Employee ( ciela, "Connor Fisk" );
        System.out.println (connor);

        chloe = new Employee ( ciela, "Chloe Summers" );
        System.out.println (chloe);

        connie = new Employee ( ciela, "Connie Tiff");
        System.out.println (connie);

    }

    static void johnsonBooksEmployees () {

        arthur = new Employee ( johnsonBooks, "Arthur Johnson" );
        System.out.println (arthur);

        tomas = new Employee ( johnsonBooks, "Tomas Johnson" );
        System.out.println (tomas);

    }

    static void defineEmployees () {

        //employees are entirely made up
        System.out.println ("\nCREATE SOME EMPLOYEES");
        waterstonesEmployees ();
        cielaEmployees ();
        johnsonBooksEmployees ();

    }

    static void definePersons () {

        System.out.println ("\nCREATE A BIT MORE PEOPLE");

        alexander = new Person ( "Alexander Cornelius" );
        System.out.println (alexander);

        jane = new Person ( "Jane Hearthmore" );
        System.out.println (jane);

        fred = new Person ( "Fred Corey" );
        System.out.println (fred);

    }

    static void defineOrders () {

        System.out.println ("\nCREATE SOME ORDERS");

        alexandersOrder = new Order ( waterstones, alexander );
        janesOrder = new Order ( ciela, jane );
        fredsOrder = new Order ( johnsonBooks, fred );

        //author who wants to buy a book
        brandonsOrder = new Order ( ciela, brandon );

        //employees who want to buy something that is not available in their bookshop
        wallysOrder = new Order ( ciela, wally );//wants to but lordOfChaos
        conniesOrder = new Order ( johnsonBooks, connie );//wants to buy steelHeart
        tomassOrder = new Order ( waterstones, tomas );//wants to buy mistborn

    }

    static void addItemsToOrders () {

        System.out.println ("\nADD ITEMS TO ORDERS");
        System.out.println (alexandersOrder.listPossibleItems ());
        alexandersOrder.addItem ( 0 );//mistborn
        alexandersOrder.addItem ( 2 );//finder keepers
        System.out.println (alexandersOrder);

        System.out.println (janesOrder.listPossibleItems ());
        janesOrder.addItem ( 0 );//legion
        janesOrder.addItem ( 1 );//lord of chaos
        janesOrder.addItem ( 4 );//red notebook
        System.out.println (janesOrder);

        System.out.println (fredsOrder.listPossibleItems ());
        fredsOrder.addItem ( 3 );//the gathering storm
        fredsOrder.addItem ( 2 );//steel heart
        fredsOrder.addItem ( 4 );//gray folder
        System.out.println (fredsOrder);
        fredsOrder.removeItem ( 2 );//remove the gray folder
        System.out.println (fredsOrder);

        System.out.println (brandonsOrder.listPossibleItems ());
        brandonsOrder.addItem ( 1 );//lord of chaos
        System.out.println (brandonsOrder);

        System.out.println (wallysOrder.listPossibleItems ());
        wallysOrder.addItem ( 1 );//lord of chaos
        System.out.println (wallysOrder);

        System.out.println (conniesOrder.listPossibleItems ());
        conniesOrder.addItem ( 2 );//steel heart
        System.out.println (conniesOrder);

        System.out.println (tomassOrder.listPossibleItems ());
        tomassOrder.addItem ( 0 );//mistborn
        tomassOrder.addItem ( 4 );//blue diary
        System.out.println (tomassOrder);
        tomassOrder.removeItem ( 0 );//remove mistborn
        System.out.println (tomassOrder);

    }

    static void processing () {

        System.out.println ("\nCONFIRM AND CANCEL ORDERS");
        alexandersOrder.confirm ();
        janesOrder.confirm ();
        fredsOrder.cancel ();
        brandonsOrder.confirm ();
        wallysOrder.cancel ();
        conniesOrder.confirm ();
        tomassOrder.confirm ();

        System.out.println ("\nPROCESS ORDERS");
        waterstones.processOrders ();
        ciela.processOrders ();
        johnsonBooks.processOrders ();

        System.out.println ("\nCANCEL PROCESSED ORDER");
        janesOrder.cancel ();

    }

    static void statesAfterPurchases () {

        System.out.println ("\nSHOW STOCK AFTER PURCHASES");
        System.out.println (waterstones.listAllItems ());
        System.out.println (ciela.listAllItems ());
        System.out.println (johnsonBooks.listAllItems ());

        System.out.println ("\nONE MONTH PASSES");
        waterstones.passOneMonth ();
        ciela.passOneMonth ();
        johnsonBooks.passOneMonth ();

        System.out.println ("\nSHOW PEOPLE'S MONEY");
        System.out.println (brandon.getName ()+" "+brandon.getMoney ());
        System.out.println (alexander.getName ()+" "+alexander.getMoney ());
        System.out.println (jane.getName ()+" "+jane.getMoney ());
        System.out.println (fred.getName ()+" "+fred.getMoney ());
        System.out.println (wally.getName ()+" "+wally.getMoney ());
        System.out.println (connie.getName ()+" "+connie.getMoney ());
        System.out.println (tomas.getName ()+" "+tomas.getMoney ());

        System.out.println ("\nSALES MADE");
        System.out.println (waterstones.listSalesMade ());
        System.out.println (ciela.listSalesMade ());
        System.out.println (johnsonBooks.listSalesMade ());

    }

    static void showBooksInGenre () {

        System.out.println (waterstones.listBooksInGenre ( Genre.FICTION ));
        System.out.println (ciela.listBooksInGenre ( Genre.FANTASY ));
        System.out.println (johnsonBooks.listBooksInGenre ( Genre.THRILLER ));

    }

    static void showNotableEmployees () {

        int threshold = 2;
        System.out.println (waterstones.listEmployeesWhoseSalesAreAtLeast ( threshold ));
        System.out.println (ciela.listEmployeesWhoseSalesAreAtLeast ( threshold ));
        System.out.println (johnsonBooks.listEmployeesWhoseSalesAreAtLeast ( threshold ));

    }

    static void showSomePurchases () {

        System.out.println (waterstones.listCustomersWhoBought ( mistborn ));
        System.out.println (ciela.listCustomersWhoBought ( lordOfChaos ));
        System.out.println (johnsonBooks.listCustomersWhoBought ( steelHeart ));

    }

    static void showStationaries () {

        System.out.println (waterstones.listStationaries ());
        System.out.println (ciela.listStationaries ());
        System.out.println (johnsonBooks.listStationaries ());

    }

    static void showFullItemDetails () {

        System.out.println (waterstones.listFullItemDetails ());
        System.out.println (ciela.listFullItemDetails ());
        System.out.println (johnsonBooks.listFullItemDetails ());

    }

    public static void main ( String[] args ) {

        //calls all methods above in the correct order
        printBookshops ();
        defineAuthors ();
        defineItems ();
        addItemsToBookshops ();
        defineEmployees ();
        definePersons ();
        defineOrders ();
        addItemsToOrders ();
        processing ();
        statesAfterPurchases ();
        showBooksInGenre ();
        showNotableEmployees ();
        showSomePurchases ();
        showStationaries ();
        showFullItemDetails ();

        //remove an author
        theGatheringStorm.removeAuthor ( 1 );
        System.out.println (theGatheringStorm.getFullDetails ());

        //move an employee
        //chloe from ciela to johnsonBooks
        chloe.setWorkPlace ( johnsonBooks );
        System.out.println ( ciela.listEmployees () );
        System.out.println ( johnsonBooks.listEmployees () );

    }
}
