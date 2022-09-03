package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.jwt.service.AuthenticationFacade;
import com.bus.ticket.web.dto.CompanyDto;
import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.CompanyRepository;
import com.bus.ticket.web.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;
    private AuthenticationFacade facade;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper modelMapper, AuthenticationFacade facade) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.facade = facade;
    }

    @Transactional
    @Override
    public Company add(CompanyDto companyDto) {
        User user = facade.getAuthentication();
        Company company = modelMapper.map(companyDto, Company.class);
        company.setAdminId(user);
        company.setTotalPassenger(0);
        company.setBlocked(false);
        return companyRepository.save(company);
    }

    @Transactional
    @Override
    public Company update(String id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("CONPANY ID NOT FOUND")) ;
        company.setName(companyDto.getName());
        company.setAddress(companyDto.getAddress());
        company.setLogo(company.getLogo());
        company.setDirector(companyDto.getDirector());
        return companyRepository.save(company);
    }

    @Transactional(readOnly = true)
    @Override
    public Company getById(String id) {
        return companyRepository.findById(id).orElseThrow(() -> new NotFoundException("DESTINATION ID NOT FOUND"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Company> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return companyRepository.findAll(pageable);
    }
}
