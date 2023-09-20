package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.monthtypereports;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class monthtypereportsDAO {

    public static ObservableList<monthtypereports> getMonthTypeReport() {

        ObservableList<monthtypereports> reportlist = FXCollections.observableArrayList();
        try{
            String sql = "SELECT monthname(Start), Type, Count(*) AS Totalcount FROM appointments GROUP BY monthname(Start), Type";
            PreparedStatement ps = JDBC.JDBCconnection.prepareStatement(sql);
            ResultSet rsGetAll = ps.executeQuery();

            while(rsGetAll.next()) {
                String month = rsGetAll.getString("monthname(Start)");
                String type = rsGetAll.getString("Type");
                int totalcount = rsGetAll.getInt("Totalcount");
                monthtypereports currentreport = new monthtypereports(month, type, totalcount);
                reportlist.add(currentreport);
                }
            }
        catch (SQLException e) {
            e.printStackTrace();
            }
        return reportlist;
    }
}
