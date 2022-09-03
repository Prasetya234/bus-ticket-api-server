package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.HistoryJoinAdmin;
import com.bus.ticket.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryJoinAdminRepository extends JpaRepository<HistoryJoinAdmin, Integer> {
    Optional<HistoryJoinAdmin> findByUserId(User user);
}
