package com.example.onlinelearning.response;

public class JwtResponse {
    private String token;

    // Constructor, Getters and Setters
    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
