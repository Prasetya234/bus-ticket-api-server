package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.Departure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, String> {
}
