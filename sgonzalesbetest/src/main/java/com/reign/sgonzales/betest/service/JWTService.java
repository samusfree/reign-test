package com.reign.sgonzales.betest.service;

public interface JWTService {
    String getJWTToken(String user, String role);

    boolean validateAccessToken(String token);

    String getUserName(String token);

    String getRole(String token);
}
