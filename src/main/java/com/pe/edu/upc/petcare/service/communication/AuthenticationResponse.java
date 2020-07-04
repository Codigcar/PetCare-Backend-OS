package com.pe.edu.upc.petcare.service.communication;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String username;
    private final String token;
}
