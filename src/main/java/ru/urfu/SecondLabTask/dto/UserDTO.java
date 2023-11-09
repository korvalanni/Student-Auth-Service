package ru.urfu.SecondLabTask.dto;

public class UserDTO
{
    private String userName;
    private String password;

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public UserDTO(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }
}