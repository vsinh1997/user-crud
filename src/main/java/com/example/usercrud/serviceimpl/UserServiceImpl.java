package com.example.usercrud.serviceimpl;

import com.example.usercrud.dto.user.UserLoginParamDto;
import com.example.usercrud.dto.user.UserRegParamDto;
import com.example.usercrud.model.UserEntity;
import com.example.usercrud.repository.UserRepository;
import com.example.usercrud.service.UserService;
import com.example.usercrud.utils.CheckUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserRegParamDto param) {

        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(param, user);

        // TODO:

    }

    @Override
    public void login(UserLoginParamDto param) {
        if (CheckUtils.isNotEmpty(param.getEmail())) {
            if (userRepository.findByEmailAndPassword(param.getEmail(), param.getPassword()).isEmpty()) {
                throw new IllegalArgumentException("Email or password is incorrect");
            }
        }

        if (CheckUtils.isNotEmpty(param.getPhone())) {
            if (userRepository.findByPhoneAndPassword(param.getPhone(), param.getPassword()).isEmpty()) {
                throw new IllegalArgumentException("Phone number or password is incorrect");
            }
        }


    }

    @Override
    public boolean isExistUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
