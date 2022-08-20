
package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.CodeOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeOptRepository extends JpaRepository<CodeOtp, String > {
    @Query(value = "SELECT a.* FROM code_otp a WHERE a.user_id = :userId", nativeQuery = true)
    Optional<CodeOtp> findByUserId(String userId);
    Optional<CodeOtp> findByCode(String code);
}
