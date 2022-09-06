package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.service.CompanyEmployeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/api/company-employe")
public class CompanyEmploye {

    private CompanyEmployeService companyEmployeService;

    public CompanyEmploye(CompanyEmployeService companyEmployeService) {
        this.companyEmployeService = companyEmployeService;
    }

    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @DeleteMapping("/resign/{companyId}")
    public CommonResponse<Map<String, Boolean>> resign(@PathVariable("companyId") String companyId){
        return ResponseHelper.successResponse(companyEmployeService.resign(companyId));
    }
}
