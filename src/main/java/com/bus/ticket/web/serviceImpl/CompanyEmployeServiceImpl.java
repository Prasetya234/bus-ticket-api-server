package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.jwt.service.AuthenticationFacade;
import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.model.CompanyEmploye;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.CompanyEmployeRepository;
import com.bus.ticket.web.service.CompanyEmployeService;
import com.bus.ticket.web.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CompanyEmployeServiceImpl implements CompanyEmployeService {
    private CompanyService companyService;
    private CompanyEmployeRepository companyEmployeRepository;
    private AuthenticationFacade facade;

    @Autowired
    public CompanyEmployeServiceImpl(CompanyService companyService, CompanyEmployeRepository companyEmployeRepository, AuthenticationFacade facade) {
        this.companyService = companyService;
        this.companyEmployeRepository = companyEmployeRepository;
        this.facade = facade;
    }

    @Override
    public CompanyEmploye add(Company companyId, User user) {
        companyService.addNumberWorker(companyId);
        CompanyEmploye create = new CompanyEmploye();
        create.setCompanyId(companyId);
        create.setEmployeId(user);
        create.setWork(false);
        return companyEmployeRepository.save(create);
    }

    @Transactional(readOnly = true)
    @Override
    public CompanyEmploye findById(String companyId, int id) {
        Company cmpy = companyService.getById(companyId);
        return companyEmployeRepository.findByIdAndCompanyId(id, cmpy).orElseThrow(() -> new NotFoundException("Worker NOT FOUND"));
    }

    @Override
    public Optional<CompanyEmploye> findWorkerAlready(User user, Company company) {
        return companyEmployeRepository.findByEmployeIdAndCompanyId(user, company);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CompanyEmploye> findAllCompanyEmploye(String companyId, int page, int size) {
        Company cmpy = companyService.getById(companyId);
        Pageable pageable = PageRequest.of(page, size);
        return companyEmployeRepository.findAllByCompanyId(cmpy, pageable);
    }

    @Transactional
    @Override
    public Map<String, Boolean> resign(String companyId) {
        Company cmpy = companyService.getById(companyId);
        User user = facade.getAuthentication();
        CompanyEmploye employe = companyEmployeRepository.findByEmployeIdAndCompanyId(user, cmpy).orElseThrow(() -> new NotFoundException("Worker NOT FOUND"));
        companyEmployeRepository.delete(employe);
        companyService.removeNumberWorker(cmpy);
        Map<String, Boolean> response = new HashMap<>();
        response.put("RESIGN", Boolean.TRUE);
        return response;
    }
}
