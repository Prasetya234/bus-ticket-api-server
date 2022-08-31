package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.TopUpDTO;
import com.bus.ticket.web.model.Wallet;
import com.bus.ticket.web.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/wallet")
public class WalletController {

    private WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/topup")
    public CommonResponse<Wallet> topup(@RequestBody TopUpDTO topup) {
        return ResponseHelper.successResponse(walletService.topup(topup));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping
    public CommonResponse<Wallet> showWallet() {
        return ResponseHelper.successResponse(walletService.showWallet());
    }
}
