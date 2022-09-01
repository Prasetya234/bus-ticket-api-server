package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TemporaryTokenRepository extends JpaRepository<TemporaryToken, String> {
    Optional<TemporaryToken> findByUserId(User userId);
    @Query(value = "SELECT a.* FROM temporary_token a WHERE a.token = :token AND a.expired_date > :expired", nativeQuery = true)
    Optional<TemporaryToken> findByTokenValid(String token, Date expired);
    Optional<TemporaryToken> findByToken(String token);
}
