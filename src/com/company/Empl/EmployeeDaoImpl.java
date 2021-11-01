package com.company.Empl;

import com.company.ConnectionFactory;
import com.company.Cust.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    Connection connection;

    public EmployeeDaoImpl(){
        this.connection = ConnectionFactory.getConnection(); //Set up the connection to the db
    }
    @Override
    public void addEmployee(Employee employee) throws SQLException {
        if(employee == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "insert into employee (username, password, firstname, lastname, email) values (?, ?, ?, ?, ?);";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setString(1, employee.getUserName());
        preppedStatement.setString(2, employee.getPassword());
        preppedStatement.setString(3, employee.getFirstName());
        preppedStatement.setString(4, employee.getLastName());
        preppedStatement.setString(5, employee.getEmail());

        int rows = preppedStatement.executeUpdate();
        ResultSet resultSet = preppedStatement.getGeneratedKeys(); //Get the id for this new item
        while(resultSet.next()){
            employee.setEmplID(resultSet.getInt(1)); //Set it into the employee object for convenience
        }
        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Employee saved to database!");
        }
        else{
            System.out.println("Problem saving this employee.");
        }
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        if(employee == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "update employee set username = ?, password = ?, firstname = ?, lastname =?, email = ? where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setString(1, employee.getUserName());
        preppedStatement.setString(2, employee.getPassword());
        preppedStatement.setString(3, employee.getFirstName());
        preppedStatement.setString(4, employee.getLastName());
        preppedStatement.setString(5, employee.getEmail());
        preppedStatement.setInt(6, employee.getEmplID());

        int rows = preppedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("Employee updated!");
        }
        else{
            System.out.println("Problem updating this employee.");
        }
    }

    @Override
    public void deleteEmployee(int id) throws SQLException {
        String sql = "delete from employee where id = ?";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,id);

        int rows = preppedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("Employee deleted!");
        }
        else{
            System.out.println("Problem deleting this employee: not found.");
        }
    }

    @Override
    public Employee getEmployeeByUserName(String username) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from employee where username = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setString(1,username);

        ResultSet resultSet = preppedStatement.executeQuery();
        resultSet.next();

        if(resultSet != null){ //Found at least 1 record
            int emplid = resultSet.getInt(1);
            String user = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String firstname = resultSet.getString(4);
            String lastname = resultSet.getString(5);
            String email = resultSet.getString(6);
            employee = new Employee(emplid, user, pass, firstname, lastname, email);
        }
        else{
            System.out.println("No employee with that username");
        }

        return employee;
    }

    @Override
    public Employee getEmployeeByID(int id) throws SQLException {
        Employee employee = new Employee();
        String sql = "select * from employee where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,id);

        ResultSet resultSet = preppedStatement.executeQuery();
        resultSet.next();

        if(resultSet != null){ //Found at least 1 record
            int emplid = resultSet.getInt(1);
            String user = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String firstname = resultSet.getString(4);
            String lastname = resultSet.getString(5);
            String email = resultSet.getString(6);
            employee = new Employee(emplid, user, pass, firstname, lastname, email);
        }
        else{
            System.out.println("No employee with that id");
        }

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int emplid = resultSet.getInt(1);
            String user = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String firstname = resultSet.getString(4);
            String lastname = resultSet.getString(5);
            String email = resultSet.getString(6);
            Employee employee = new Employee(emplid, user, pass, firstname, lastname, email);
            employees.add(employee);
        }

        return employees;
    }
}
