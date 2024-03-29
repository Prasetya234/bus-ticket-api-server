package com.bus.ticket.web.service;

import com.bus.ticket.web.dto.TopUpDTO;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;

public interface WalletService {
    Wallet add(User user);

    Wallet showWallet();

    Wallet topup(TopUpDTO topUpDTO);
    Wallet balanceManipulation(Float nominal, boolean out);
}
