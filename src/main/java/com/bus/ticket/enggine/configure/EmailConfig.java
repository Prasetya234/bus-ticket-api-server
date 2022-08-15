package com.bus.ticket.enggine.configure;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class EmailConfig {
    private Configuration config;
    private static JavaMailSenderImpl mailSender;

    @Autowired
    public EmailConfig(JavaMailSenderImpl mailSender, Configuration config) {
        this.mailSender = mailSender;
        this.config = config;
    }

    @Value("${spring.mail.username}")
    private static String EMAIL_SENDER;
    @Value("${spring.mail.password}")
    private static String PASSWORD_SENDER;


    public static void requestEmail(String emailTo, Map<String, Object> components)throws IOException, TemplateException, MessagingException {
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(EMAIL_SENDER);
        mailSender.setPassword(PASSWORD_SENDER);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);
    }

    public static void send() {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXE D_RELATED,
                StandardCharsets.UTF_8.name());
        Template t = config.getTemplate("email-login.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, components);
        String from = EMAIL_SENDER;
        helper.setTo(emailTo);
        helper.setText(html, true);
        helper.setSubject("Access Code Fuex Service");
        helper.setFrom(from);
        mailSender.send(message);
    }
}
