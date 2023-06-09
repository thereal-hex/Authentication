package com.google.demo.Service;

import com.google.demo.Entity.UserLoginEntity;
import com.google.demo.Repository.UserLoginRepository;
import com.google.demo.Validator.AuthenticationValidation;
import com.google.demo.Validator.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserLoginRepository repository;
    private final AuthenticationValidation authenticationValidation;
    private final Validation validation;

    public UserLoginEntity create(UserLoginEntity entity) throws Exception {
        validation.isPresent(entity.getName());
        validation.mailPresent(entity.getEmail());
        entity.setCreatedAt(new Date());
        entity.setUpdatedAt(new Date());
        return repository.save(entity);
    }

    public UserLoginEntity update(UserLoginEntity entity, Long id) throws Exception {
        validation.updatePresent(entity.getName(), entity.getId());
        validation.updateMailPresent(entity.getEmail(), entity.getId());
        entity.setUpdatedAt(new Date());
        entity.setId(id);
        return repository.save(entity);
    }

    public void delete(UserLoginEntity id) {
        repository.delete(id);
    }

    public void authentication(String inputUserName, String inputPassword) throws Exception {
        authenticationValidation.authValidation(inputPassword, inputUserName);
    }

    public void generateOtp(UserLoginEntity entity) throws Exception {
        validation.generateOtp(entity);
    }

    public void validateOtp(UserLoginEntity entity) throws Exception {
        validation.validateOtp(entity.getName(), entity.getPassword(), entity.getOtpCode());
    }

    public List<UserLoginEntity> findAll() {
        return repository.findAll();
    }


}