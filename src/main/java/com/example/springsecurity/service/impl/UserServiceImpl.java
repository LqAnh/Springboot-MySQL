package com.example.springsecurity.service.impl;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.dto.UserDtoV2;
import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repo.RoleRepo;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.service.impl.city_district_sex_dantoc_kvut.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final CityServiceImpl cityService;
    private final DistrictServiceImpl districtService;
    private final SexServiceImpl sexService;
    private final DantocServiceImpl dantocService;
    private final KvutServiceImpl kvutService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
//            throw new ApiRequestException("Username is incorrect");
        } else {
            log.info("User found {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getFullname());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Saving new role {} to user {} to database", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Getting user {} to database", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUser() {
        log.info("Getting all user database");
        return userRepo.findAll();
    }

    @Override
    public void createUser(UserDto userDto) {
        if (!checkInput(userDto.getFullname(), userDto.getUsername(), userDto.getPassword(), userDto.getPhoneNumber())) {
            throw new ApiRequestException("Field Can Not Be Empty");
        } else if (!checkUsername(userDto.getUsername())) {
            throw new ApiRequestException("Username already taken");
        } else {
            User user = new User();
            user.setCityCode(userDto.getCityCode());
            user.setDantoc(userDto.getDantoc());
            user.setDistrictCode(userDto.getDistrictCode());
            user.setFullname(userDto.getFullname());
            user.setKvut(userDto.getKvut());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setSex(userDto.getSex());
            user.setUsername(userDto.getUsername());
            user.setDateOfBirth(userDto.getDateOfBirth());
            Role role = roleRepo.findByName("ROLE_USER");
            user.setRoles(Arrays.asList(role));
            userRepo.save(user);
            log.info("Create new user {}", user);
            ///return user;
        }
    }

    @Override
    public void delUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User findUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public User updateUser(String username, UserDto userDto) {
        User updateU = userRepo.findByUsername(username);
        if (updateU == null) {
            throw new ApiRequestException("user not exist: " + username);
        } else if (!checkInput(userDto.getFullname(), userDto.getPhoneNumber(), userDto.getDateOfBirth())) {
            throw new ApiRequestException("Field Can Not Be Empty");
        } else {
            updateU.setCityCode(userDto.getCityCode());
            updateU.setDantoc(userDto.getDantoc());
            updateU.setDistrictCode(userDto.getDistrictCode());
            updateU.setFullname(userDto.getFullname());
            updateU.setKvut(userDto.getKvut());
            updateU.setPhoneNumber(userDto.getPhoneNumber());
            updateU.setSex(userDto.getSex());
            updateU.setUsername(username);
            updateU.setDateOfBirth(userDto.getDateOfBirth());
            userRepo.save(updateU);
            log.info("Change saved for user {}", updateU);
        }
        return updateU;
    }

    @Override
    public UserDtoV2 getDetailInfo(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null){
            throw new ApiRequestException("User not found " + username);
        }else {
            long city = user.getCityCode();
            long district = user.getDistrictCode();
            long sex = Long.parseLong(user.getSex());
            long kvut = user.getKvut();
            long dantoc = Long.parseLong(user.getDantoc());
            UserDtoV2 detail = new UserDtoV2(user.getFullname(), username, user.getPhoneNumber(), sex, dantoc,kvut,city,district,user.getDateOfBirth());
            return detail;
        }

    }

    @Override
    public String changePassword(String username, String oldPass, String newPass) {
        User user = userRepo.findByUsername(username);
        if (user == null){
            throw new ApiRequestException("User not found " + username);
        }else {
            if(passwordEncoder.matches(oldPass,user.getPassword())){
                user.setPassword(passwordEncoder.encode(newPass));
                userRepo.save(user);
                return "Password Changed";
            }else {
                throw new ApiRequestException("Your Current Password Is Not Correct");
            }

        }
    }


    private boolean checkUsername(String userName) {
        User user = findUsername(userName);
        return user == null;
    }

    private boolean checkInput(String fullName, String userName, String password, String phoneNumber, String dateOfBirth) {
        return !userName.equals("") && !fullName.equals("") && !password.equals("") && !phoneNumber.equals("") && !dateOfBirth.equals("");
    }

    private boolean checkInput(String fullName, String password, String phoneNumber , String dateOfBirth) {
        return  !fullName.equals("") && !password.equals("") && !phoneNumber.equals("") &&!dateOfBirth.equals("");
    }
    private boolean checkInput(String fullName, String phoneNumber, String dateOfBirth) {
        return  !fullName.equals("") && !phoneNumber.equals("") && !dateOfBirth.equals("") ;
    }
}
