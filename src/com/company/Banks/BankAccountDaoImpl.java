package com.company.Banks;

import com.company.Banks.BankAccountDao;
import com.company.ConnectionFactory;
import com.company.Cust.Customer;
import com.company.Empl.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDaoImpl implements BankAccountDao {
    Connection connection;

    public BankAccountDaoImpl(){
        this.connection = ConnectionFactory.getConnection(); //Set up the connection to the db
    }

    @Override
    public void addBankAccount(BankAccount account) throws SQLException {
        if(account == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "insert into bankaccounts (balance, ownerid, pending, approved, rejected) values (?, ?, ?, ?, ?);";

        PreparedStatement preppedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); //Prepare the sql
        preppedStatement.setDouble(1, account.getBalance());
        preppedStatement.setInt(2, account.getOwnerID());
        preppedStatement.setBoolean(3, account.getPendingStatus());
        preppedStatement.setBoolean(4, account.getApprovalStatus());
        preppedStatement.setBoolean(5, account.getRejectionStatus());

        int rows = preppedStatement.executeUpdate();
        ResultSet resultSet = preppedStatement.getGeneratedKeys(); //Get the id for this new item
        while(resultSet.next()){
            account.setBID(resultSet.getInt(1)); //Set it into the account object for convenience
        }
        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Account saved to database!");
        }
        else{
            System.out.println("Problem saving this account.");
        }
    }

    @Override
    public void updateBankAccount(BankAccount account) throws SQLException {
        if(account == null){
            System.out.println("Null object provided. Cancelling...");
            return;
        }
        String sql = "update bankaccounts set balance = ?, ownerid = ?, pending = ?, approved = ?, rejected = ? where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setDouble(1, account.getBalance());
        preppedStatement.setInt(2, account.getOwnerID());
        preppedStatement.setBoolean(3, account.getPendingStatus());
        preppedStatement.setBoolean(4, account.getApprovalStatus());
        preppedStatement.setBoolean(5, account.getRejectionStatus());
        preppedStatement.setInt(6,account.getBID());

        int rows = preppedStatement.executeUpdate();

        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Account updated!");
        }
        else{
            System.out.println("Problem updating this account.");
        }
    }

    @Override
    public void deleteBankAccount(int id) throws SQLException {
        String sql = "delete from bankaccounts where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql); //Prepare the sql
        preppedStatement.setInt(1,id);

        int rows = preppedStatement.executeUpdate();

        if(rows > 0){ //If at least 1 row affected, success! (CONSIDER HANDLING CASE WHERE ROW > 1)
            System.out.println("Account deleted!");
        }
        else{
            System.out.println("Problem deleting this account.");
        }
    }

    @Override
    public BankAccount getBankAccountByID(int id) throws SQLException {
        BankAccount account = new BankAccount();
        String sql = "select * from bankaccounts where id = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,id);

        ResultSet resultSet = preppedStatement.executeQuery();
        resultSet.next();

        if(resultSet != null){ //Found at least 1 record
            int bankid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int ownerid = resultSet.getInt(3);
            boolean pending = resultSet.getBoolean(4);
            boolean approved = resultSet.getBoolean(5);
            boolean rejected = resultSet.getBoolean(6);

            account = new BankAccount(bankid,balance,pending,approved,rejected,ownerid);
        }
        else{
            System.out.println("No account with that id");
        }

        return account;
    }

    @Override
    public List<BankAccount> getAllApprovedBankAccountsOwned(Customer customer) throws SQLException {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "select * from bankaccounts where ownerid = ? and approved = true;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,customer.getCustID());

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int bankid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int ownerid = resultSet.getInt(3);
            boolean pending = resultSet.getBoolean(4);
            boolean approved = resultSet.getBoolean(5);
            boolean rejected = resultSet.getBoolean(6);

            BankAccount account = new BankAccount(bankid,balance,pending,approved,rejected,ownerid);
        }
        return accounts;
    }

    @Override
    public List<BankAccount> getAllPendingBankAccountsOwned(Customer customer) throws SQLException {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "select * from bankaccounts where ownerid = ? and pending = true;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,customer.getCustID());

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int bankid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int ownerid = resultSet.getInt(3);
            boolean pending = resultSet.getBoolean(4);
            boolean approved = resultSet.getBoolean(5);
            boolean rejected = resultSet.getBoolean(6);

            BankAccount account = new BankAccount(bankid,balance,pending,approved,rejected,ownerid);
        }
        return accounts;
    }

    @Override
    public List<BankAccount> getAllRejectedBankAccountsOwned(Customer customer) throws SQLException {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "select * from bankaccounts where ownerid = ? and rejected = true;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,customer.getCustID());

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int bankid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int ownerid = resultSet.getInt(3);
            boolean pending = resultSet.getBoolean(4);
            boolean approved = resultSet.getBoolean(5);
            boolean rejected = resultSet.getBoolean(6);

            BankAccount account = new BankAccount(bankid,balance,pending,approved,rejected,ownerid);
        }
        return accounts;
    }

    @Override
    public List<BankAccount> getAllBankAccountsOwned(Customer customer) throws SQLException {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "select * from bankaccounts where ownerid = ?;";

        PreparedStatement preppedStatement = connection.prepareStatement(sql);
        preppedStatement.setInt(1,customer.getCustID());

        ResultSet resultSet = preppedStatement.executeQuery();

        while(resultSet.next()){
            int bankid = resultSet.getInt(1);
            double balance = resultSet.getDouble(2);
            int ownerid = resultSet.getInt(3);
            boolean pending = resultSet.getBoolean(4);
            boolean approved = resultSet.getBoolean(5);
            boolean rejected = resultSet.getBoolean(6);

            BankAccount account = new BankAccount(bankid,balance,pending,approved,rejected,ownerid);
        }
        return accounts;
    }


}
