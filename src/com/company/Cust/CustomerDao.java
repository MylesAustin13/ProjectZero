package com.company.Cust;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    void addCustomer( Customer customer) throws SQLException;
    void updateCustomer (Customer customer) throws SQLException;
    void deleteCustomer (int id) throws SQLException; //Probably won't use, but it's here as a placeholder

    Customer getCustomerByUserName(String username) throws SQLException; //Return the customer with the matching username
    Customer getCustomerByID(int id) throws SQLException; //Return the customer with the matching id

    List<Customer> getAllCustomers() throws SQLException; //Return a list of all customers

}
