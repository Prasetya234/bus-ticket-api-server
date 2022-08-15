package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.WalletDto;
import com.bus.ticket.web.model.Wallet;

public interface WalletService {
    Wallet add(String userId);
    Wallet balanceManipulation(Float nominal, boolean out);
}
