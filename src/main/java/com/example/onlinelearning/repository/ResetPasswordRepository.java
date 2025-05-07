package com.example.onlinelearning.repository;

import com.example.onlinelearning.entity.ResetPassword;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword,String> {
    public ResetPassword findByEmail(String email);
    public ResetPassword findByEmailAndToken(String email, String token);

    @Modifying
    @Transactional
    @Query("update ResetPassword rp set rp.token = null, rp.expiryDate = null where  rp.email = :email")
    public void updateNullForTokenAndTime(@Param("email") String email);
}
