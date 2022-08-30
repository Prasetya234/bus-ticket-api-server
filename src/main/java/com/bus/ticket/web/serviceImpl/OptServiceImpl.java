
package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.configure.EmailConfig;
import com.bus.ticket.enggine.configure.service.GenerateSMS;
import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.utils.Utils;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.CodeOptRepository;
import com.bus.ticket.web.service.OtpService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class OptServiceImpl extends EmailConfig implements OtpService {
    private CodeOptRepository codeOptRepository;

    @Autowired
    public OptServiceImpl(Configuration config, CodeOptRepository codeOptRepository) {
        super(config);
        this.codeOptRepository = codeOptRepository;
    }

    @Transactional
    @Override
    public CodeOtp create(User user) throws TemplateException, MessagingException, IOException {
        Optional<CodeOtp> checking = codeOptRepository.findByUserId(user.getId());
        if (checking.isPresent()) codeOptRepository.delete(checking.get());
        CodeOtp crt = new CodeOtp();
        crt.setCode(Utils.randomCode());
        crt.setUserId(user);
        crt.setUsed(false);
        crt.setExpiredDate(new Date(System.currentTimeMillis() + 900_000));
        CodeOtp response = codeOptRepository.save(crt);
        try {
            sendingMailOtp(user.getEmail(), objTemplate(user.getFirstName(), response.getCode(), response.getExpiredDate()));
        } catch (Exception e) {
            try {
                GenerateSMS.sendMessageOtp(response.getCode(), user);
            } catch (Exception a) {
                throw new Exception(a.getMessage());
            }
        } finally {
            return response;
        }
    }

    @Override
    public CodeOtp activeUser(String code) {
        CodeOtp codeOtp = codeOptRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Otp not found"));
        Date date = codeOtp.getExpiredDate();
        Date dateNow = new Date();
        if (dateNow.after(date)) throw new BussinesException("Token anda sudah kadaluarsa");
        return codeOtp;
    }

    @Transactional
    @Override
    public CodeOtp resendOtp(User user) throws TemplateException, MessagingException, IOException {
        Optional<CodeOtp> otp = codeOptRepository.findByUserId(user.getId());
        if (otp.isEmpty()) {
            return create(user);
        }
        CodeOtp existing = otp.get();
        codeOptRepository.delete(existing);
        return create(user);
    }

    private Map<String, Object> objTemplate(String name, String otp, Date expiredDate) {
        Map<String, Object> template = new HashMap<>();
        template.put("firstName", name);
        template.put("otp", otp);
        template.put("expiredDate", String.valueOf(expiredDate));
        return template;
    }
}
