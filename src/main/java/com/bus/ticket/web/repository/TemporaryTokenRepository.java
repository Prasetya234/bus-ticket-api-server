package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TemporaryTokenRepository extends JpaRepository<TemporaryToken, String> {
    Optional<TemporaryToken> findByUserId(User userId);
    Optional<TemporaryToken> findByTokenAndExpiredDateIsBefore(String token, Date exipredDate);
    TemporaryToken findByToken(String token);
}
