package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/login")
//    public String login(@RequestParam String username,
//                        @RequestParam String password) {
//
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            if (authentication.isAuthenticated()) {
//                return "Login Successful";
//            } else {
//                return "Login Failed";
//            }
//
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }


    @GetMapping("/google-success")
    public String googleLoginSuccess(Authentication authentication) {

        String username = authentication.getName();

        return jwtUtil.generateToken(username); // jwt return
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(username); // token return
            } else {
                return "Login Failed";
            }

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
