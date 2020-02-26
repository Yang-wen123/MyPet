package com.company.Bean;

public class PlayerBean {
    int username;
    String password;
    public PlayerBean(){}
    public PlayerBean(int username,String password){
        this.username=username;
        this.password=password;
    }
    public void setUsername(int username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public int getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public String toString() {
        return "PlayerBean [username=" + username + ", password=" + password + "]";
    }
}
