package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.WorkerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRegistrationRepository extends JpaRepository<WorkerRegistration, String> {
}
