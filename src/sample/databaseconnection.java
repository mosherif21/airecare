package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseconnection {
    public static Statement  test_database() throws SQLException {

        Connection con=DriverManager.getConnection("jdbc:sqlserver://LAPTOP-PL2T5KEJ;instanceName=SQLEXPRESS;integratedSecurity=true;");
        Statement st=con.createStatement();
       return st;
    }

}
