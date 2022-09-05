package com.bus.ticket.utils;


import com.bus.ticket.utils.HttpInformation.IpAddress;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class Utils {

    @Autowired
    private static final int LIMITED = 6;
    private IpAddress ipAddress;

    public static String randomCode() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < LIMITED) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
    public  String getIpAdress() {
        return ipAddress.getClientIpAddressIfServletRequestExist();
    }
}
