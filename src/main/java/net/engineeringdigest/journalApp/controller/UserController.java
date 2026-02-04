package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @GetMapping
    public List<User> getAllUsers()
    {
        return userservice.getAll();
    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userInDb = userservice.findByUserName(username);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userservice.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
