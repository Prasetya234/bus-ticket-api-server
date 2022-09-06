package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.jwt.service.AuthenticationFacade;
import com.bus.ticket.web.dto.WorkerRegistrationDTO;
import com.bus.ticket.web.model.WorkerRegistration;
import com.bus.ticket.web.repository.CompanyEmployeRepository;
import com.bus.ticket.web.repository.WorkerRegistrationRepository;
import com.bus.ticket.web.service.CompanyEmployeService;
import com.bus.ticket.web.service.CompanyService;
import com.bus.ticket.web.service.WorkerRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class WorkerRegistrationSerivceImpl implements WorkerRegistrationService {
    private WorkerRegistrationRepository workerRegistrationRepository;
    private CompanyService companyService;
    private CompanyEmployeService companyEmployeService;
    private AuthenticationFacade facade;
    private ModelMapper modelMapper;

    @Autowired
    public WorkerRegistrationSerivceImpl(WorkerRegistrationRepository workerRegistrationRepository, CompanyService companyService, CompanyEmployeService companyEmployeService, AuthenticationFacade facade, ModelMapper modelMapper) {
        this.workerRegistrationRepository = workerRegistrationRepository;
        this.companyService = companyService;
        this.companyEmployeService = companyEmployeService;
        this.facade = facade;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public WorkerRegistration add(WorkerRegistrationDTO workerRegistrationDto) {
        WorkerRegistration workerRegistration = modelMapper.map(workerRegistrationDto, WorkerRegistration.class);
        workerRegistration.setUserId(facade.getAuthentication());
        workerRegistration.setCompanyId(companyService.getById(workerRegistrationDto.getCompanyId()));
        return workerRegistrationRepository.save(workerRegistration);
    }


    @Transactional
    @Override
    public Map<String, Boolean> applied(String id) {
        WorkerRegistration applied = workerRegistrationRepository.findById(id).orElseThrow(() -> new NotFoundException("Worker Registration NOT FOUND"));
        companyEmployeService.add(applied.getCompanyId(), applied.getUserId());
        workerRegistrationRepository.delete(applied);
        Map<String, Boolean> response = new HashMap<>();
        response.put("applied", Boolean.TRUE);
        return response;
    }

    @Transactional
    @Override
    public Map<String, Boolean> delete(String id) {
        WorkerRegistration delete = workerRegistrationRepository.findById(id).orElseThrow(() -> new NotFoundException("Worker Registration NOT FOUND"));
        workerRegistrationRepository.delete(delete);
        Map<String, Boolean> response = new HashMap<>();
        response.put("DELETED", Boolean.TRUE);
        return response;
    }
}
