package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByUserId(User userId);
}
