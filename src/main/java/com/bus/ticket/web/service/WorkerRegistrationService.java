package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.WorkerRegistrationDTO;
import com.bus.ticket.web.model.WorkerRegistration;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface WorkerRegistrationService {
    WorkerRegistration add(WorkerRegistrationDTO workerRegistrationDto);

    Page<WorkerRegistration> findAllWorkerRegistrationByCompany(String companyId, int page, int size);

    Map<String, Boolean> applied(String id);

    Map<String, Boolean> delete(String id);
}

