package com.bus.ticket.web.service;

import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;

public interface OtpService {
    CodeOtp create(User user);
    CodeOtp activeUser(String otp);
    CodeOtp resendOtp(User user);
}
