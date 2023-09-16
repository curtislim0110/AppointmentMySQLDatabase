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
}



