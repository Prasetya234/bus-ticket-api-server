package com.bus.ticket.web.controller;


import com.bus.ticket.enggine.configure.xendit.XenditClient;
import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/xendit")
public class XenditController {


    @GetMapping
    public CommonResponse<?> createPaymentInvoice() {
        return ResponseHelper.successResponse(XenditClient.build());
    }
}
