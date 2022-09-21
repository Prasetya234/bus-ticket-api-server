package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.DepartureDTO;
import com.bus.ticket.web.model.Departure;
import com.bus.ticket.web.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/departure")
public class DepartureController {
    private DepartureService departureService;

    @Autowired
    public DepartureController(DepartureService departureService) {
        this.departureService = departureService;
    }

    @PostMapping
    public CommonResponse<Departure> createDeparture(@RequestParam("companyId") String companyId, @RequestParam(name ="promoId", required = false) String promoId, @RequestBody DepartureDTO departureDTO) {
        return ResponseHelper.successResponse(departureService.create(companyId, promoId, departureDTO));
    }

    @GetMapping
    public CommonResponse<Page<Departure>> findAllDeparture(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return ResponseHelper.successResponse(departureService.findAll(page, size));
    }
}
