package org.example.miniprojetback.Services;

import org.example.miniprojetback.Dtos.request.AuthRequest;
import org.example.miniprojetback.Dtos.response.AuthResponse;


public interface IAuthService {
    AuthResponse login(AuthRequest authRequest);
    void register(AuthRequest authRequest);
}