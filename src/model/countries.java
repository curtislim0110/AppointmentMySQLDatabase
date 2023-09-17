package model;

public class countries {

    private int countryID;
    private String countryName;

    public countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    // Getters
    public String getCountryName() { return countryName; }
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