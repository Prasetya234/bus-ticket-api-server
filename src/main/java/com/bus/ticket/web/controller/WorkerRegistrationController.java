package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.WorkerRegistrationDTO;
import com.bus.ticket.web.model.WorkerRegistration;
import com.bus.ticket.web.service.WorkerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/worker-registration")
    public CommonResponse<Page<WorkerRegistration>> findAllWorker(@RequestParam("companyId") String companyId,@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseHelper.successResponse(workerRegistrationService.findAllWorkerRegistrationByCompany(companyId, page, size));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reject/{id}")
    public CommonResponse<?> reject(@PathVariable("id") String id) {
        return ResponseHelper.successResponse(workerRegistrationService.delete(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/apply/{id}")
    public CommonResponse<?> applied(@PathVariable("id") String id) {
        return ResponseHelper.successResponse(workerRegistrationService.applied(id));
    }
}
