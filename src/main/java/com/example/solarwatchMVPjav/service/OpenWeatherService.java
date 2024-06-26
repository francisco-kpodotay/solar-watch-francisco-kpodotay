package com.example.solarwatchMVPjav.service;

import com.example.solarwatchMVPjav.model.entity.City;
import com.example.solarwatchMVPjav.model.dto.OpenWeatherReport;
import com.example.solarwatchMVPjav.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherService {
  private final RestTemplate restTemplate;
  private final CityRepository cityRepository;
  private static final String API_KEY = System.getenv("API_KEY");

  public OpenWeatherService(RestTemplate restTemplate, CityRepository cityRepository) {
    this.restTemplate = restTemplate;
    this.cityRepository = cityRepository;
  }

  public City getOpenWeatherReport(String inputCityName) {
    City alreadyExistingCity = cityRepository.findByName(inputCityName).orElse(null);
    //System.out.println(alreadyExistingCity != null ? alreadyExistingCity.getName() : null);
    if (alreadyExistingCity == null) {
      OpenWeatherReport response = fetchCityData(inputCityName);
      return saveCity(response);
    }
    return alreadyExistingCity;
  }

  private OpenWeatherReport fetchCityData(String cityName) {
    String url = String.format("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=1&appid=%s", cityName, API_KEY);
    OpenWeatherReport[] response = restTemplate.getForObject(url, OpenWeatherReport[].class);
    return response[0];
  }

  private City saveCity(OpenWeatherReport response) {
    City city = new City();
    city.setName(response.name());
    city.setLat(response.lat());
    city.setLon(response.lon());
    city.setState(response.state());
    city.setCountry(response.country());
    cityRepository.save(city);
    return city;
  }

}
