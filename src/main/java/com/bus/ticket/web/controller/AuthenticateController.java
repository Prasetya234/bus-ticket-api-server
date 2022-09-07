package com.bus.ticket.web.controller;

import com.bus.ticket.enggine.response.CommonResponse;
import com.bus.ticket.enggine.response.ResponseHelper;
import com.bus.ticket.web.dto.HistoryJoinAdminDto;
import com.bus.ticket.web.dto.LoginDto;
import com.bus.ticket.web.dto.UserDto;
import com.bus.ticket.web.model.CodeOtp;
import com.bus.ticket.web.model.HistoryJoinAdmin;
import com.bus.ticket.web.model.TemporaryToken;
import com.bus.ticket.web.model.User;
import com.bus.ticket.web.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/authenticate")
public class AuthenticateController {
    private UserService userService;
    @Autowired
    public AuthenticateController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @PostMapping("/signup")
    public CommonResponse<CodeOtp> signUp(@RequestBody UserDto user) {
        return ResponseHelper.successResponse(userService.create(user));
    }
    @PostMapping("/signin")
    public CommonResponse<TemporaryToken> signIn(@RequestBody LoginDto user){
        return ResponseHelper.successResponse(userService.authorities(user));
    }
    @GetMapping("/user-active-code/{userId}")
    public CommonResponse<User> active(@PathVariable(name = "userId") String userId, @RequestParam(name = "code") String code) {
        return ResponseHelper.successResponse(userService.active(userId, code));
    }
    @GetMapping("/resend-active-code/{userId}")
    public CommonResponse<CodeOtp> resendActiveCode(@PathVariable(name = "userId") String userId) {
        return ResponseHelper.successResponse(userService.resendCodeOtp(userId));
    }
    @PutMapping("/change-email-resend-code")
    public CommonResponse<CodeOtp> changeEmailActiveCode(@RequestParam(name = "email") String email,  @RequestParam(name = "userId") String userId) {
        return ResponseHelper.successResponse(userService.changeEmail(email, userId));
    }

    @PostMapping("/mitra-join/{userId}")
    public CommonResponse<HistoryJoinAdmin> joinMitraBus(@PathVariable(name = "userId") String userId, @RequestBody HistoryJoinAdminDto historyJoinAdminDto) {
        return ResponseHelper.successResponse(userService.joinMitraBus(userId, historyJoinAdminDto));
    }
}
