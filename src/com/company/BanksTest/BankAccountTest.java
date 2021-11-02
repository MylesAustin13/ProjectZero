package com.company.BanksTest;

import com.company.Banks.BankAccount;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    public void depositTest(){
        BankAccount testAccount = new BankAccount(100);

        //testAccount.deposit(100);

        Assert.assertEquals(testAccount.deposit(100), true); //Should succeed
        Assert.assertEquals(testAccount.deposit(-2), false); //Should fail
    }

    @Test
    public void withdrawalTest(){
        BankAccount testAccount = new BankAccount(100);

        //testAccount.deposit(100);

        Assert.assertEquals(testAccount.withdraw(10), true); //Should succeed
        Assert.assertEquals(testAccount.withdraw(-2), false); //Should fail
        Assert.assertEquals(testAccount.withdraw(100), false); //Should fail
    }

}
