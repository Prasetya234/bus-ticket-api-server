package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.jwt.service.AuthenticationFacade;
import com.bus.ticket.web.dto.WorkerRegistrationDTO;
import com.bus.ticket.web.model.WorkerRegistration;
import com.bus.ticket.web.repository.WorkerRegistrationRepository;
import com.bus.ticket.web.service.CompanyService;
import com.bus.ticket.web.service.WorkerRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Slf4j
@Service
public class WorkerRegistrationSerivceImpl implements WorkerRegistrationService {
    private WorkerRegistrationRepository workerRegistrationRepository;
    private CompanyService companyService;
    private AuthenticationFacade facade;
    private ModelMapper modelMapper;

    @Autowired
    public WorkerRegistrationSerivceImpl(WorkerRegistrationRepository workerRegistrationRepository, CompanyService companyService, AuthenticationFacade facade, ModelMapper modelMapper) {
        this.workerRegistrationRepository = workerRegistrationRepository;
        this.companyService = companyService;
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

    @Override
    public Map<String, Boolean> applied(String id) {

        return null;
    }

    @Override
    public Map<String, Boolean> delete(String id) {
        return null;
    }
}
