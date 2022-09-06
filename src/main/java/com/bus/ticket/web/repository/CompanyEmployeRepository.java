package com.bus.ticket.web.repository;

import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.model.CompanyEmploye;
import com.bus.ticket.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyEmployeRepository extends JpaRepository<CompanyEmploye, Integer> {
    Optional<CompanyEmploye> findByIdAndCompanyId(int id, Company companyId);
    Optional<CompanyEmploye> findByEmployeIdAndCompanyId(User employeId, Company companyId);
    Page<CompanyEmploye> findAllByCompanyId(Company companyId, Pageable pageable);
}
