package model;

/**
 * class for first level divisions, like states and provinces
 */
public class firstleveldivisions {

    private int divisionID;
    private String divisionName;
    private int countryID;

    /**
     * constructor
     * @param divisionID divisionID
     * @param divisionName division name
     * @param countryID countryID
     */
    public firstleveldivisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    /**
     * getter
     * @return
     */
    public int getDivisionID() { return divisionID; }

    /**
     * getter
     * @return
     */
    public String getDivisionName() { return divisionName; }

    /**
     * getter
     * @return
     */
    public int getCountryID() { return countryID; }


    /**
     * this toString method overrides the default toString method, which returns divisionName instead
     * of a pointer reference to a hash code when firstleveldivisions is displayed in a combo box
     *
     * @return returns division name to be displayed as a string
     */
    @Override
    public String toString() {
        return (divisionName);
    }



}
