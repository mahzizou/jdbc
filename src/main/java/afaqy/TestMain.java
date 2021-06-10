package afaqy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMain {

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //==> com.mysql.cj.jdbc.Driver
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Arsenal");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from employees limit 20");

            System.out.println("Number\tFirst Name");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(3));
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
