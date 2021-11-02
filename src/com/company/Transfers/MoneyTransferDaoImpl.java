package com.company.Transfers;

import com.company.Banks.BankAccount;
import com.company.Banks.BankAccountDao;
import com.company.Banks.BankAccountDaoFactory;
import com.company.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoneyTransferDaoImpl implements MoneyTransferDao{

    Connection connection;

    public MoneyTransferDaoImpl(){
        this.connection = ConnectionFactory.getConnection(); //Set up the connection to the db
    }

    @Override
    public void addMoneyTransfer(MoneyTransfer transfer) throws SQLException {
        if(transfer == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "insert into moneytransfers (amount, senderid, receiverid, pending, completed) values (?, ?, ?, ?, ?);";

        PreparedStatement preppedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //Prepare the sql
        preppedStatement.setDouble(1, transfer.getAmount());
        preppedStatement.setInt(2, transfer.getSender());
        preppedStatement.setInt(3, transfer.getRecipient());
        preppedStatement.setBoolean(4,transfer.isPending());
        preppedStatement.setTimestamp(5,transfer.getCompletedOn());

        int rows = preppedStatement.executeUpdate();
        ResultSet resultSet = preppedStatement.getGeneratedKeys(); //Get the id for this new item
        while(resultSet.next()){
            transfer.setTransferID(resultSet.getInt(1)); //Set it into the customer object for convenience
        }
        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Transfer saved to database!");
        }
        else{
            System.out.println("Problem saving this transfer.");
        }
    }

    @Override
    public void updateMoneyTransfer(MoneyTransfer transfer) throws SQLException {
        if(transfer == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "update moneytransfers set amount = ?, senderid = ?, receiverid = ?, pending = ?, completed = ? where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setDouble(1, transfer.getAmount());
        preppedStatement.setInt(2, transfer.getSender());
        preppedStatement.setInt(3, transfer.getRecipient());
        preppedStatement.setBoolean(4,transfer.isPending());
        preppedStatement.setTimestamp(5,transfer.getCompletedOn());
        preppedStatement.setInt(6,transfer.getTransferID());

        int rows = preppedStatement.executeUpdate();
        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Transfer updated!");
        }
        else{
            System.out.println("Problem updating transfer.");
        }
    }

    @Override
    public void deleteMoneyTransfer(int id) throws SQLException {

        String sql = "delete from moneytransfers where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setInt(1, id);

        int rows = preppedStatement.executeUpdate();
        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Transfer deleted!");
        }
        else{
            System.out.println("Problem deleting transfer.");
        }
    }

    @Override
    public void executeTransfer(MoneyTransfer transfer, BankAccount sender, BankAccount receiver) throws SQLException {
        /*
        PROCEDURE IMPLEMENTATION MAYBE
         */
        BankAccountDao bDao = BankAccountDaoFactory.getBankAccountDao(); //Set up interactions with bank accounts

        sender.withdraw(transfer.getAmount()); //Take out the transfer
        bDao.updateBankAccount(sender); //Update

        receiver.deposit(transfer.getAmount()); //Place it into the other account
        bDao.updateBankAccount(receiver); //Update

        transfer.setComplete(); //Mark the transfer as completed
        updateMoneyTransfer(transfer); //Update it in the database

    }

    @Override
    public MoneyTransfer getTransferByID(int tID) throws SQLException {
        MoneyTransfer transfer = new MoneyTransfer();
        String sql = "select * from moneytransfers where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1, tID);

        ResultSet resultSet = preppedStatement.executeQuery();
        resultSet.next();

        if(resultSet != null){ //Found at least 1 record
            int transferid = resultSet.getInt(1);
            double amount = resultSet.getDouble(2);
            int senderID = resultSet.getInt(3);
            int receiverID = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            transfer = new MoneyTransfer(transferid, amount, senderID, receiverID, pending, completed);
        }
        else{
            System.out.println("No transfer with that id");
        }

        return transfer;


    }

    @Override
    public List<MoneyTransfer> getAllTransfersFrom(int senderID) throws SQLException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        String sql = "select * from moneytransfers where senderid = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,senderID);

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int transferid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int senderid = resultSet.getInt(3);
            int receiverID = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            MoneyTransfer transfer = new MoneyTransfer(transferid, balance, senderid, receiverID, pending, completed);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<MoneyTransfer> getAllPendingTransfersFrom(int senderID) throws SQLException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        String sql = "select * from moneytransfers where senderid = ? and pending = true;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,senderID);

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int transferid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int senderid = resultSet.getInt(3);
            int receiverID = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            MoneyTransfer transfer = new MoneyTransfer(transferid, balance, senderid, receiverID, pending, completed);
        }
        return transfers;
    }

    @Override
    public List<MoneyTransfer> getAllCompletedTransfersFrom(int senderID) throws SQLException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        String sql = "select * from moneytransfers where senderid = ? and pending = false;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,senderID);

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int transferid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int senderid = resultSet.getInt(3);
            int receiverID = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            MoneyTransfer transfer = new MoneyTransfer(transferid, balance, senderid, receiverID, pending, completed);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<MoneyTransfer> getAllTransfersTo(int receiverID) throws SQLException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        String sql = "select * from moneytransfers where receiverid = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,receiverID);

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int transferid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int senderid = resultSet.getInt(3);
            int receiverid = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            MoneyTransfer transfer = new MoneyTransfer(transferid, balance, senderid, receiverid, pending, completed);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<MoneyTransfer> getAllPendingTransfersTo(int receiverID) throws SQLException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        String sql = "select * from moneytransfers where receiverid = ? and pending = true;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,receiverID);

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int transferid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int senderid = resultSet.getInt(3);
            int receiverid = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            MoneyTransfer transfer = new MoneyTransfer(transferid, balance, senderid, receiverid, pending, completed);
            transfers.add(transfer);
        }
        return transfers;
    }

    @Override
    public List<MoneyTransfer> getAllCompletedTransfersTo(int receiverID) throws SQLException {
        List<MoneyTransfer> transfers = new ArrayList<>();
        String sql = "select * from moneytransfers where receiverid = ? and pending = false;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,receiverID);

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int transferid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int senderid = resultSet.getInt(3);
            int receiverid = resultSet.getInt(4);
            boolean pending = resultSet.getBoolean(5);
            Timestamp completed = resultSet.getTimestamp(6);
            MoneyTransfer transfer = new MoneyTransfer(transferid, balance, senderid, receiverid, pending, completed);
            transfers.add(transfer);
        }
        return transfers;
    }
}
