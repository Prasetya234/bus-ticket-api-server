package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.PaymentTypeDTO;
import com.bus.ticket.web.model.PaymentType;
import com.bus.ticket.web.service.PaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/payment-type")
public class PaymentTypeController {
    private PaymentTypeService paymentTypeService;

    @Autowired
    public PaymentTypeController(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PostMapping
    public CommonResponse<PaymentType> create(@RequestBody PaymentTypeDTO paymentType) {
        return ResponseHelper.successResponse(paymentTypeService.addNew(paymentType));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'EMPLOYEE')")
    @GetMapping
    public CommonResponse<List<PaymentType>> getAll() {
        return ResponseHelper.successResponse(paymentTypeService.getAllPayment());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @PutMapping("/{id}")
    public CommonResponse<PaymentType> update(@PathVariable("id") int id, @RequestBody PaymentTypeDTO paymentType) {
        return ResponseHelper.successResponse(paymentTypeService.updatePaymentType(id, paymentType));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public CommonResponse<Map<String, Boolean>> delete(@PathVariable("id") int id) {
        return ResponseHelper.successResponse(paymentTypeService.delete(id));
    }
}
