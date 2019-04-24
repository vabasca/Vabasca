package com.vabasca.model;


public class User {

    private String username;
    private String email;
    private String password;
    private String newPassword;

    public void setUsername(String name) {
        this.username = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsernameName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
