package com.example.solarwatchMVPjav.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SunriseSunsetReport (SunriseSunsetReportResult results){
}
