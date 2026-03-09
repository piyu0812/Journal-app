package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.utils.JWUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetails;

    @Autowired
    private JWUtils jwtUtils;

    @Autowired
    private UserService userservice;
    @GetMapping("/health-check")
    public String healthcheck (){
        return "OK";
    }
    @PostMapping("/signup")
    public void signup(@RequestBody User user)
    {
        userservice.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails details=userDetails.loadUserByUsername(user.getUserName());
            String jwt=jwtUtils.generateToken(details.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        }
        catch (Exception e)
        {
            log.error("Error occured while authentication ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);

        }
    }
}
