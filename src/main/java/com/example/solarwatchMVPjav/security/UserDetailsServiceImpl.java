package com.example.solarwatchMVPjav.security;

import com.example.solarwatchMVPjav.model.entity.Role;
import com.example.solarwatchMVPjav.model.entity.SolarWatchUser;
import com.example.solarwatchMVPjav.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)
          throws UsernameNotFoundException {
    SolarWatchUser userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

    List<SimpleGrantedAuthority> roles = new ArrayList<>();
    for (Role role : userEntity.getRoles()) {
      roles.add(new SimpleGrantedAuthority(role.name()));
    }

    return new User(userEntity.getUsername(), userEntity.getPassword(), roles);
  }
}

