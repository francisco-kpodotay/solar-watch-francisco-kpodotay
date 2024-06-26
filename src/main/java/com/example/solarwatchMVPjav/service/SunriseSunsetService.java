package com.example.solarwatchMVPjav.service;

import com.example.solarwatchMVPjav.model.entity.City;
import com.example.solarwatchMVPjav.model.entity.Date;
import com.example.solarwatchMVPjav.model.dto.SunriseSunsetReport;
import com.example.solarwatchMVPjav.repository.DateRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class SunriseSunsetService {
  private final RestTemplate restTemplate;
  private final DateRepository dateRepository;
  private final OpenWeatherService openWeatherService;

  public SunriseSunsetService(RestTemplate restTemplate, OpenWeatherService openWeatherService, DateRepository dateRepository) {
    this.restTemplate = restTemplate;
    this.openWeatherService = openWeatherService;
    this.dateRepository = dateRepository;
  }

  public String getSunriseSunsetReportResults2(String cityString, String dateString) {
    City city = openWeatherService.getOpenWeatherReport(cityString);
    double lat = city.getLat();
    double lon = city.getLon();

    LocalDate inputDate = convertStringToLocalDate(dateString);

    List<Date> existingDates = dateRepository.findByDate(inputDate).orElse(null);

    Date properDate = existingDates != null
            ? existingDates.stream()
            .filter(date -> date.getCity().getCityId() == city.getCityId())
            .findFirst()
            .orElse(null)
            : null;

    if (properDate == null) {
      SunriseSunsetReport response = fetchSunsetSunrise(lat, lon);
      saveNewDate(response, inputDate, city);
      return "Sunset: " + response.results().sunset() + " -- Sunrise: " + response.results().sunrise();
    }

    return "Sunset: " + properDate.getSunset() + " -- Sunrise: " + properDate.getSunrise();
  }

  private LocalDate convertStringToLocalDate(String dateString) {
    String[] parts = dateString.split("-");

    int[] dateArray = new int[parts.length];
    for (int i = 0; i < parts.length; i++) {
      dateArray[i] = Integer.parseInt(parts[i]);
    }
    return LocalDate.of(dateArray[0], dateArray[1], dateArray[2]);
  }

  private SunriseSunsetReport fetchSunsetSunrise(double lat, double lon) {
    String url = String.format("https://api.sunrise-sunset.org/json?lat=" + lat + "&lng=" + lon);
    return restTemplate.getForObject(url, SunriseSunsetReport.class);
  }

  private void saveNewDate(SunriseSunsetReport response, LocalDate inputDate, City city) {
    Date newDate = new Date();
    newDate.setDate(inputDate);
    newDate.setSunrise(response.results().sunrise());
    newDate.setSunset(response.results().sunset());
    newDate.setCity(city);
    dateRepository.save(newDate);
  }

}
