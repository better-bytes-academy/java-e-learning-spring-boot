package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.UserRole;
import com.example.onlinelearning.enums.RoleName;
import com.example.onlinelearning.util.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleKey> {
    @Query("SELECT r.roleName FROM Role r JOIN UserRole ur ON r.roleId = ur.roleId WHERE ur.userId = :userId")
    List<RoleName> findRolesByUserId(@Param("userId") Integer userId);
}