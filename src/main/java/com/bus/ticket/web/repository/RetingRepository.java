package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.Reting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetingRepository extends JpaRepository<Reting, Integer> {
}
