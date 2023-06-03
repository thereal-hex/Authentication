package com.google.demo.Validator;

import com.google.demo.Entity.UserLoginEntity;
import com.google.demo.Repository.UserLoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Validation {

    private final UserLoginRepository userLoginRepository;

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
            throw new Exception("mail Error");
        }
    }
}
