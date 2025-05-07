package com.example.onlinelearning.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "reset_password")
public class ResetPassword implements Serializable {
    public static final int TIME = 5;

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @Column(name = "expiry_date")
    private Timestamp expiryDate = new Timestamp(System.currentTimeMillis() + TIME * 60 * 1000);

    public ResetPassword() {
    }

    public ResetPassword(String email, String token, Timestamp expiryDate) {
        this.email = email;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }
}
