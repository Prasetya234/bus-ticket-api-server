package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.DestinationDTO;
import com.bus.ticket.web.dto.FacilityDTO;
import com.bus.ticket.web.model.Destination;
import com.bus.ticket.web.model.Facility;
import com.bus.ticket.web.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public CommonResponse<Destination> create(@RequestBody DestinationDTO destinationDTO){
        return ResponseHelper.successResponse(destinationService.create(destinationDTO));
    }

    @GetMapping
    public CommonResponse<List<Destination>> findAll(){
        return ResponseHelper.successResponse(destinationService.findAll());
    }

    @PutMapping("/{id}")
    public CommonResponse<Destination> update(@PathVariable("id") Integer id, @RequestBody DestinationDTO destinationDTO){
        return ResponseHelper.successResponse(destinationService.update(id, destinationDTO));
    }

    @DeleteMapping("/{id}")
    private CommonResponse<Map<String, Boolean>> delete(@PathVariable("id") Integer id){
        return ResponseHelper.successResponse(destinationService.delete(id));
    }
}
