package com.company.Transfers;

import com.company.Banks.BankAccount;

import java.sql.SQLException;
import java.util.List;

public interface MoneyTransferDao {
    void addMoneyTransfer(MoneyTransfer transfer) throws SQLException;
    void updateMoneyTransfer(MoneyTransfer transfer) throws SQLException;
    void deleteMoneyTransfer(int id) throws SQLException;

    void executeTransfer(MoneyTransfer transfer, BankAccount sender, BankAccount receiver) throws SQLException;

    MoneyTransfer getTransferByID(int tID) throws SQLException;

    List<MoneyTransfer> getAllTransfersFrom(int senderID) throws SQLException;              //Get all the transfers where the sender is this bank account
    List<MoneyTransfer> getAllPendingTransfersFrom(int senderID) throws SQLException;       //Get all the unfinished transfers where the sender is this bank account
    List<MoneyTransfer> getAllCompletedTransfersFrom(int senderID) throws SQLException;     //Get all the finished transfers where the sender is this bank account (LOGGING)

    List<MoneyTransfer> getAllTransfersTo(int receiverID) throws SQLException;              //Get all the transfers where the destination is this bank account
    List<MoneyTransfer> getAllPendingTransfersTo(int receiverID) throws SQLException;       //Get all the unfinished transfers where the destination is this bank account
    List<MoneyTransfer> getAllCompletedTransfersTo(int receiverID) throws SQLException;     //Get all the finished transfers where the destination is this bank account (LOGGING)
}
