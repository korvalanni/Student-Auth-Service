package ru.urfu.SecondLabTask.dto;

public class UserUpdatePasswordDTO {
    private String userOldPassword;
    private String userNewPassword;

    public UserUpdatePasswordDTO(String userOldPassword, String userNewPassword) {
        this.userOldPassword = userOldPassword;
        this.userNewPassword = userNewPassword;
    }

    public String getUserNewPassword() {
        return userNewPassword;
    }

    public void setUserNewPassword(String userNewPassword) {
        this.userNewPassword = userNewPassword;
    }

    public String getUserOldPassword() {
        return userOldPassword;
    }

    public void setUserOldPassword(String userOldPassword) {
        this.userOldPassword = userOldPassword;
    }
}
