package com.bus.ticket.enggine.configure.service;

import com.bus.ticket.web.model.User;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class GenerateSMS {
    public static void sendMessageOtp(String otp, User user) {
        String message = "Pakai kode OTP " + otp + " untuk masuk ke masuk ke akun Website TiketBus.com. JANGAN KASIH KODENYA KE SIAPAPUN, bahkan yang mengaku agen bus dari Website TiketBus.com\n" + user.getId();
        Message.creator( new PhoneNumber(user.getNumberPhone()), new PhoneNumber("+19706388928"), message).create();
    }
}
