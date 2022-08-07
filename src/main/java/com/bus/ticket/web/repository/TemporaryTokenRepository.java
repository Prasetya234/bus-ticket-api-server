package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.TemporaryToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryTokenRepository extends JpaRepository<TemporaryToken, String> {
}
