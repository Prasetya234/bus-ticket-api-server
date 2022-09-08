package com.bus.ticket.web.service;

import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.model.CompanyEmploye;
import com.bus.ticket.web.model.User;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;

public interface CompanyEmployeService {
    CompanyEmploye add(Company companyId, User user);
    CompanyEmploye findById(String companyId, int id);
    Optional<CompanyEmploye> findWorkerAlready(User user, Company company);
    Page<CompanyEmploye> findAllCompanyEmploye(String companyId, int page, int size);
    Map<String, Boolean> resign(String companyId);
}
