package com.chuwa.learn.hw1_OOP_example.hw1_eceapsulation_example;

// example for Encapsulation in OOP Java:
// Create a class BankAccount that encapsulates the details of a bank account.
public class BankAccount {
    private String accountNumber;
    private double balance;


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if(amount >0){
            balance += amount;
            System.out.println("Deposit $ " + amount + "successful");
        }
    }

    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance){
            balance -= amount;
            System.out.println("Withdraw $ " + amount + "successful");
        }else{
            System.out.println("Insufficient balance");
        }
    }
}
