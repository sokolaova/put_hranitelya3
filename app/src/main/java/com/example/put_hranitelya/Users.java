package com.example.put_hranitelya;

public class Users {

    private int id;
    private String emailAddress;
    private String password;

    public Users (String emailAddress, String password){
        this.emailAddress = emailAddress;
        this.password = password;
    }

    /*public Users (int id, String emailAddress, String password){
        this.id = id;
        this.emailAddress = emailAddress;
        this.password = password;
    }
*/
    public int getId(){
        return id;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public String getPassword(){
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
