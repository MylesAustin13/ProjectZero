package com.company.Cust;

import com.company.Banks.BankAccount;

import java.util.List;

public class Customer {
    private int custID;                        //Unique ID for the customer
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String email;

    private List<BankAccount> accounts;        //A list of all the VALID owned user accounts
    private List<BankAccount> pendingAccounts; // A list of all the PENDING user accounts for this customer

    public List<BankAccount> getBankAccounts(){
        return accounts;
    }

    public Customer() {
    }

    public Customer(int custID, String user_name, String password, String first_name, String last_name, String email) {
        this.custID = custID;
        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int id) {
        custID = id;
    }

    public boolean applyForBankAccount(double initialbalance){
        BankAccount bank = new BankAccount(initialbalance);
        bank.setPending();
        return true;
    }

    public String getUserName() {
        return user_name;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
