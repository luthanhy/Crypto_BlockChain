package com.group3.web_wallet_crypto.Model;

public class User {
    private String userName;
    private String passWord;

    private String publicKey;
    private String privateKey;
    private double balance;

    public User(String userName, String passWord, String publicKey, String privateKey, double balance) {
        this.userName = userName;
        this.passWord = passWord;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.balance = balance;
    }


}
