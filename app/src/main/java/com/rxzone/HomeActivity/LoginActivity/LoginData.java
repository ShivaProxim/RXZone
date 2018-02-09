package com.rxzone.HomeActivity.LoginActivity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PROXIM on 2/5/2018.
 */

public class LoginData {
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
