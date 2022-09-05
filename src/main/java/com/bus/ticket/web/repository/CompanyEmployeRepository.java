package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.CompanyEmploye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyEmployeRepository extends JpaRepository<CompanyEmploye, Integer> {
}
