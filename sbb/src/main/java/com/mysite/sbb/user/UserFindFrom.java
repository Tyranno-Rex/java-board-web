package com.mysite.sbb.user;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFindFrom {
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;
    @NotEmpty(message =  "이메일은 필수항목입니다.")
    private String email;
}
