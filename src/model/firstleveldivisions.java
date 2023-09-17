package model;

public class firstleveldivisions {

    private int divisionID;
    private String divisionName;
    private int countryID;

    public firstleveldivisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    // Getters
    public int getDivisionID() { return divisionID; }
    public String getDivisionName() { return divisionName; }
    public int getCountryID() { return countryID; }


    /**
     * this toString method overrides the default toString method, which returns divisionName instead
     * of a pointer reference to a hash code when firstleveldivisions is displayed in a combo box
     *
     * @return divisionName
     */
    @Override
    public String toString() {
        return (divisionName);
    }



}
