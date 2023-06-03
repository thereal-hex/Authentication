package com.google.demo.Validator;

import com.google.demo.Entity.UserLoginEntity;
import com.google.demo.Repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class AuthenticationValidation implements Serializable {

    private final UserLoginRepository userLoginRepository;

    public void authValidation(String inputPassword, String inputUserName) throws Exception {
        UserLoginEntity userLoginEntity = userLoginRepository.getByName(inputUserName);
        if (userLoginEntity == null)
            throw new Exception("UsernameNotFound");
        else {
            if (!inputPassword.equals(userLoginEntity.getPassword()))
                throw new Exception("PasswordInCurrent");
        }
    }

}
