package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.model.CompanyEmploye;
import com.bus.ticket.web.service.CompanyEmployeService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/company-employe")
public class CompanyEmployeController {

    private CompanyEmployeService companyEmployeService;

    public CompanyEmployeController(CompanyEmployeService companyEmployeService) {
        this.companyEmployeService = companyEmployeService;
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
    @DeleteMapping("/resign/{companyId}")
    public CommonResponse<Map<String, Boolean>> resign(@PathVariable("companyId") String companyId){
        return ResponseHelper.successResponse(companyEmployeService.resign(companyId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/company/{companyId}/worker")
    public CommonResponse<Page<CompanyEmploye>> findAllWorker(@PathVariable(value = "companyId") String companyId, @RequestParam(name= "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size){
        return ResponseHelper.successResponse(companyEmployeService.findAllCompanyEmploye(companyId, page, size));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/company/{companyId}/worker/{employeeId}")
    public CommonResponse<CompanyEmploye> findWorker(@PathVariable(value = "companyId") String companyId, @PathVariable("employeeId") int employeeId){
        return ResponseHelper.successResponse(companyEmployeService.findById(companyId, employeeId));
    }
}
