package afaqy;

import java.sql.*;

public class NewCRUDOperations {

    private Connection con;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String result = "";

    public NewCRUDOperations(){

    }

    private Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");    //==> com.mysql.cj.jdbc.Driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Arsenal");
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }

    private void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String addDepartment(String depID, String newName){
        try {
            preparedStatement = getConnection().prepareStatement("insert into employees.departments values (?,?)");
            preparedStatement.setString(1, depID);
            preparedStatement.setString(2, newName);
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                result = "Department added successfully\n";
            }
            preparedStatement.close();
            getConnection().close();
        } catch (Exception e){
            result = "Something Went Wrong\n";
            e.printStackTrace();
        }
        return result;
    }

    public String getDepartmentByID(String depID){
        try {
            preparedStatement = getConnection().prepareStatement("SELECT * FROM employees.departments WHERE dept_no=?");
            preparedStatement.setString(1, depID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                result = "Department name: " + resultSet.getString(2) + "\n";
            }
            resultSet.close();
            preparedStatement.close();
            getConnection().close();
        } catch (Exception e){
            result = "Something Went Wrong\n";
            e.printStackTrace();
        }
        return result;
    }

    public String updateDepartmentName(String depID, String newName){
        try {
            preparedStatement = getConnection().prepareStatement("update employees.departments set dept_name = ? where dept_no = ? ");
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, depID);
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                result = "Department updated successfully\n" +
                        "Department new details: [" + depID + " " + newName + "]\n";
            }
            preparedStatement.close();
            getConnection().close();
        } catch (Exception e){
            result = "Something Went Wrong\n";
            e.printStackTrace();
        }
        return result;
    }

    public String deleteDepartment(String depID){
        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM employees.departments WHERE dept_no = ?");
            preparedStatement.setString(1, depID);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            getConnection().close();
            if(i>0) {
                result = "Department deleted successfully\n";
            }
        } catch (Exception e){
            result = "Something Went Wrong\n";
            e.printStackTrace();
        }
        return result;
    }

    public String addEmployee(int empId, String firstName, String lastName, char gender, Date birthDate, Date hireDate ){
        try {
            preparedStatement = getConnection().prepareStatement("insert into employees.employees (emp_no, birth_date, first_name, last_name, gender, hire_date)\n" +
                    "values (?,?,?,?,?,?)");
            preparedStatement.setInt(1, empId);
            preparedStatement.setDate(2, birthDate);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, String.valueOf(gender));
            preparedStatement.setDate(6, hireDate);
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                String s = "";
                result = "Employee added successfully\n" +
                        "Employee details: [" + empId + " " + firstName + " " + lastName + " " + (s = (gender =='M') ? "Male" : "Female") +
                        " " + birthDate.toString() + " " + hireDate.toString() + "]\n";
            }
            preparedStatement.close();
            getConnection().close();
        } catch (Exception e){
            result = "Something Went Wrong\n";
            e.printStackTrace();
        }
        return result;
    }

    public String deleteEmployee(int empId){
        try {
            preparedStatement = getConnection().prepareStatement("DELETE FROM employees.employees WHERE emp_no = ?");
            preparedStatement.setInt(1, empId);
            int i = preparedStatement.executeUpdate();
            if(i>0) {
                result = "Employee deleted successfully";
            }
            preparedStatement.close();
            getConnection().close();
        } catch (Exception e){
            result = "Something Went Wrong\n";
            e.printStackTrace();
        }
        return result;
    }

}

