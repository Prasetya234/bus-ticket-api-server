package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.FacilityDTO;
import com.bus.ticket.web.model.Facility;
import com.bus.ticket.web.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/facility")
public class FacilityController {

    private FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CommonResponse<Facility> addNewFacility(@RequestBody FacilityDTO facility){
        return ResponseHelper.successResponse(facilityService.addNewFacility(facility));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public CommonResponse<List<Facility>> getAllUserRole(){
        return ResponseHelper.successResponse(facilityService.getAllFacility());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public CommonResponse<Facility> getById(@PathVariable ("id")Integer id){
        return ResponseHelper.successResponse(facilityService.getById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CommonResponse<Facility> updateData(@PathVariable("id") Integer id, @RequestBody FacilityDTO facility){
        return ResponseHelper.successResponse(facilityService.updateData(id, facility));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    private CommonResponse<Map<String, Boolean>> deleteFacility(@PathVariable("id") Integer Id){
        return ResponseHelper.successResponse(facilityService.deleteFacility(Id));
    }
}
