package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for database queries related to countries objects
 */
public class countriesDAO {

    /**
     * This method gets a list of all countries in the database
     * @return list of countries
     */
    public static ObservableList<countries> getAllCountries() {
        ObservableList<countries> countriesList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT Country_ID, Country FROM countries";
            PreparedStatement country = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = country.executeQuery();

            while (rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                countries currentcountry = new countries(countryID, countryName);
                countriesList.add(currentcountry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countriesList;
    }

    /**
     * This method gets a single country name from the database, based on the countryID
     * @param countryID
     * @return country name
     */
    public static String getCountryName(int countryID) {
        try {
            String sql = "SELECT Country FROM countries WHERE Country_ID = " + countryID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();
            rs.next();
            return rs.getString("Country");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
