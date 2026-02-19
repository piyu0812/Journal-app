package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Constants.Placeholders;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WheatherService{

    @Value("${weather.api.key}")
    public String api_key;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

    public WeatherResponse getWeather(String city)
    {
        String final_api=appCache.app_Cache.get(AppCache.keys.weather_api.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,api_key);
        ResponseEntity<WeatherResponse> response=restTemplate.exchange(final_api, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body=response.getBody();
        return body;
    }
}
