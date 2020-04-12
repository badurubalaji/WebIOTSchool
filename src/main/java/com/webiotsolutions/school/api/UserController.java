package com.webiotsolutions.school.api;

import com.webiotsolutions.school.models.AuthenticationRequest;
import com.webiotsolutions.school.models.AuthenticationResponse;
import com.webiotsolutions.school.security.util.JwtUtil;
import com.webiotsolutions.school.services.SchoolUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private SchoolUserService userDetailsService;
    @GetMapping(path = "/")
    public String home() {
        return "Home";
    }
    @GetMapping(path = "/home")
    public String start() {
        return "Welcome Home";
    }
    @PostMapping(path = "authenticate")
    public ResponseEntity<?> user(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
