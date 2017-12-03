package StoringClasses;

import ItemClasses.Book;
import ItemClasses.BookProperties.Genre;
import ItemClasses.Frequent;
import ItemClasses.Item;
import PersonClasses.Employee;
import PersonClasses.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an online bookshop.
 * It has employees and can process orders.
 *
 * @see Inventory
 * @see Employee
 * @see Order
 *
 * @author anonymus
 * @version 1.4
 */
public class Bookshop extends Inventory implements Frequent {

    /**
     * Each bookshop charges its own delivery charge.
     * By default this amount is 1 GBP.
     *
     * @see Order#calculateTotalOrderCost()
     */
    private double deliveryCharge = 1.0;
    /**
     * This is the amount of money an order needs to be (or more) for the delivery cahrge to be waived.
     * By default this amount is 20 GBP.
     *
     * @see Order#calculateTotalOrderCost()
     */
    private double orderCostThreshold = 20;
    /**
     * This is the percentage of the price of the Book that its author(s)
     * is(are) paid. When an order is processed this field is used in
     * calculating the amount the authr should be paid.
     *
     * @see PersonClasses.Author
     * @see Bookshop#processOneOrder(int)
     */
    private double authorCommission = 0.15;
    /**
     * This is the percentage of the total order that an employee is paid.
     *
     * @see Bookshop#processOneOrder(int)
     */
    private double employeeCommission = 0.1;

    private String name = NOT_FILLED;
    private String shopURL = NOT_FILLED;
    private double profit = 0;//starting amount of money is zero.
    /**
     * Because the List of orders is private, this field is provided,
     * so that the number of orders is viewable but not amendable.
     *
     * @see Bookshop#orders
     */
    private int currentNumberOfOrders = 0;
    private List <Order> orders = new ArrayList<>();
    /**
     * Because the List of employees is private, this field is provided,
     * so that the number of employees is viewable but not amendable;
     *
     * @see Bookshop#employees
     */
    private int currentNumberOfEmployees = 0;
    /**
     * This is technically the number of the last Employee that received commission.
     * The method getNetEmployeeToReceiveCommission()
     * increases this value than returns it.
     *
     * @see Bookshop#getNextEmployeeToReceiveCommission()
     */
    private int nextEmployeeToReceiveCommission = -1;// values range 0-currentNumberOfEmployees
    private List <Employee> employees = new ArrayList<> ();

    public void setDeliveryCharge ( double deliveryCharge ) {

        if ( deliveryCharge >= 0 )
            this.deliveryCharge = deliveryCharge;

        else System.out.println ("You cannot set a negative delivery charge.");

    }
    public double getDeliveryCharge () {

        return this.deliveryCharge;

    }

    public void setOrderCostThreshold ( double orderCostThreshold ) {

        if ( orderCostThreshold > 0 )
            this.orderCostThreshold = orderCostThreshold;

        else System.out.println ("You cannot set a threshold less than 0.\n" +
                "If you want a threshold of 0, please set the delivery charge to zero, instead.");

    }
    public double getOrderCostThreshold () {

        return this.orderCostThreshold;

    }

    public void setAuthorCommission ( double authorCommission ) {

        if ( authorCommission >= 1 || authorCommission < 0 )
            System.out.println ("The portion of a Book's price" +
                    "that the Author is credited must be between 0 and 1");

        else this.authorCommission = authorCommission;

    }
    public double getAuthorCommission () {

        return this.authorCommission;

    }

    public void setEmployeeCommission ( double employeeCommission ) {

        if ( employeeCommission >= 1 || employeeCommission < 0 )
            System.out.println ("The portion of a Book's price" +
                    "that the Employee is credited must be between 0 and 1");

        else this.employeeCommission = employeeCommission;

    }
    public double getEmployeeCommission () {

        return this.employeeCommission;

    }

    public void setName ( String name ) {

        if ( !Frequent.stringIsEmpty ( name ) )
            this.name = name;

        else System.out.println ("You cannot enter an empty name.");

    }
    public String getName () {

        return this.name;

    }

    public void setShopURL ( String shopURL ) {

        if ( Frequent.stringIsEmpty ( shopURL ) )
            System.out.println ( "You cannot enter an empty url." );

        else {//the url should have at least one period ('.')

            int i;
            for ( i = 0; i < shopURL.length (); i++ )
                if ( shopURL.charAt ( i ) == '.' )
                    break;

            if ( i < shopURL.length () )
                this.shopURL = shopURL;

            else System.out.println ("This URL is not valid.");

        }

    }
    public String getShopURL () {

        return this.shopURL;

    }

    public double getProfit () {
        return this.profit;
    }
    public int getCurrentNumberOfOrders () {
        return this.currentNumberOfOrders;
    }

