package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.CompanyDto;
import com.bus.ticket.web.model.Company;
import com.bus.ticket.web.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CommonResponse<Company> create(@RequestBody CompanyDto companyDto) {
        return ResponseHelper.successResponse(companyService.add(companyDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CommonResponse<Company> update(@PathVariable("id") String id, @RequestBody CompanyDto companyDto) {
        return ResponseHelper.successResponse(companyService.update(id, companyDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'EMPLOYEE')")
    @GetMapping
    public CommonResponse<Page<Company>> findAll(@RequestParam(name= "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size){
        return ResponseHelper.successResponse(companyService.getAll(page, size));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'EMPLOYEE')")
    @GetMapping("/{id]")
    public CommonResponse<Company> getById(@PathVariable("id") String id) {
        return ResponseHelper.successResponse(companyService.getById(id));
    }
}
