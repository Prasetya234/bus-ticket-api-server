package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ModelDelete;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.RetingDto;
import com.bus.ticket.web.model.Reting;
import com.bus.ticket.web.service.RetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/api/reting")
public class RetingController {

    private RetingService retingService;

    @Autowired
    public RetingController(RetingService retingService) {
        this.retingService = retingService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public CommonResponse<Reting> create(@RequestBody RetingDto retingDto) {
        return ResponseHelper.successResponse(retingService.create(retingDto));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public CommonResponse<List<Reting>> getAll() {
        return ResponseHelper.successResponse(retingService.findAll());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public CommonResponse<Reting> update(@PathVariable("id") int id, @RequestBody RetingDto retingDto) {
        return ResponseHelper.successResponse(retingService.update(id, retingDto));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public CommonResponse<ModelDelete> delete(@PathVariable("id") int id) {
        return ResponseHelper.successResponse(retingService.delete(id));
    }
}
