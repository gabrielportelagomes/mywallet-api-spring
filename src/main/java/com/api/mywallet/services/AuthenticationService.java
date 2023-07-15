package com.api.mywallet.services;

import com.api.mywallet.exception.UserAlreadyExistsException;
import com.api.mywallet.model.user.AuthenticationDTO;
import com.api.mywallet.model.user.RegisterDTO;
import com.api.mywallet.model.user.SignInResponseDTO;
import com.api.mywallet.model.user.User;
import com.api.mywallet.repositories.UserRepository;
import com.api.mywallet.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    public SignInResponseDTO signIn(AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new SignInResponseDTO(token);
    }

    public void signUp(RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null) {
            throw new UserAlreadyExistsException("User Already Exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);
    }
}