    public int getCurrentNumberOfEmployees () {
        return this.currentNumberOfEmployees;
    }
    private int getNextEmployeeToReceiveCommission () {

        this.nextEmployeeToReceiveCommission++;
        return this.nextEmployeeToReceiveCommission;

    }
    /**
     * An empty order can be added because it will at first always be created empty.
     * (E.G. you visit an online shop and it says that your shopping basket is empty)
     *
     * @param order - the order (usually a just created one)
     *
     * @see Order#Order(Bookshop, Person)
     */
    public void addOrder ( Order order ) {

        orders.add ( order );
        currentNumberOfOrders ++;

    }
    /**
     * Removes an order from the bookshops record.
     * This method is meant for usage only by the class.
     * If this is changed to public, a condition (if)
     * to check the validity of the index, should be added.
     * Similar to removeEmployee(index)
     * @param index of order to remove
     *
     * @see Bookshop#removeCanceledOrders()
     * @see Bookshop#removeEmployee(int)
     */
    private void removeOrder ( int index ) {

        orders.remove ( index );
        currentNumberOfOrders --;

    }
    /**
     * Removes all canceled orders.
     * Uses removeOrder(index) in a loop.
     * This is done at the end of each month.
     *
     * @see Bookshop#removeOrder(int)
     * @see Bookshop#passOneMonth()
     */
    private void removeCanceledOrders () {

        for ( int i = 0; i < this.getCurrentNumberOfOrders (); i++ )
            if ( this.getOrderAt ( i ).isCanceled () )
                this.removeOrder ( i );

    }
    public Order getOrderAt ( int index ) {

        if ( Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfOrders() ) )
            return orders.get ( index );

