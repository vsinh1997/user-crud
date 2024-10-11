package com.example.usercrud.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@RestController
@Slf4j
public class MailVerificationController {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMailAddress;

    public MailVerificationController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping(method = RequestMethod.GET, path = "api/mail/sendVerificationMail")
    public ResponseEntity<String> sendVerificationMail(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {

        String senderName = "Nguyen Canh Duong";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Your verification code is bellow,<br>"
                + "[[verificationCode]],<br>"
                + "Thank you,<br>"
                + "株式会社リッケイ";
        content = content.replace("[[name]]", email);
        content = content.replace("[[verificationCode]]", getRandom6DigitString());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromMailAddress, senderName);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);

        javaMailSender.send(mimeMessage);

        return new ResponseEntity<String>("Your verification code is sent successfully, please check your email!", HttpStatus.OK);

    }


    private String getRandom6DigitString() {

        Random random = new Random();
        int randomNumber = 100000 + random.nextInt(900000);
        char randomChar = (char) ('A' + random.nextInt(26));

        return String.valueOf(randomNumber).concat(String.valueOf(randomChar));

    }

}
