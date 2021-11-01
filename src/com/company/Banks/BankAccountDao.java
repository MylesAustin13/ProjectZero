package com.company.Banks;

import com.company.Cust.Customer;

import java.sql.SQLException;
import java.util.List;

public interface BankAccountDao {

    void addBankAccount( BankAccount account) throws SQLException;
    void updateBankAccount( BankAccount account) throws SQLException;
    void deleteBankAccount (int id) throws SQLException;

    BankAccount getBankAccountByID(int id) throws SQLException;
    List<BankAccount> getAllApprovedBankAccountsOwned(Customer customer) throws SQLException; //All the approved accounts owned by a customer
    List<BankAccount> getAllPendingBankAccountsOwned(Customer customer) throws SQLException; //All the finished accounts owned by a customer

    List<BankAccount> getAllRejectedBankAccountsOwned(Customer customer) throws SQLException; //All the rejected accounts owned by a customer

    List<BankAccount> getAllBankAccountsOwned(Customer customer) throws SQLException; //All the approved accounts owned by a customer





}
