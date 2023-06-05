package com.google.demo.Validator;

import com.google.demo.Entity.UserLoginEntity;
import com.google.demo.Repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class Validation {

    private final UserLoginRepository userLoginRepository;
    private static final long OTP_VALID_DURATION = 2 * 60 * 1000;

    public void isPresent(String username) throws Exception {
        UserLoginEntity userLoginEntity = userLoginRepository.getByName(username);
        if (userLoginEntity != null)
            throw new Exception("Duplicated UserName");

    }

    public void updatePresent(String userName, Long id) throws Exception {
        UserLoginEntity userLoginEntity = userLoginRepository.getUserLoginEntitiesByNameUpdate(userName, id);
        if (userLoginEntity != null)
            throw new Exception("Error");
    }

    public void mailPresent(String mail) throws Exception {
        UserLoginEntity userLoginEntity = userLoginRepository.getUserLoginEntitiesByEmail(mail);
        if (userLoginEntity != null)
            throw new Exception("Duplicated Mail");
    }

    public void updateMailPresent(String mail, Long id) throws Exception {
        UserLoginEntity userLoginEntity = userLoginRepository.getUserLoginEntitiesByEmailAndId(mail, id);
        if (userLoginEntity != null) {
            throw new Exception("Mail Error");
        }
    }

    public void generateOtp(UserLoginEntity entity) throws Exception {

        SecureRandom random = new SecureRandom();
        UserLoginEntity userLoginEntity = userLoginRepository.getByName(entity.getName());
        String otp = String.format("%06d", random.nextInt(999999));

        if (userLoginEntity != null) {
            userLoginEntity.setOtpCode(otp);
            userLoginEntity.setOtpRequestTime(new Date());
            userLoginRepository.save(userLoginEntity);
        } else
            throw new Exception("UserName Not Found");
    }

    public void validateOtp(String name, String password, String otpCode) throws Exception {

        UserLoginEntity userLoginEntity = userLoginRepository.getByOtpCodeAndName(name, otpCode);

        if (userLoginEntity == null) {
            throw new Exception("Something Went Wrong!");
        } else if (userLoginEntity.getOtpRequestTime().getTime() + OTP_VALID_DURATION < System.currentTimeMillis()) {
            userLoginEntity.setOtpCode(null);
            userLoginEntity.setOtpRequestTime(null);
            userLoginRepository.save(userLoginEntity);
            throw new Exception("Times Up");
        } else {
            userLoginEntity.setPassword(password);
            userLoginEntity.setOtpRequestTime(null);
            userLoginEntity.setOtpCode(null);
            userLoginRepository.save(userLoginEntity);
        }
    }
}
