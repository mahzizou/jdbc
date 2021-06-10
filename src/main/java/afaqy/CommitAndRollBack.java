package afaqy;

import java.sql.*;
import java.time.LocalDate;

public class CommitAndRollBack {

    public static void main(String[] args) {

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");    //==> com.mysql.cj.jdbc.Driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Arsenal");
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement("insert into employees.employees (emp_no, birth_date, first_name, last_name, gender, hire_date)\n" +
                    "values (?,?,?,?,?,?)");
            preparedStatement.setInt(1, 303);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3, "FirstName");
            preparedStatement.setString(4, "LastName");
            preparedStatement.setString(5, String.valueOf('M'));
            preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                System.out.println("Employee added successfully\n");
            }
            con.commit();
            System.out.println("Transaction is committed successfully\n");
            throw new Exception("Something wasn't done right\n");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
                System.err.println("Transaction is rolling back\n");
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

}