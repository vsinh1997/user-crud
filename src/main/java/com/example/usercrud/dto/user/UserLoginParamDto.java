package com.example.usercrud.dto.user;

import com.example.usercrud.dto.common.CommonDto;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UserLoginParamDto extends CommonDto {

    @Nullable
    private String email;

    @Nullable
    private String phone;

    private String password;

}
