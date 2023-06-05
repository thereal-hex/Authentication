package com.google.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserLoginEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "users")
    List<RoleEntity> roleEntities;

    @Column(name = "otp_code")
    private String otpCode;

    @Column(name = "otp-request-time")
    private Date otpRequestTime;
}
