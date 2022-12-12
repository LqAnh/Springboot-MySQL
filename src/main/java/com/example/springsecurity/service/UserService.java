package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.dto.UserDtoV2;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUser();

    void createUser(UserDto userDto);

    void delUser(long id);

    User findUsername(String userName);

//    User updateUser(long id, UserDto userDto);

    User updateUser(String username, UserDto userDto);

    UserDtoV2 getDetailInfo(String username);

    String changePassword(String username, String oldPass, String newPass);


}
