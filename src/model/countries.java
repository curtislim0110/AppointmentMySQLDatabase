package model;

/**
 * class for countries, each country is associated with multiple first level divisions like states or provinces
 */
public class countries {

    private int countryID;
    private String countryName;

    /**
     * constructor
     * @param countryID countryID
     * @param countryName country name
     */
    public countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * getter
     * @return
     */
    public String getCountryName() { return countryName; }

    /**
     * getter
     * @return
     */
    public int getCountryID() { return countryID; }

    /**
     * this toString method overrides the default toString method, which returns countryName instead
     * of a pointer reference to a hash code when countries is displayed in a combo box
     *
     * @return countryName
     */
    @Override
    public String toString() {
        return (countryName);
    }

}