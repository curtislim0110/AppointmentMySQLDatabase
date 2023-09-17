package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.firstleveldivisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class firstleveldivisionsDAO {

    public static ObservableList<firstleveldivisions> getAllFirstLevelDivisions() {
        ObservableList<firstleveldivisions> firstDivisionsList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT  * FROM first_level_divisions";
            PreparedStatement psSELECT = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rs = psSELECT.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                firstleveldivisions currentdivision = new firstleveldivisions(divisionID, divisionName, countryID);
                firstDivisionsList.add(currentdivision);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstDivisionsList;
    }

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
