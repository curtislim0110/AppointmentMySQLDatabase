package model;

public class customers {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPostal;
    private String customerPhone;
    private int firstDivisionID;
    private String firstDivisionName;

    public customers (int customerID, String customerName, String customerAddress, String customerPostal,
                     String customerPhone, int firstDivisionID, String firstDivisionName) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostal = customerPostal;
        this.customerPhone = customerPhone;
        this.firstDivisionID = firstDivisionID;
        this.firstDivisionName = firstDivisionName;
    }

    // Getters
    public int getCustomerID() { return customerID; }
    public String getCustomerName() { return customerName; }
    public String getCustomerAddress() { return customerAddress; }
    public String getCustomerPostal() { return customerPostal; }
    public String getCustomerPhone() { return customerPhone; }
    public int getFirstDivisionID() { return firstDivisionID; }
    public String getFirstDivisionName() { return firstDivisionName; }

    // Setters
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    public void setCustomerPostal(String customerPostal) { this.customerPostal = customerPostal; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }
    public void setFirstDivisionID(int firstDivisionID) { this.firstDivisionID = firstDivisionID; }
    public void setFirstDivisionName(String firstDivisionName) { this.firstDivisionName = firstDivisionName; }
}
