package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.CompanyDto;
import com.bus.ticket.web.model.Company;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompanyService {
    Company add(CompanyDto companyDto);
    Company update(String id, CompanyDto companyDto);
    Company getById(String id);
    Page<Company> getAll(int page, int size);
}

