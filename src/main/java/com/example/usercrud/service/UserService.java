package com.example.usercrud.service;

import com.example.usercrud.dto.user.UserLoginParamDto;
import com.example.usercrud.dto.user.UserRegParamDto;
import com.example.usercrud.model.UserEntity;

import java.util.Optional;

public interface UserService {

    void register(UserRegParamDto param);

    void login(UserLoginParamDto param);

    boolean isExistUserByEmail(String email);

    Optional<UserEntity> getUserByEmail(String email);
}
