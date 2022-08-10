package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.DepartureStatusDTO;
import com.bus.ticket.web.dto.DestinationDTO;
import com.bus.ticket.web.model.DepartureStatus;
import com.bus.ticket.web.model.Destination;
import com.bus.ticket.web.service.DepartureStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/departure-status")
public class DepartureStatusController {

    private DepartureStatusService departureStatusService;

    @Autowired
    public DepartureStatusController(DepartureStatusService departureStatusService) {
        this.departureStatusService = departureStatusService;
    }

    @PostMapping
    public CommonResponse<DepartureStatus> create(@RequestBody DepartureStatusDTO departureStatusDTO){
        return ResponseHelper.successResponse(departureStatusService.create(departureStatusDTO));
    }

    @GetMapping
    public CommonResponse<List<DepartureStatus>> findAll(){
        return ResponseHelper.successResponse(departureStatusService.findAll());
    }

    @PutMapping("/{id}")
    public CommonResponse<DepartureStatus> update(@PathVariable("id") Integer id, @RequestBody DepartureStatusDTO departureStatusDTO){
        return ResponseHelper.successResponse(departureStatusService.update(id, departureStatusDTO));
    }

    @DeleteMapping("/{id}")
    private CommonResponse<Map<String, Boolean>> delete(@PathVariable("id") Integer id){
        return ResponseHelper.successResponse(departureStatusService.delete(id));
    }
}
