package com.example.solarwatchMVPjav.repository;

import com.example.solarwatchMVPjav.model.entity.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {

  Optional<List<Date>> findByDate(LocalDate date);
}
