package com.google.demo.Controller;

import com.google.demo.Entity.UserLoginEntity;
import com.google.demo.Service.UserLoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/api/user-login"})
@RequiredArgsConstructor
public class UserLoginController {

    private final UserLoginService userLoginService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserLoginEntity userLoginEntity) throws Exception {
        return ResponseEntity.ok(userLoginService.create(userLoginEntity));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserLoginEntity userLoginEntity, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userLoginService.update(userLoginEntity, id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delete(@RequestBody UserLoginEntity id) {
        userLoginService.delete(id);
        return ResponseEntity.ok("successfully deleted");
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> authentication(@RequestBody UserLoginEntity entity) throws Exception {
        userLoginService.authentication(entity.getName(), entity.getPassword());
        return ResponseEntity.ok("Login SuccessFully");
    }

    @PostMapping("/otp")
    public ResponseEntity<?> otp(@RequestBody UserLoginEntity entity) throws Exception {
        userLoginService.generateOtp(entity);
        return ResponseEntity.ok("otp code is successfully generated!");
    }

    @PostMapping("/otp-validation")
    public ResponseEntity<?> otpValidation(@RequestBody UserLoginEntity entity) throws Exception {
        userLoginService.validateOtp(entity);
        return ResponseEntity.ok("password is successfully changed");
    }

    @PostMapping("/list")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userLoginService.findAll());
    }


}
