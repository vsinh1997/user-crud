package com.example.usercrud.dto.user;

import com.example.usercrud.dto.common.CommonDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserRegParamDto extends CommonDto {

    private String name;

    private String email;

    private String phone;

    private String password;

    private String address;

    private String delFlag;

}
