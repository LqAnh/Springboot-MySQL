package com.example.springsecurity.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.dto.UserDtoV2;
import com.example.springsecurity.exception.ApiRequestException;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.util.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getuser() {
        return ResponseEntity.ok().body(userService.getUser());
    }


    @PostMapping(value = "/signup")
    public String crEm(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return "saved";
    }

    @DeleteMapping("/user/del/{id}")
    public void delEm(@PathVariable long id) {
        userService.delUser(id);
    }


    @PutMapping(value = "/user/up", produces = MediaType.APPLICATION_JSON_VALUE)
    public User upUserbyName(HttpServletRequest request, UserDto userDto) {
        return userService.updateUser(Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION)), userDto);

    }

    @GetMapping("/user/get")
    public UserDtoV2 getAUserInfo(HttpServletRequest request) {
        return userService.getDetailInfo(Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION)));
    }

    @GetMapping(value = "/user/adget/{username}")
    public User getUserInfoAd( @PathVariable String username) {
        User u = userService.getUser(username);
        if (u == null) {
            throw new ApiRequestException("User not found " + username);
        } else {
            return u;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/changepass", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String changePass(HttpServletRequest request,  String oldpass,  String newpass) {
        String username = Utilities.Util.getUsernameFromJwt(request.getHeader(AUTHORIZATION));
        return userService.changePassword(username, oldpass, newpass);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}




