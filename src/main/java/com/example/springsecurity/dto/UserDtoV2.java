package com.example.springsecurity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDtoV2 {
    private String fullname;
    private String username;
    private String phoneNumber;
    private long sex;
    private long dantoc;
    private long kvut;
    private long cityName;
    private long districtName;
    private String dateOfBirth;

    public UserDtoV2(String fullname, String username, String phoneNumber, long sex, long dantoc, long kvut, long cityName, long districtName, String dateOfBirth) {
        this.fullname = fullname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.dantoc = dantoc;
        this.kvut = kvut;
        this.cityName = cityName;
        this.districtName = districtName;
        this.dateOfBirth = dateOfBirth;
    }
}
