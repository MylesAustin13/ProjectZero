package com.company.Cust;


import com.company.ConnectionFactory;
import com.company.Empl.EmployeeDaoImpl;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    Connection connection;

    public CustomerDaoImpl(){
        this.connection = ConnectionFactory.getConnection(); //Set up the connection to the db
    }
    @Override
    public void addCustomer(Customer customer) throws SQLException {
        if(customer == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "insert into customer (username, password, firstname, lastname, email) values (?, ?, ?, ?, ?);";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setString(1, customer.getUserName());
        preppedStatement.setString(2, customer.getPassword());
        preppedStatement.setString(3, customer.getFirstName());
        preppedStatement.setString(4, customer.getLastName());
        preppedStatement.setString(5, customer.getEmail());

        int rows = preppedStatement.executeUpdate();
        ResultSet resultSet = preppedStatement.getGeneratedKeys(); //Get the id for this new item
        while(resultSet.next()){
            customer.setCustID(resultSet.getInt(1)); //Set it into the customer object for convenience
        }
        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Customer saved to database!");
        }
        else{
            System.out.println("Problem saving this customer.");
        }

    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        if(customer == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "update customer set username = ?, password = ?, firstname = ?, lastname =?, email = ? where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setString(1, customer.getUserName());
        preppedStatement.setString(2, customer.getPassword());
        preppedStatement.setString(3, customer.getFirstName());
        preppedStatement.setString(4, customer.getLastName());
        preppedStatement.setString(5, customer.getEmail());
        preppedStatement.setInt(6, customer.getCustID());

        int rows = preppedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("Customer updated!");
        }
        else{
            System.out.println("Problem updating this customer.");
        }
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String sql = "delete from customer where id = ?";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,id);

        int rows = preppedStatement.executeUpdate();
        if(rows > 0){
            System.out.println("Customer deleted!");
        }
        else{
            System.out.println("Problem deleting this customer: not found.");
        }


    }

    @Override
    public Customer getCustomerByUserName(String username) throws SQLException { //Could overload
        Customer customer = new Customer();
        String sql = "select * from customer where username = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setString(1,username);

        ResultSet resultSet = preppedStatement.executeQuery();
        resultSet.next();

        if(resultSet != null){ //Found at least 1 record
            int custid = resultSet.getInt(1);
            String user = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String firstname = resultSet.getString(4);
            String lastname = resultSet.getString(5);
            String email = resultSet.getString(6);
            customer = new Customer(custid, user, pass, firstname, lastname, email);
        }
        else{
            System.out.println("No customer with that username");
        }

        return customer;
    }

    @Override
    public Customer getCustomerByID(int id) throws SQLException {
        Customer customer = new Customer();
        String sql = "select * from customer where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,id);

        ResultSet resultSet = preppedStatement.executeQuery();
        resultSet.next();

        if(resultSet != null){ //Found at least 1 record
            int custid = resultSet.getInt(1);
            String user = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String firstname = resultSet.getString(4);
            String lastname = resultSet.getString(5);
            String email = resultSet.getString(6);
            customer = new Customer(custid, user, pass, firstname, lastname, email);
        }
        else{
            System.out.println("No customer with that id");
        }

        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "select * from customer;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int custid = resultSet.getInt(1);
            String user = resultSet.getString(2);
            String pass = resultSet.getString(3);
            String firstname = resultSet.getString(4);
            String lastname = resultSet.getString(5);
            String email = resultSet.getString(6);
            Customer customer = new Customer(custid, user, pass, firstname, lastname, email);
            customers.add(customer);
        }

        return customers;
    }
}
