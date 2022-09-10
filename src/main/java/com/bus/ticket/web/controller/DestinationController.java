package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.DestinationDTO;
import com.bus.ticket.web.model.Destination;
import com.bus.ticket.web.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/destination")
public class DestinationController {

    private DestinationService destinationService;

    @Autowired
    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public CommonResponse<Destination> create(@RequestBody DestinationDTO destinationDTO){
        return ResponseHelper.successResponse(destinationService.create(destinationDTO));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'EMPLOYEE')")
    @GetMapping
    public CommonResponse<Page<Destination>> findAll(@RequestParam(name = "page", defaultValue = "10", required = false) int page, @RequestParam(name="size", defaultValue = "0", required = false) int size){
        return ResponseHelper.successResponse(destinationService.findAll(page, size));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}")
    public CommonResponse<Destination> update(@PathVariable("id") Integer id, @RequestBody DestinationDTO destinationDTO){
        return ResponseHelper.successResponse(destinationService.update(id, destinationDTO));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN' , 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    private CommonResponse<Map<String, Boolean>> delete(@PathVariable("id") Integer id){
        return ResponseHelper.successResponse(destinationService.delete(id));
    }
}
