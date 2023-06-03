package com.google.demo.Repository;

import com.google.demo.Entity.UserLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginEntity, Long> {

    @Query(value = "select t from UserLoginEntity t where t.name = :username")
    UserLoginEntity getByName(String username);

    @Query(value = "select t from UserLoginEntity t where t.name =:username and t.id <> :id")
    UserLoginEntity getUserLoginEntitiesByNameUpdate(String username, Long id);

    @Query(value = "select x from UserLoginEntity x where x.email =:mail")
    UserLoginEntity getUserLoginEntitiesByEmail(String mail);

    @Query(value = "select y from UserLoginEntity y where y.email =:email and y.id <> :id")
    UserLoginEntity getUserLoginEntitiesByEmailAndId(String email, Long id);

    @Query(value = "select x from UserLoginEntity x where x.otpCode =:otp")
    UserLoginEntity getByOtpCode(String otp);

    }
