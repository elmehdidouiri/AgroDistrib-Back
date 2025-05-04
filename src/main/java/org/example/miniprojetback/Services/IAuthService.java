package org.example.miniprojetback.Services;

import org.example.miniprojetback.DAOs.request.AuthRequest;
import org.example.miniprojetback.DAOs.response.AuthResponse;


public interface IAuthService {
    AuthResponse login(AuthRequest authRequest);
    void register(AuthRequest authRequest);
}