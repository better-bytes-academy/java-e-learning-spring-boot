package com.example.onlinelearning.entity;

import com.example.onlinelearning.util.UserRoleKey;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@IdClass(UserRoleKey.class)
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Id
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "assigned_at")
    private Timestamp assignedAt = new Timestamp(System.currentTimeMillis());

    public UserRole() {
    }

    public UserRole(Integer userId, Integer roleId, Timestamp assignedAt) {
        this.userId = userId;
        this.roleId = roleId;
        this.assignedAt = assignedAt;
    }

    public UserRole(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Timestamp getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(Timestamp assignedAt) {
        this.assignedAt = assignedAt;
    }
}
