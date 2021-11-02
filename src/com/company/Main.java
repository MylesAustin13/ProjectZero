package com.company;

import com.company.Banks.BankAccount;
import com.company.Banks.BankAccountDao;
import com.company.Banks.BankAccountDaoFactory;
import com.company.Cust.Customer;
import com.company.Cust.CustomerDao;
import com.company.Cust.CustomerDaoFactory;
import com.company.Empl.Employee;
import com.company.Empl.EmployeeDao;
import com.company.Empl.EmployeeDaoFactory;
import com.company.Transfers.MoneyTransfer;
import com.company.Transfers.MoneyTransferDao;
import com.company.Transfers.MoneyTransferDaoFactory;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
	    // Daos
        CustomerDao custDao = CustomerDaoFactory.getCustomerDao();
        EmployeeDao emplDao = EmployeeDaoFactory.getEmployeeDao();
        MoneyTransferDao xferDao = MoneyTransferDaoFactory.getMoneyTransferDao();
        BankAccountDao bankDao = BankAccountDaoFactory.getBankAccountDao();

        Scanner scanner = new Scanner(System.in);

        boolean user_currently_active = true;
        boolean user_logged_in = false;
        int user_type = 0; //0 = none, 1 = customer, 2 = employee
        int new_user; //0 = none, 1 = new, 2 = returning
        int customer_choice;
        int employee_choice;
        String uname;
        String fname;
        String lname;
        String email;
        String password;

        Customer activeCustomer = null;
        Employee activeEmployee = null;

        while(user_currently_active){
            System.out.println("Are you a new customer or returning user?");
            System.out.println("1: New customer");
            System.out.println("2: Returning user");
            System.out.println("0: Exit");
            new_user = scanner.nextInt();
            scanner.nextLine(); //cleanup
            System.out.println("--------------------------------------------------------");
            switch(new_user){ //HANDLE LOGIN
                case 1: ////////////////////////////////////////////////////////NEW USER
                    /*
                    System.out.println("Will you be creating a customer account");
                    System.out.println("1: Proceed");
                    System.out.println("0: Return");
                    user_type = scanner.nextInt();
                    scanner.nextLine(); //Cleanup
                    */
                    Customer newCustomer = new Customer();
                    System.out.println("Enter a username.");
                    uname = scanner.nextLine(); //Being explicit here for safety
                    newCustomer.setUserName(uname);
                    System.out.println("Enter your first name.");
                    fname = scanner.nextLine();
                    newCustomer.setFirstName(fname);
                    System.out.println("Enter your last name.");
                    lname = scanner.nextLine();
                    newCustomer.setLastName(lname);
                    System.out.println("Enter your email.");
                    email = scanner.nextLine();
                    newCustomer.setEmail(email);
                    System.out.println("Enter a password.");
                    password = scanner.nextLine();
                    newCustomer.setPassword(password);
                    try {
                        custDao.addCustomer(newCustomer);
                    }
                    catch (SQLIntegrityConstraintViolationException e){
                        System.out.println("That user name is taken!");
                        e.printStackTrace();
                    }
                    catch (SQLException e) {
                        System.out.println("There may be an issue with the sql query. Please check for typos!");
                        e.printStackTrace();
                    }
                    /*
                    switch(user_type){
                        case 1: /////////////////////////////////////////////////////////////////////////////////NEW CUSTOMER
                            Customer newCustomer = new Customer();
                            System.out.println("Enter a username.");
                            uname = scanner.nextLine(); //Being explicit here for safety
                            newCustomer.setUserName(uname);
                            System.out.println("Enter your first name.");
                            fname = scanner.nextLine();
                            newCustomer.setFirstName(fname);
                            System.out.println("Enter your last name.");
                            lname = scanner.nextLine();
                            newCustomer.setLastName(lname);
                            System.out.println("Enter your email.");
                            email = scanner.nextLine();
                            newCustomer.setEmail(email);
                            System.out.println("Enter a password.");
                            password = scanner.nextLine();
                            newCustomer.setPassword(password);
                            try {
                                custDao.addCustomer(newCustomer);
                            }
                            catch (SQLIntegrityConstraintViolationException e){
                                System.out.println("That user name is taken!");
                                e.printStackTrace();
                            }
                            catch (SQLException e) {
                                System.out.println("There may be an issue with the sql query. Please check for typos!");
                                e.printStackTrace();
                            }
                            break;
                        /*case 2: /////////////////////////////////////////////////////////////////////////////////NEW EMPLOYEE (SKIP FOR NOW)
                            Employee newEmployee = new Employee();
                            System.out.println("Enter a username.");
                            uname = scanner.nextLine(); //Being explicit here for safety
                            newEmployee.setUserName(uname);
                            System.out.println("Enter your first name.");
                            fname = scanner.nextLine();
                            newEmployee.setFirstName(fname);
                            System.out.println("Enter your last name.");
                            lname = scanner.nextLine();
                            newEmployee.setLastName(lname);
                            System.out.println("Enter your email.");
                            email = scanner.nextLine();
                            newEmployee.setEmail(email);
                            System.out.println("Enter a password.");
                            password = scanner.nextLine();
                            newEmployee.setPassword(password);

                            try {
                                emplDao.addEmployee(newEmployee);
                            }
                            catch (SQLIntegrityConstraintViolationException e){
                                System.out.println("That user name is taken!");
                                e.printStackTrace();
                            }
                            catch (SQLException e) {
                                System.out.println("There may be an issue with the sql query. Check for any typos!");
                                e.printStackTrace();
                            }
                            break;
                        default: ////////////////////////////////////////////////////////////////////////////////WRONG INPUT
                            user_currently_active = false; //break out of loop for now
                            break;
                    } */
                    break;
                case 2: ///////////////////////////////////////////////////////RETURNING USER
                    System.out.println("Select the account type you will be logging into.");
                    System.out.println("1: Customer Account");
                    System.out.println("2: Employee Account");
                    user_type = scanner.nextInt();
                    scanner.nextLine(); //Cleanup
                    switch (user_type){
                        case 1: /////////////////////////////////////////////////////////////////////////////////RETURNING CUSTOMER
                            System.out.println("Enter username: ");
                            uname = scanner.nextLine();
                            try {
                                activeCustomer = custDao.getCustomerByUserName(uname);
                            } catch (SQLException e) {
                                System.out.println("Check the sql for typos!");
                                e.printStackTrace();
                            }
                            if(activeCustomer == null){ //Login failed, username not in table.
                                user_logged_in = false; //Set it again, just to make sure
                                System.out.println("No customer with that username is present. Try again.");
                            }
                            else{ //Try to insert password
                                System.out.println("Enter password: ");
                                password = scanner.nextLine();

                                if(password.equals(activeCustomer.getPassword())){
                                    user_logged_in = true;
                                    System.out.println("Logged in successfully!");
                                }
                                else{
                                    user_logged_in = false;
                                    System.out.println("Password incorrect. Try again.");
                                }
                            }
                            break;
                        case 2: ////////////////////////////////////////////////////////////////////////////////RETURNING EMPLOYEE
                            System.out.println("Enter username: ");
                            uname = scanner.nextLine();
                            try {
                                activeEmployee = emplDao.getEmployeeByUserName(uname);
                            } catch (SQLException e) {
                                System.out.println("Check the sql for typos!");
                                e.printStackTrace();
                            }
                            if(activeEmployee == null){ //Login failed, username not in table.
                                user_logged_in = false; //Set it again, just to make sure
                                System.out.println("No customer with that username is present. Try again.");
                            }
                            else{ //Try to insert password
                                System.out.println("Enter password: ");
                                password = scanner.nextLine();

                                if(password.equals(activeEmployee.getPassword())){
                                    user_logged_in = true;
                                    System.out.println("Logged in successfully!");
                                }
                                else{
                                    user_logged_in = false; //Reset to be safe
                                    System.out.println("Password incorrect. Try again.");
                                }
                            }
                            break;
                        default:
                            break;
                    }

                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:///////////////////////////////////////////////////////WRONG INPUT
                    user_currently_active = false; //break out of loop for now
                    break;
            }
            //IF LOGGED IN, HANDLE FUNCTIONS
            while(user_logged_in){
                customer_choice = 0; //Reset choices for loops
                employee_choice = 0;
                boolean valid_source = false; //Whether the given id/username is applicable
                switch (user_type){
                    case 1: //////////////////////////////////////////////////CUSTOMER
                        System.out.println("Welcome, " + activeCustomer.getUserName() + "! What would you like to do today?");
                        System.out.println("1: View Summary of Active Accounts");
                        System.out.println("2: View Balance of Specific Account");
                        System.out.println("3: Make a Deposit to an Account");
                        System.out.println("4: Make a Withdrawal from an Account");
                        System.out.println("5: Initiate a Transfer of Funds to an Account");
                        System.out.println("6: Accept a Transfer from an Account");
                        System.out.println("7: Apply for a New Account");
                        System.out.println("0: Log out");
                        customer_choice = scanner.nextInt();
                        switch (customer_choice){
                            case 1: ///////////////////////////////////////////////////////////////////////View all active bank accounts on customer
                                try {
                                    List<BankAccount> myAccounts = bankDao.getAllApprovedBankAccountsOwned(activeCustomer);
                                    for(BankAccount account : myAccounts){
                                        System.out.println(account); //print the account no. and balance
                                        List<MoneyTransfer> pendingTransfers = xferDao.getAllPendingTransfersTo(account.getBID()) ;//Get all the transfers to this account
                                        if(pendingTransfers.size() == 0){
                                            System.out.println("\t No pending transfers to be accepted.");
                                        }
                                        else{
                                            System.out.println("\t Pending Transfers to this account");
                                            for(MoneyTransfer transfer : pendingTransfers){
                                                System.out.println("\t" + transfer + " from Account No. " + transfer.getSender());
                                            }
                                        }

                                    }
                                } catch (SQLException e) {
                                    System.out.println("Check SQL for typos!");
                                    e.printStackTrace();
                                }

                                break;
                            case 2: //////////////////////////////////////////////View balance of one account
                                List<BankAccount> myOwnedAccounts = null;

                                try {
                                    myOwnedAccounts = bankDao.getAllApprovedBankAccountsOwned(activeCustomer);

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                if(myOwnedAccounts.isEmpty()){ //This user doesnt have any accounts
                                    System.out.println("You don't have any active accounts.");
                                    break;
                                }
                                List<Integer> valid_view_ids = new ArrayList<>();
                                for(BankAccount account : myOwnedAccounts){ //Create a list of accounts the user can pick from
                                    valid_view_ids.add(account.getBID());
                                }
                                System.out.println("Input the id of the account: ");
                                int bID = scanner.nextInt();
                                if(valid_view_ids.contains(bID)){
                                    valid_source = true;
                                }
                                else{
                                    valid_source = false;
                                }
                                while(!valid_source){ //Make sure the customer actually owns the account they are taking money from
                                    if(valid_view_ids.contains(bID)){
                                        valid_source = true;
                                    }
                                    else{
                                        System.out.println("Invalid account number. You may choose from your accounts: ");
                                        for(BankAccount account : myOwnedAccounts){ //show the user the ids
                                            System.out.println(account);
                                        }
                                        bID = scanner.nextInt();
                                    }

                                }

                                try {//Print the bank account no. and balance
                                    System.out.println(bankDao.getBankAccountByID(bID));

                                } catch (SQLException e) {
                                    System.out.println("Check SQL for typos.");
                                    e.printStackTrace();
                                }
                                break;
                            case 3: //////////////////////////////////////////////////////////////Deposit
                                List<BankAccount> myBankingAccounts = null;

                                try {
                                    myBankingAccounts = bankDao.getAllApprovedBankAccountsOwned(activeCustomer);

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                if(myBankingAccounts.isEmpty()){ //This user doesn't have any accounts
                                    System.out.println("You don't have any active accounts.");
                                    break;
                                }
                                List<Integer> valid_deposit_ids = new ArrayList<>();
                                for(BankAccount account : myBankingAccounts){ //Create a list of accounts the user can pick from
                                    valid_deposit_ids.add(account.getBID());
                                }
                                System.out.println("Input the id of the account: ");
                                int accID = scanner.nextInt();
                                if(valid_deposit_ids.contains(accID)){
                                    valid_source = true;
                                }
                                else{
                                    valid_source = false;
                                }
                                while(!valid_source){ //Make sure the customer actually owns the account they are putting money into
                                    if(valid_deposit_ids.contains(accID)){
                                        valid_source = true;
                                    }
                                    else{
                                        System.out.println("Invalid account number. You may choose from your accounts: ");
                                        for(BankAccount account : myBankingAccounts){ //show the user the ids
                                            System.out.println(account);
                                        }
                                        accID = scanner.nextInt();
                                    }

                                }
                                System.out.println("Input the amount you wish to deposit");
                                double amount = scanner.nextDouble();
                                try {
                                    BankAccount acc = bankDao.getBankAccountByID(accID); //get the bank
                                    boolean deposited = acc.deposit(amount); //Deposit into it
                                    if(deposited) //If we actually made a change
                                        bankDao.updateBankAccount(acc); //update the database

                                } catch (SQLException e) {
                                    System.out.println("Check SQL for typos.");
                                    e.printStackTrace();
                                }
                                break;
                            case 4: ///////////////////////////////////////////////////////////////////Withdraw

                                List<BankAccount> myBankAccounts = null;

                                try {
                                    myBankAccounts = bankDao.getAllApprovedBankAccountsOwned(activeCustomer);

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                if(myBankAccounts.isEmpty()){ //This user doesnt have any accounts
                                    System.out.println("You don't have any active accounts.");
                                    break;
                                }
                                List<Integer> valid_withdraw_ids = new ArrayList<>();
                                for(BankAccount account : myBankAccounts){ //Create a list of accounts the user can pick from
                                    valid_withdraw_ids.add(account.getBID());
                                }
                                System.out.println("Input the id of the account: ");
                                int bankID = scanner.nextInt();
                                if(valid_withdraw_ids.contains(bankID)){
                                    valid_source = true;
                                }
                                else{
                                    valid_source = false;
                                }
                                while(!valid_source){ //Make sure the customer actually owns the account they are taking money from
                                    if(valid_withdraw_ids.contains(bankID)){
                                        valid_source = true;
                                    }
                                    else{
                                        System.out.println("Invalid account number. You may choose from your accounts: ");
                                        for(BankAccount account : myBankAccounts){ //show the user the ids
                                            System.out.println(account);
                                        }
                                        bankID = scanner.nextInt();
                                    }

                                }

                                System.out.println("Input the amount you wish to withdraw");
                                double funding = scanner.nextDouble();
                                try {
                                    BankAccount acc = bankDao.getBankAccountByID(bankID); //get the bank
                                    boolean withdrew = acc.withdraw(funding); //Withdraw from it
                                    if(withdrew) //If we actually withdrew money
                                        bankDao.updateBankAccount(acc); //update the database
                                } catch (SQLException e) {
                                    System.out.println("Check SQL for typos.");
                                    e.printStackTrace();
                                }
                                break;
                            case 5: /////////////////////////////////////////////////////////////////////Create new money transfer
                                try {
                                    List<BankAccount> myAccounts = bankDao.getAllApprovedBankAccountsOwned(activeCustomer);
                                    if(myAccounts.isEmpty()){ //This user doesnt have any accounts
                                        System.out.println("You don't have any active accounts.");

                                    }
                                    else{
                                        int source;
                                        valid_source = false;
                                        int dest;
                                        double funds;

                                        System.out.println("Please enter the amount you wish to send.");
                                        funds = scanner.nextDouble();
                                        if(funds <= 0){
                                            System.out.println("Invalid amount. Returning to previous menu.");
                                            break;
                                        }


                                        List<Integer> valid_nums = new ArrayList<>();
                                        for(BankAccount account : myAccounts){ //Create a list of accounts the user can pick from
                                            valid_nums.add(account.getBID());
                                        }
                                        System.out.println("Please enter the account id of the account you will use to send this.");
                                        source = scanner.nextInt();
                                        if(valid_nums.contains(source)){
                                            valid_source = true;
                                        }
                                        else{
                                            valid_source = false;
                                        }
                                        while(!valid_source){ //Make sure the customer actually owns the account they are taking money from
                                            if(valid_nums.contains(source)){
                                                valid_source = true;
                                            }
                                            else{
                                                System.out.println("Invalid account number. You may choose from your accounts: ");
                                                for(BankAccount account : myAccounts){ //show the user the ids
                                                    System.out.println(account);
                                                }
                                                source = scanner.nextInt();
                                            }

                                        }

                                        System.out.println("Please enter the account number representing the destination.");
                                        dest = scanner.nextInt();

                                        MoneyTransfer transfer = new MoneyTransfer();
                                        transfer.setAmount(funds);
                                        transfer.setPending();
                                        transfer.setSender(source);
                                        transfer.setRecipient(dest);

                                        xferDao.addMoneyTransfer(transfer);

                                    }
                                } catch (SQLException e) {
                                    System.out.println("Check SQL for typos");
                                    e.printStackTrace();
                                }


                                break;
                            case 6: ///////////////////////////////////////////////////////////////////////////Accept funds from a transfer
                                List<BankAccount> myAccounts = null;
                                try {
                                    myAccounts = bankDao.getAllApprovedBankAccountsOwned(activeCustomer); //Get all accounts
                                    if(myAccounts.isEmpty()){ //This user doesnt have any accounts
                                        System.out.println("You don't have any active accounts.");

                                    }
                                    else{
                                        List<MoneyTransfer> transfers = new ArrayList<>();
                                        for(BankAccount account : myAccounts){ //Get all the transfers.
                                            transfers.addAll(xferDao.getAllPendingTransfersTo(account.getBID()));
                                        }
                                        if(transfers.isEmpty()){ //No transfers for any accounts
                                            System.out.println("No pending transfers.");
                                            break;
                                        }
                                        else{
                                            System.out.println("Here are the transfers to choose from: ");
                                            for(MoneyTransfer transfer : transfers){
                                                System.out.println(transfer + " To Account No.: " + transfer.getRecipient() + " From Account No.: " + transfer.getSender());
                                            }
                                            System.out.println("Enter the id of the transfer to confirm: ");
                                            int xferid = scanner.nextInt();
                                            MoneyTransfer xfer = xferDao.getTransferByID(xferid);

                                            //Complete the transfer
                                            xferDao.executeTransfer(xfer, bankDao.getBankAccountByID(xfer.getSender()),bankDao.getBankAccountByID(xfer.getRecipient()));

                                            System.out.println("Transfer complete!");
                                        }

                                    }
                                } catch (SQLException e) {
                                    System.out.println("Check for typos.");
                                    e.printStackTrace();
                                }

                                break;
                            case 7: ////////////////////////////////////////////////////////////////////////////Apply for a new account
                                System.out.println("Please input the starting balance: ");
                                double funds = scanner.nextDouble();

                                BankAccount bank = new BankAccount(funds);
                                bank.setPending(); //Mark this account as pending/unapproved
                                bank.setOwnerID(activeCustomer.getCustID()); //Set who owns this account
                                try {
                                    bankDao.addBankAccount(bank);
                                    System.out.println("Account initialized! Please wait for an employee to review it.");

                                } catch (SQLException e) {
                                    System.out.println("Check sql for typos.");
                                    e.printStackTrace();
                                }

                                break;
                            case 0:
                                System.out.println("Logging out. Until next time!");
                                user_logged_in = false;
                                activeCustomer = null;
                                break;
                            default:
                                System.out.println("Invalid input.");
                                break;
                        }
                        break;
                    case 2: //////////////////////////////////////////////////EMPLOYEE
                        employee_choice = 0; //Reset if looping
                        System.out.println("Welcome, " + activeEmployee.getUserName() + "! What business would you like to handle today?");
                        System.out.println("1: Approve / Reject an Account");
                        System.out.println("2: View a Customer's Bank Account");
                        System.out.println("0: Log out");
                        employee_choice = scanner.nextInt();
                        //scanner.nextLine(); //cleanup
                        switch (employee_choice){
                            case 1:
                                System.out.println("Enter the Account's ID: ");
                                int accID = scanner.nextInt();
                                try {
                                    BankAccount pendingAccount = bankDao.getBankAccountByID(accID);
                                    if(!pendingAccount.getPendingStatus()){ //not actually pending
                                        System.out.println("This account has already been resolved.");
                                        break;
                                    }
                                    //Else keep going
                                    System.out.println("Account Info----------- ");
                                    System.out.println("Account Owner: " + custDao.getCustomerByID(pendingAccount.getOwnerID()).getUserName());
                                    System.out.println("Account Starting Balance: " + pendingAccount.getBalance());
                                    System.out.println("Account No.: " + pendingAccount.getBID());
                                    System.out.println("Will you approve or reject the account?");
                                    System.out.println("1: Approve");
                                    System.out.println("2: Reject");
                                    int empl_approve = scanner.nextInt(); ///////////0 - none, 1 - approve, 2 - reject

                                    switch (empl_approve){
                                        case 1:
                                            pendingAccount.getApproved(); //Approve the account
                                            bankDao.updateBankAccount(pendingAccount); //Update DB with new data
                                            break;
                                        case 2:
                                            pendingAccount.getRejected(); //Reject the account
                                            bankDao.updateBankAccount(pendingAccount); //Update DB with new data
                                            break;
                                        default:
                                            break;
                                    }

                                } catch (SQLException e) {
                                    System.out.println("Check SQL for typos.");
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.println("Enter the Bank Account's ID");
                                int bankID = scanner.nextInt();
                                BankAccount pendingAccount = null;
                                try {
                                    pendingAccount = bankDao.getBankAccountByID(bankID);
                                    System.out.println("Account Info----------- ");
                                    System.out.println("Account Owner: " + custDao.getCustomerByID(pendingAccount.getOwnerID()).getUserName());
                                    System.out.println("Account Starting Balance: " + pendingAccount.getBalance());
                                    System.out.println("Account No.: " + pendingAccount.getBID());
                                    System.out.println("Pending Status: " + (pendingAccount.getPendingStatus() ? "Currently Pending" : "Resolved" ) );
                                    if(!pendingAccount.getPendingStatus()){
                                        System.out.println("Approval Status: " + (pendingAccount.getApprovalStatus() ? "Approved" : "Rejected"));
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 0:
                                System.out.println("Logging out. Until next time!");
                                user_logged_in = false;
                                activeEmployee = null;
                                break;
                            default:
                                System.out.println("Invalid input");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }


            //user_currently_active = false; //break out of loop for now
        }
    }
}
