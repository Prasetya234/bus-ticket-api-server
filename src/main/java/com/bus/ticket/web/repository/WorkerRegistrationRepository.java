package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.WorkerRegistration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRegistrationRepository extends JpaRepository<WorkerRegistration, String> {
    Optional<WorkerRegistration> findByCompanyIdAndUserId(Company companyId, User userId);
    Page<WorkerRegistration> findAllByCompanyId(Company companyId, Pageable pageable);
}
