package com.example.solarwatchMVPjav.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherReport(String name, double lat, double lon, String country, String state) {
}

/*
"name":"London",
"lat":51.5073219,
"lon":-0.1276474,
"country":"GB",
"state":"England"
*/
