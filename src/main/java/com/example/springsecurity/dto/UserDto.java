package com.example.springsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String phoneNumber;
    private String sex;
    private String dantoc;
    private int kvut;
    private int cityCode;
    private int districtCode;
    private String dateOfBirth;

}
