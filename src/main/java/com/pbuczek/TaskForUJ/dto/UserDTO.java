package com.pbuczek.TaskForUJ.dto;

public class UserDTO {
    private String id;
    private String password;
    private String newPassword;
    private String login;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNewPassword() {
        return newPassword;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
