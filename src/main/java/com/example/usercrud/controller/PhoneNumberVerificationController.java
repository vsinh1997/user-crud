package com.example.usercrud.controller;

import com.example.usercrud.constant.VerificationConst;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class PhoneNumberVerificationController {

    @Value("${TWILIO_ACCOUNT_SID}")
    private String twilioAccountSid;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String twilioAuthToken;

    @Value("${VERIFICATION_SID}")
    private String verificationSid;

    @RequestMapping(method = RequestMethod.GET, path = "/api/phoneNumber/generatorOTP")
    public ResponseEntity<String> generateOTP(@RequestParam String phoneNumberCountry, @RequestParam String phoneNumber) {

        String fullPhoneNumber = "+".concat(phoneNumberCountry).concat(phoneNumber);
        Twilio.init(twilioAccountSid, twilioAuthToken);

        Verification verification = Verification.creator(
                verificationSid,
                fullPhoneNumber,
                VerificationConst.TWILIO_CHANEL_TYPE_SMS
        ).create();

        System.out.println(verification.getStatus());
        log.info("OTP has been successfully generated, and awaits your verification {}", LocalDateTime.now());

        return new ResponseEntity<String>("Your OTP has been sent to your verified phone number", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/phoneNumber/verifyOTP")
    public ResponseEntity<String> verifyOTP(@RequestParam String phoneNumberCountry, @RequestParam String phoneNumber, @RequestParam String verificationCode) {

        String fullPhoneNumber = "+".concat(phoneNumberCountry).concat(phoneNumber);
        Twilio.init(twilioAccountSid, twilioAuthToken);

        if (!isValidVerificationCode(fullPhoneNumber, verificationCode)) {
            return new ResponseEntity<String>("Verify failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<String>("This user's verification has been completed successfully", HttpStatus.OK);

    }

    private boolean isValidVerificationCode(String fullPhoneNumber, String verificationCode) {
        VerificationCheck verificationCheck = VerificationCheck.creator(verificationSid)
                .setTo(fullPhoneNumber)
                .setCode(verificationCode)
                .create();
        return verificationCheck.getValid();
    }
}
