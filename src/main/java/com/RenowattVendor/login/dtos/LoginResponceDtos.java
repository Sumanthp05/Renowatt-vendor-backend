package com.RenowattVendor.login.dtos;

import com.RenowattVendor.User.Model.User;

public class LoginResponceDtos {
    private User users;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
