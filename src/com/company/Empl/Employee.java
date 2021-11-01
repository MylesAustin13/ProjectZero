package com.company.Empl;

import com.company.Banks.BankAccount;
import com.company.Cust.Customer;

import java.util.List;

public class Employee {
    private int emplID;             //Unique identifier for employee
    private String user_name;
    private String password;
    private String first_name;
    private String last_name;
    private String email;

    public Employee() {
    }

    public Employee(int emplID, String user_name, String password, String first_name, String last_name, String email) {
        this.emplID = emplID;
        this.user_name = user_name;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public void approveAccount(BankAccount other){ //Approve the pending account
        other.getApproved();
    }

    public void rejectAccount(BankAccount other){ //Reject the pending account
        other.getRejected();
    }

    public List<BankAccount> getCustomerBankAccounts(Customer other){
        return other.getBankAccounts();
    }
    public List<BankAccount> getPendingCustomerBankAccounts(Customer other){
        return other.getPendingBankAccounts();
    }


    public int getEmplID(){
        return emplID;
    }

    public void setEmplID(int id){
        emplID = id;
    }
    public String viewLogs(){
        return "lol we didnt learn about this but this placeholder will remind me later i hope";
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
