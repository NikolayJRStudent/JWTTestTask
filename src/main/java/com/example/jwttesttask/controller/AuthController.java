package com.example.jwttesttask.controller;
import com.example.jwttesttask.dto.JwtRequest;
import com.example.jwttesttask.dto.RegistrationUserDto;
import com.example.jwttesttask.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;



    @PostMapping("/user/add")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){

        return authService.createNewUser(registrationUserDto);
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){

        return authService.createAuthToken(authRequest);
    }

}
