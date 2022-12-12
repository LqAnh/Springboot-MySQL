package com.example.springsecurity.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class Utilities {
    public static class Util {

        public static String getUsernameFromJwt(String authorizationHeader) {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String refresh_token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(refresh_token);
                    return decodedJWT.getSubject();
                } catch (Exception exception) {
//                log.error("missing");
                }
            }
            return null;
        }
    }
}
