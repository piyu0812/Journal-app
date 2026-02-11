package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WheatherService{
    public static final String api_key="63e8d5347951a7b959f97c7d8c9d436f";
    public static final String API="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city)
    {
        String final_api=API.replace("CITY",city).replace("API_KEY",api_key);
        ResponseEntity<WeatherResponse> response=restTemplate.exchange(final_api, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body=response.getBody();
        return body;
    }
}
