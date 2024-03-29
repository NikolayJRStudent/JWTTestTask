package com.example.jwttesttask.service;

import com.example.jwttesttask.domain.Users;
import com.example.jwttesttask.dto.JwtRequest;
import com.example.jwttesttask.dto.JwtResponse;
import com.example.jwttesttask.dto.RegistrationUserDto;
import com.example.jwttesttask.dto.UserDto;
import com.example.jwttesttask.exception.AppError;
import com.example.jwttesttask.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final JwtTokenUtils jwtTokenUtils;

    private final AuthenticationManager authenticationManager;


    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword()) ){
            return  new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"password failed"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),"user with the same name already exist"), HttpStatus.BAD_REQUEST);
        }

        Users user =userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(),user.getUsername(),user.getEmail()));
    }


    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),"Incorrect login or password" ), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
