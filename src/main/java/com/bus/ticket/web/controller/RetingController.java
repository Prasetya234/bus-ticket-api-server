package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ModelDelete;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.RetingDto;
import com.bus.ticket.web.model.Reting;
import com.bus.ticket.web.service.RetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/reting")
public class RetingController {

    private RetingService retingService;

    @Autowired
    public RetingController(RetingService retingService) {
        this.retingService = retingService;
    }

    @PostMapping
    public CommonResponse<Reting> create(@RequestBody RetingDto retingDto) {
        return ResponseHelper.successResponse(retingService.create(retingDto));
    }

    @GetMapping
    public CommonResponse<List<Reting>> getAll() {
        return ResponseHelper.successResponse(retingService.findAll());
    }

    @PutMapping("/{id}")
    public CommonResponse<Reting> update(@PathVariable("id") int id, @RequestBody RetingDto retingDto) {
        return ResponseHelper.successResponse(retingService.update(id, retingDto));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<ModelDelete> delete(@PathVariable("id") int id) {
        return ResponseHelper.successResponse(retingService.delete(id));
    }
}
