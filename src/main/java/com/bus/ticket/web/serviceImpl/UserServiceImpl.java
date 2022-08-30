package com.bus.ticket.web.serviceImpl;

import com.bus.ticket.enggine.configure.EmailConfig;
import com.bus.ticket.enggine.configure.service.GenerateSMS;
import com.bus.ticket.enggine.exception.BussinesException;
import com.bus.ticket.enggine.exception.NotFoundException;
import com.bus.ticket.enggine.jwt.JwtProvider;
import com.bus.ticket.enggine.jwt.service.UserDetailsServiceImpl;
import com.bus.ticket.web.dto.LoginDto;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.*;
import com.bus.ticket.web.repository.UserRepository;
import com.bus.ticket.web.repository.UserRoleRepository;
import com.bus.ticket.web.service.*;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;
    private OtpService otpService;
    private TemporaryTokenService temporaryTokenService;
    private UserRoleService userRoleService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsService;
    private ModelMapper modelMapper;
    @Autowired
    public UserServiceImpl(Configuration config, WalletService walletService, HistoryBalanceService historyBalanceService, JwtProvider jwtProvider, AuthenticationManager authenticationManager, OtpService otpService, TemporaryTokenService temporaryTokenService, UserRoleService userRoleService,  PasswordEncoder passwordEncoder ,UserRepository userRepository, UserDetailsServiceImpl userDetailsService, ModelMapper modelMapper) {
        super(config);
        this.walletService = walletService;
        this.passwordEncoder = passwordEncoder;
        this.historyBalanceService = historyBalanceService;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.otpService = otpService;
        this.temporaryTokenService = temporaryTokenService;
        this.userRoleService = userRoleService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Transactional
    @Override
    public CodeOtp create(UserDto userDto) throws TemplateException, MessagingException, IOException {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) throw new BussinesException("Email sudah di gunakan");
        User crt = modelMapper.map(userDto, User.class);
        crt.setBlocked(true);
        crt.setUserRole(userRoleService.getById(2));
        crt.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
        return otp;
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
    public TemporaryToken authorities(LoginDto loginDto) {
        String jwt = authories(loginDto);
        User user = userRepository.findByEmailAndBlockedIsFalse(loginDto.getEmail()).get();
        return temporaryTokenService.create(jwt, user);
    }

    @Transactional
    @Override
    public User active(String userId, String code) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User tidak di temukan"));
        otpService.activeUser(code);
        Wallet wallet = walletService.add(user);
        historyBalanceService.createHistoryFirst(user, wallet);
        user.setBlocked(false);
        return userRepository.save(user);
    }

    @Override
    public CodeOtp resendCodeOtp(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User tidak di temukan"));
        return otpService.resendOtp(user);
    }

    private Map<String, Object> objTemplate(String name, String otp, Date expiredDate) {
        Map<String, Object> template = new HashMap<>();
        template.put("firstName",  name);
        template.put("otp",  otp);
        template.put("expiredDate" ,  String.valueOf(expiredDate));
        return template;
    }
    private String authories(LoginDto user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BussinesException("Email atau Password Salah");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        return jwtProvider.generateJwtToken(userDetails);
    }

}