        else {

            System.out.println ("There is no order at that index.");
            return null;
        }

    }
    private void processOneOrder ( int index ) {

        if ( !Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfOrders () ) )
            return;

        Order myOrder = this.getOrderAt ( index );

        if ( Frequent.objectIsEmpty ( myOrder ) )
            return;

        //order should be confirmed, NOT processed and NOT canceled.
        if ( myOrder.isConfirmed () && !myOrder.isProcessed () && !myOrder.isCanceled () ) {

            double cost = myOrder.calculateTotalOrderCost ();
            double moneyForEmployee = cost*this.getEmployeeCommission ();
            cost -= moneyForEmployee;
            myOrder.process ();

            //remove stock
            for ( int i = 0; i < myOrder.getCurrentNumberOfItems (); i++ ) {

                Item itemFromOrder = myOrder.getItemAt ( i );
                itemFromOrder.setQuantityAvailable ( itemFromOrder.getQuantityAvailable ()-1 );

                //give commission to author(s)
                if ( itemFromOrder instanceof Book ) {

                    double moneyForAuthors = itemFromOrder.getPrice ()*this.getAuthorCommission ();
                    cost -= moneyForAuthors;
                    int numberOfAuthors = ( ( Book ) itemFromOrder ).getCurrentNumberOfAuthors ();
                    moneyForAuthors /= numberOfAuthors;//divide money between all authors

                    for ( int j = 0; j < numberOfAuthors; j++ )//credit them all.
                        ( ( Book ) itemFromOrder ).getAuthorAt ( j ).creditAuthor ( moneyForAuthors );

                }

            }

            //give employee some commission :)
            this.getEmployeeAt ( this.getNextEmployeeToReceiveCommission () ).receiveCommission ( moneyForEmployee );

            if ( this.nextEmployeeToReceiveCommission+1 >= this.getCurrentNumberOfEmployees () )
                this.nextEmployeeToReceiveCommission = -1;

            profit += cost;

        }

    }
    public void processOrders () {

        for ( int i = 0; i < this.getCurrentNumberOfOrders (); i++ )
            processOneOrder ( i );

    }
    public String listAllOrders () {

        String list = this.getName () + " - List of orders:\n";

        for ( int i = 0; i < this.getCurrentNumberOfOrders (); i++ )
            list += "Order index: " + i + " Client: " + this.getOrderAt ( i ).getClient ().getName ()
                    + "\n" + this.getOrderAt ( i ) + "\n";

        return list;
    }
    public String listSalesMade () {

        String list = this.getName () + " - List of sales made:\n";

        for ( int i = 0; i < this.getCurrentNumberOfOrders (); i++ )
            if ( this.getOrderAt ( i ).isProcessed () )
                list += "Order index: " + i + "\n" + this.getOrderAt ( i ) + "\n";

        return list;

    }
    public String listCustomersWhoBought ( Item item ) {

        String list = this.getName () + " - List of people who bought: " + item.getName () + "\n";

        for ( int i = 0; i < this.getCurrentNumberOfOrders (); i++ )
            if ( this.getOrderAt ( i ).isProcessed () && this.getOrderAt ( i ).hasItem ( item ) )
                list += this.getOrderAt ( i ).getClient ().getName () + "\n";

        return list;

    }

    /**
     * This method is called whenever an Employee is created.
     * Created employees always have an ID and an associated bookshop.
     * Therefore it is never null.
     * @param employee the new employee
     *
     * @see Employee#Employee(Bookshop, String)
     */
    public void addEmployee ( Employee employee ) {

        this.employees.add ( employee );
        currentNumberOfEmployees++;

    }
    public void removeEmployee ( int index ) {

        if ( !Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfEmployees () ) )
            System.out.println ("There is no employee at this index.");

        else {

            this.employees.remove ( index );
            currentNumberOfEmployees --;

        }

    }
    public Employee getEmployeeAt ( int index ) {

        if ( !Frequent.indexIsWithinRange ( index, this.getCurrentNumberOfEmployees () ) )
            System.out.println ("There is no employee at this index.");

        else return this.employees.get ( index );

        return null;

    }
    public String listEmployees () {

        String list = this.getName () + " - List of employees\n";

        for ( int i = 0; i < this.getCurrentNumberOfEmployees (); i++ )
            list += i + " " + this.getEmployeeAt ( i ).getIdentity () + "\n";

        return list;

    }
    public String listEmployeesWhoseSalesAreAtLeast ( int numberOfSales ) {

        String list = this.getName () + " - List of employees who made at least than "
                + numberOfSales + " sales:\n";

        for ( int i = 0; i < this.getCurrentNumberOfEmployees (); i++ )
            if ( this.getEmployeeAt ( i ).getAmountOfSalesMade () >= numberOfSales )
                list += this.getEmployeeAt ( i ).getIdentity () + "\n";

        return list;

    }
    public double calculateTotalAmountPayableToEmployees () {

        double amount = 0;

        for ( int i = 0; i < this.getCurrentNumberOfEmployees (); i++ )
            amount += this.getEmployeeAt ( i ).getMonthlySalary ();

        return amount;

    }
    /**
     * All employees are paid their monthly salary.
     * This is done are the end of each month.
     *
     * @see Bookshop#calculateTotalAmountPayableToEmployees()
     * @see Bookshop#passOneMonth()
     */
    private void paySalaries () {

        double amountToPay = this.calculateTotalAmountPayableToEmployees ();

        if ( amountToPay > this.getProfit () )
            System.out.println ("The bookshop cannot pay its employees' salaries.");

        else {

            for ( int i = 0; i < this.getCurrentNumberOfEmployees (); i++ ) {

                Employee nextEmployee = this.getEmployeeAt ( i );

                this.profit -= nextEmployee.getMonthlySalary ();
                nextEmployee.setMoney ( nextEmployee.getMoney () + nextEmployee.getMonthlySalary () );

            }

        }

    }

    /**
     * At the end of each month:
     * - employees get paid
     * - canceled orders are removed from the bookshop record
     *
     * @see Bookshop#paySalaries()
     * @see Bookshop#removeCanceledOrders()
     */
    public void passOneMonth () {

        this.paySalaries ();
        this.removeCanceledOrders ();

    }

    public Bookshop ( String name ) {

        this.setName ( name );

    }
    public Bookshop ( String name, String shopURL ) {

        this (name);
        this.setShopURL ( shopURL );

    }
    public Bookshop ( double deliveryCharge, double orderCostThreshold ) {

        this.setDeliveryCharge ( deliveryCharge );
        this.setOrderCostThreshold ( orderCostThreshold );

    }
    public Bookshop ( String name, double deliveryCharge, double orderCostThreshold ) {

        this (deliveryCharge, orderCostThreshold);
        this.setName ( name );

    }
    public Bookshop ( String name, String shopURL, double orderCostThreshold, double deliveryCharge ) {

        this( name, shopURL);
        this.setDeliveryCharge ( deliveryCharge );
        this.setOrderCostThreshold ( orderCostThreshold );

    }

    @Override
    public boolean equals ( Object obj ) {

        if ( !super.equals ( obj ) )
            return false;

        else if ( !(obj instanceof Bookshop))
            return false;

        //two bookshops are the same if they have the same url
        else return Frequent.stringsAreEqual ( this.getShopURL (), ( ( Bookshop ) obj ).getShopURL () );

    }

    @Override
    public String toString () {

        String string = "Bookshop: " + this.getName ();

        string += " (" + this.getShopURL () + ")\n";
        string += "delivery charge (" + this.getDeliveryCharge () + " GBP)";
        string += " is waived for orders worth more than " + this.getOrderCostThreshold () + " GBP\n";

        string += "Author commission: " + this.getAuthorCommission ()*100 + "% of book price\n";
        string += "Employee commission: " + this.getEmployeeCommission ()*100 + "% of order price\n";

        string += "Items in stock: " + this.getCurrentNumberOfItems () + "\n";
        string += "Working employees: " + this.getCurrentNumberOfEmployees ();
        string += "Orders: " + this.getCurrentNumberOfOrders ();

        return string;
    }

    @Override
    public String listAllItems () {

        return this.getName () + " - " + super.listAllItems ();

    }

    @Override
    public String listBooks () {

        return this.getName () + " - " + super.listBooks ();

    }

    @Override
    public String listStationaries () {

        return this.getName () + " - " + super.listStationaries ();

    }

    @Override
    public String listBooksInGenre ( Genre genre ) {

        return this.getName () + " - " + super.listBooksInGenre ( genre );

    }

    @Override
    public String listFullItemDetails () {

        return this.getName () + " - " + super.listFullItemDetails ();

    }
}