package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.HistoryBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryBalanceRepository extends JpaRepository<HistoryBalance, String> {
}
