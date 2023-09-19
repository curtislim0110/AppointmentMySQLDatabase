package model;

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

    // Getters
    public int getCustomerID() { return customerID; }
    public String getCustomerName() { return customerName; }
    public String getCustomerAddress() { return customerAddress; }
    public String getCustomerPostal() { return customerPostal; }
    public String getCustomerPhone() { return customerPhone; }
    public int getFirstDivisionID() { return firstDivisionID; }
    public String getFirstDivisionName() { return firstDivisionName; }
    public int getCountryID() { return countryID; }
    public String getCountryName() { return countryName; }

    // Setters
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    public void setCustomerPostal(String customerPostal) { this.customerPostal = customerPostal; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public void setFirstDivisionID(int firstDivisionID) { this.firstDivisionID = firstDivisionID; }
    public void setFirstDivisionName(String firstDivisionName) { this.firstDivisionName = firstDivisionName; }
    public void setCountryID(int countryID) { this.countryID = countryID; }
    public void setCountryName(String countryName) { this.countryName = countryName; }

    /**
     * this toString method overrides the default toString method, which returns customer name instead
     * of a pointer reference to a hash code when customers are displayed in a combo box
     *
     * @return customerName
     */
    @Override
    public String toString() {
        return (customerName);
    }

}
