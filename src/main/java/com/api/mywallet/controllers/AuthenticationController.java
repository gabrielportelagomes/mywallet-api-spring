package com.api.mywallet.controllers;

import com.api.mywallet.model.user.AuthenticationDTO;
import com.api.mywallet.model.user.RegisterDTO;
import com.api.mywallet.model.user.SignInResponseDTO;
import com.api.mywallet.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;


    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody @Valid AuthenticationDTO data) {
        SignInResponseDTO token = service.signIn(data);

        return ResponseEntity.ok(token);
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid RegisterDTO data) {
        service.signUp(data);

        return ResponseEntity.ok().build();
    }
}
