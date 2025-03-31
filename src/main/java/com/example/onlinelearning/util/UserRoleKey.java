package com.example.onlinelearning.util;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleKey implements Serializable {
    private Integer userId;
    private Integer roleId;

    public UserRoleKey() {}

    public UserRoleKey(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleKey that = (UserRoleKey) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
