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

    public int getCustID() {
        return custID;
    }

    public boolean applyForBankAccount(double initialbalance){
        BankAccount bank = new BankAccount(initialbalance);
        bank.setPending();
        return true;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
