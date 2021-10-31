package com.company.Cust;

import java.util.List;

public interface CustomerDao {

    void addCustomer( Customer customer);
    void updateCustomer (Customer customer);
    void deleteCustomer (int id); //Probably won't use, but it's here as a placeholder

    Customer getCustomerByUserName(String username); //Return the customer with the matching username
    Customer getCustomerByID(int id); //Return the customer with the matching id

    List<Customer> getAllCustomers(); //Return a list of all customers

}
