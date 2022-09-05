package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.WorkerRegistrationDTO;
import com.bus.ticket.web.model.WorkerRegistration;

import java.util.Map;

public interface WorkerRegistrationService {
    WorkerRegistration add(WorkerRegistrationDTO workerRegistrationDto);

    Map<String, Boolean> applied(String id);

    Map<String, Boolean> delete(String id);
}

