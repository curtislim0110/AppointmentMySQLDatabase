package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.reportsorted;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for generating reports related to customer appointments sorted by type and month
 */
public class reportsortedDAO {

    /**
     * This static method queries the database for a list of appointments with columns for the starting date
     * (in month form), the type, and a total count of appointments by each month and type.  A list of these
     * appointments is returned for display in a report tableview.
     * @return returns a list of appointments sorted by the SQL database
     */
    public static ObservableList<reportsorted> getMonthTypeReport() {
        ObservableList<reportsorted> reportlist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT monthname(Start), Type, Count(*) AS Totalcount FROM appointments GROUP BY monthname(Start), Type";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rsGetAll = ps.executeQuery();

            while(rsGetAll.next()) {
                String month = rsGetAll.getString("monthname(Start)");
                String type = rsGetAll.getString("Type");
                int totalcount = rsGetAll.getInt("Totalcount");
                reportsorted currentreport = new reportsorted(month, type, totalcount);
                reportlist.add(currentreport);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
            }
        return reportlist;
    }
}
