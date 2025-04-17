package fr.diginamic.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCCall {
    public static void executeRequest(String request){
        ResourceBundle config = ResourceBundle.getBundle("database");

        String url = config.getString("db.url");
        String user = config.getString("db.user");

        Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = DriverManager.getConnection(url, user, "")) {

            stmt = conn.createStatement();
            rs = stmt.executeQuery(request);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nom"));
            }

        } catch (SQLException e) {
            System.err.println(e);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (stmt != null){
                    stmt.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
                e.printStackTrace();
            }

        }
    }
}
