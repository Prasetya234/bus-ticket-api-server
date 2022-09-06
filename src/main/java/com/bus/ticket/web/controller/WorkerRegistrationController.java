package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.WorkerRegistrationDTO;
import com.bus.ticket.web.model.WorkerRegistration;
import com.bus.ticket.web.service.WorkerRegistrationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/worker-registration")
public class WorkerRegistrationController {

    private WorkerRegistrationService workerRegistrationService;

    @Autowired
    public WorkerRegistrationController(WorkerRegistrationService workerRegistrationService) {
        this.workerRegistrationService = workerRegistrationService;
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @PostMapping
    public CommonResponse<WorkerRegistration> registration(@RequestBody WorkerRegistrationDTO worker) {
        return ResponseHelper.successResponse(workerRegistrationService.add(worker));
    }
}
