package com.example.solarwatchMVPjav.controller;


import com.example.solarwatchMVPjav.model.dto.JwtResponse;
import com.example.solarwatchMVPjav.model.dto.NewUser;
import com.example.solarwatchMVPjav.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public boolean addUser(@RequestBody NewUser newUser) {
    return userService.registerUser(newUser);
  }

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> loginUser(@RequestBody NewUser newUser) {
    return userService.loginUser(newUser);
  }

}
