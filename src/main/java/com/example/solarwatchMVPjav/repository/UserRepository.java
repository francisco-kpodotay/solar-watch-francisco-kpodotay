package com.example.solarwatchMVPjav.repository;

import com.example.solarwatchMVPjav.model.entity.SolarWatchUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SolarWatchUser, Long> {
  Optional<SolarWatchUser> findByUsername(String username);
}
