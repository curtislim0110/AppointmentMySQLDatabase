package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class countriesDAO {
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
}
