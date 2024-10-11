package com.example.usercrud.controller;

import com.example.usercrud.constant.UserConst;
import com.example.usercrud.dto.user.UserLoginParamDto;
import com.example.usercrud.dto.user.UserRegParamDto;
import com.example.usercrud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = UserConst.USER_MAIN_URL + UserConst.REGISTER_URL)
    public ResponseEntity<String> register(@RequestBody UserRegParamDto param) {
        userService.register(param);
        return new ResponseEntity<>( "Created Successfully",HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = UserConst.USER_MAIN_URL + UserConst.LOGIN_URL)
    public ResponseEntity<String> login(@RequestBody UserLoginParamDto param) {
        userService.login(param);
        return new ResponseEntity<>("Login Successfully",HttpStatus.OK);
    }

}
