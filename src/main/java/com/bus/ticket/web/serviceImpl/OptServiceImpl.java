
package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.utils.Utils;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.repository.CodeOptRepository;
import com.bus.ticket.web.service.OtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class OptServiceImpl implements OtpService {
    private CodeOptRepository codeOptRepository;

    public OptServiceImpl(CodeOptRepository codeOptRepository) {
        this.codeOptRepository = codeOptRepository;
    }

    @Override
    public CodeOtp create(User user) {
        Optional<CodeOtp> checking = codeOptRepository.findByUserId(user.getId());
        if (checking.isPresent()) codeOptRepository.delete(checking.get());
        CodeOtp crt = new CodeOtp();
        crt.setCode(Utils.randomCode());
        crt.setUserId(user);
        crt.setExpiredDate(new Date(System.currentTimeMillis() + 900_000));
        return codeOptRepository.save(crt);
    }
}
