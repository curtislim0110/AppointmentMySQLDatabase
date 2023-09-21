package model;

/**
 * class for customers, all appointments must be assigned to at least one customer
 */
public class customers {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostal;
    private String customerPhone;
    private int firstDivisionID;
    private String firstDivisionName;
    private int countryID;
    private String countryName;

    /**
     * constructor
     * @param customerID customer ID, this is set by the database automatically
     * @param customerName customer name
     * @param customerAddress customer address
     * @param customerPostal customer postal code
     * @param customerPhone customer phone number
     * @param firstDivisionID first division ID
     * @param firstDivisionName first division name
     * @param countryID country ID
     * @param countryName country name
     */
    public customers (int customerID, String customerName, String customerAddress, String customerPostal,
                     String customerPhone, int firstDivisionID, String firstDivisionName, int countryID, String countryName) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.firstDivisionID = firstDivisionID;
        this.firstDivisionName = firstDivisionName;
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * getter
     * @return
     */
    public int getCustomerID() { return customerID; }

    /**
     * getter
     * @return
     */
    public String getCustomerName() { return customerName; }

    /**
     * getter
     * @return
     */
    public String getCustomerAddress() { return customerAddress; }

    /**
     * getter
     * @return
     */
    public String getCustomerPostal() { return customerPostal; }

    /**
     * getter
     * @return
     */
    public String getCustomerPhone() { return customerPhone; }

    /**
     * getter
     * @return
     */
    public int getFirstDivisionID() { return firstDivisionID; }

    /**
     * getter
     * @return
     */
    public String getFirstDivisionName() { return firstDivisionName; }

    /**
     * getter
     * @return
     */
    public int getCountryID() { return countryID; }

    /**
     * getter
     * @return
     */
    public String getCountryName() { return countryName; }

    /**
     * this toString method overrides the default toString method, which returns customerID instead
     * of a pointer reference to a hash code when customers are displayed in a combo box
     *
     * @return returns customerID to be displayed as a string
     */
    @Override
    public String toString() {
        return (Integer.toString(customerID));
    }

}
