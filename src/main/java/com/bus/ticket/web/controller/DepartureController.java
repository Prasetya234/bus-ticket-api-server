package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.DepartureDTO;
import com.bus.ticket.web.model.Departure;
import com.bus.ticket.web.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/departure")
public class DepartureController {
    private DepartureService departureService;

    @Autowired
    public DepartureController(DepartureService departureService) {
        this.departureService = departureService;
    }

    @PostMapping
    public CommonResponse<Departure> createDeparture(@RequestBody DepartureDTO departureDTO) {
        return ResponseHelper.successResponse(departureService.create(departureDTO));
    }
}
