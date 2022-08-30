
package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.utils.Utils;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.CodeOptRepository;
import com.bus.ticket.web.service.OtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class OptServiceImpl implements OtpService {
    private CodeOptRepository codeOptRepository;

    @Autowired
    public OptServiceImpl(CodeOptRepository codeOptRepository) {
        this.codeOptRepository = codeOptRepository;
    }

    @Transactional
    @Override
    public CodeOtp create(User user) {
        Optional<CodeOtp> checking = codeOptRepository.findByUserId(user.getId());
        if (checking.isPresent()) codeOptRepository.delete(checking.get());
        CodeOtp crt = new CodeOtp();
        crt.setCode(Utils.randomCode());
        crt.setUserId(user);
        crt.setUsed(false);
        crt.setExpiredDate(new Date(System.currentTimeMillis() + 900_000));
        return codeOptRepository.save(crt);
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
    public CodeOtp resendOtp(User user) {
        Optional<CodeOtp> otp = codeOptRepository.findByUserId(user.getId());
        if (otp.isEmpty()) {
            return create(user);
        }
        CodeOtp existing = otp.get();
        codeOptRepository.delete(existing);
        return create(user);
    }
}
