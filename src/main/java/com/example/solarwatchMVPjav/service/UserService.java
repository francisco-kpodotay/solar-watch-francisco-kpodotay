package com.example.solarwatchMVPjav.service;

import com.example.solarwatchMVPjav.model.dto.JwtResponse;
import com.example.solarwatchMVPjav.model.dto.NewUser;
import com.example.solarwatchMVPjav.model.entity.Role;
import com.example.solarwatchMVPjav.model.entity.SolarWatchUser;
import com.example.solarwatchMVPjav.repository.UserRepository;
import com.example.solarwatchMVPjav.security.jwt.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder encoder;


  public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.encoder = encoder;
  }

  private void addRoleFor(SolarWatchUser user, Role role) throws Exception {
    throw new Exception("Method not implemented");
   /* Set<Role> oldRoles = user.getRoles();

    Set<Role> copiedRoles = new HashSet<>(oldRoles);
    copiedRoles.add(role);

    userRepository.updateUser(new SolarWatchUser(user.username(), user.password(), Set.copyOf(copiedRoles)));*/
  }

  public boolean registerUser(NewUser newUser) {
    Optional<SolarWatchUser> user = userRepository.findByUsername(newUser.username());
    if (user.isEmpty()) {
      userRepository.save(createNewUserEntity(newUser));
      return true;
    } else {
      return false;
    }
  }

  private SolarWatchUser createNewUserEntity(NewUser newUser) {
    SolarWatchUser solarWatchUser = new SolarWatchUser();
    solarWatchUser.setUsername(newUser.username());
    solarWatchUser.setPassword(encoder.encode(newUser.password()));
    solarWatchUser.setRoles(Set.of(Role.ROLE_USER));
    return solarWatchUser;
  }

  public ResponseEntity<JwtResponse> loginUser(NewUser newUser) {
    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(newUser.username(), newUser.password()));

    String jwt = jwtUtils.generateJwtToken(authentication);

    /*User userDetails = (User) authentication.getPrincipal();
    List<String> roles =
            userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
    Optional<SolarWatchUser> userEntity = userRepository.findByUsername(newUser.username());*/

    return ResponseEntity.ok(new JwtResponse(jwt));
  }


}
