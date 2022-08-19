package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.configure.EmailConfig;
import com.bus.ticket.enggine.configure.service.GenerateSMS;
import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.model.Wallet;
import com.bus.ticket.web.repository.UserRepository;
import com.bus.ticket.web.service.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl  extends EmailConfig  implements UserService{

    private WalletService walletService;
    private HistoryBalanceService historyBalanceService;
    private OtpService otpService;
    private UserRoleService userRoleService;

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Autowired
    public UserServiceImpl(Configuration config, WalletService walletService, HistoryBalanceService historyBalanceService, OtpService otpService,UserRoleService userRoleService, UserRepository userRepository, ModelMapper modelMapper) {
        super(config);
        this.walletService = walletService;
        this.historyBalanceService = historyBalanceService;
        this.otpService = otpService;
        this.userRoleService = userRoleService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public User create(UserDto userDto) throws TemplateException, MessagingException, IOException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) throw new BussinesException("Email sudah di gunakan");
        User crt = modelMapper.map(userDto, User.class);
        crt.setBlocked(true);
        crt.setUserRole(userRoleService.getById(2));
        User user = userRepository.save(crt);
        CodeOtp otp = otpService.create(user);
        try{
            sendingMailOtp(user.getEmail(), objTemplate(user.getFirstName(), otp.getCode(), otp.getExpiredDate()));
        } catch (Exception e) {
            try {
                GenerateSMS.sendMessageOtp(otp.getCode(), user);
            } catch (Exception a) {
                  throw new BussinesException("Tidak Bisa Membuat Akun. Dikarena aplikasi ini dalam tahap ujicoba. Kami Developer TiketBus membatasi pembuatan akun baru setiap harinya supaya proses server lebih cepat. Coba Lagi besok pada jam 09.00 WIB");
            }
        }
        return user;
    }

    @Override
    public Page<User> findAll(int page, int size) {
        return null;
    }

    @Override
    public User update(UserDto userDto) {
        return null;
    }

    @Override
    public User active(String userId, String otp) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User tidak di temukan"));
        Wallet wallet = walletService.add(user.getId());
        historyBalanceService.createHistoryFirst(user, wallet);
        return user;
    }

    public Map<String, Object> objTemplate(String name, String otp, Date expiredDate) {
        Map<String, Object> template = new HashMap<>();
        template.put("firstName",  name);
        template.put("otp",  otp);
        template.put("expiredDate" ,  String.valueOf(expiredDate));
        return template;
    }
}
