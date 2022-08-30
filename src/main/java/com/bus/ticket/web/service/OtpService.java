package com.bus.ticket.web.service;

import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;
import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface OtpService {
    CodeOtp create(User user) throws TemplateException, MessagingException, IOException;
    CodeOtp activeUser(String otp);
    CodeOtp resendOtp(User user) throws TemplateException, MessagingException, IOException;
}
