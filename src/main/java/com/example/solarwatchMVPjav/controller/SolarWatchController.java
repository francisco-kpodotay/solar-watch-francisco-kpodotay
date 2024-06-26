package com.example.solarwatchMVPjav.controller;

import com.example.solarwatchMVPjav.service.SunriseSunsetService;
import com.ibm.icu.text.Transliterator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solar")
public class SolarWatchController {
  private final SunriseSunsetService sunriseSunsetService;

  public SolarWatchController(SunriseSunsetService sunriseSunsetService) {
    this.sunriseSunsetService = sunriseSunsetService;
  }

  private String transliterate(String input) {
    Transliterator transliterator = Transliterator.getInstance("Any-Latin; Latin-ASCII");
    return transliterator.transliterate(input);
  }

  @GetMapping("/hello")
  public String sayHello() {
    return "Hello";
  }

  @GetMapping("/current")
  public String getSunsetSunrise(@RequestParam String city, @RequestParam String date){
    //http://localhost:8080/solar?city=London&date=2022-7-25
   return sunriseSunsetService.getSunriseSunsetReportResults2(transliterate(city), date);
  }



}
