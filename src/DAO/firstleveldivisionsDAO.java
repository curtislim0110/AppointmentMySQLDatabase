package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.firstleveldivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for databae queries related to the first level divisions table.
 */
public class firstleveldivisionsDAO {

    /**
     * This static method returns a list
     * @param countryID countryID
     * @return returns a list of first level division objects related to the input countryID
     */
    public static ObservableList<firstleveldivisions> showFirstLevels(int countryID) {
        ObservableList<firstleveldivisions> firstDivisionListOfCountry = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryID;
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                countryID = rs.getInt("Country_ID");

                firstleveldivisions currentfirstlevel = new firstleveldivisions(divisionID, divisionName, countryID);
                firstDivisionListOfCountry.add(currentfirstlevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstDivisionListOfCountry;
    }

}
