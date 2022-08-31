package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.model.HistoryBalance;
import com.bus.ticket.web.service.HistoryBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/history-balace")
public class HistoryBalanceController {

    private HistoryBalanceService historyBalanceService;

    @Autowired
    public HistoryBalanceController(HistoryBalanceService historyBalanceService) {
        this.historyBalanceService = historyBalanceService;
    }

    @GetMapping
    public CommonResponse<Page<HistoryBalance>> findHistoryBalance(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "10", required = false) int size ) {
        return ResponseHelper.successResponse(historyBalanceService.showHistoryBalance(page, size));
    }

    @GetMapping("/{id}")
    public CommonResponse<HistoryBalance> findById(@PathVariable("id") String id) {
        return ResponseHelper.successResponse(historyBalanceService.findById(id));
    }
}
