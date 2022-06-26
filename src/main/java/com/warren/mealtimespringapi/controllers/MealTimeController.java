package com.warren.mealtimespringapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpRequest;

@RestController
@CrossOrigin(origins="*")
public class MealTimeController {
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.address}")
    private String api_Address;

    @GetMapping("/restaurants")
    public ResponseEntity<?> getRandomRestaurantInCity(@RequestParam(name="cityName") String cityName, @RequestParam (name="price") int price,
                                                       @RequestParam(name="cuisine") String cuisineType, @RequestParam(name="limit") int limit){
        RestTemplate restTemplate = new RestTemplate();
        String uri = UriComponentsBuilder.fromHttpUrl(api_Address)
                .queryParam("location", cityName)
                .queryParam("limit", limit)
                .queryParam("term",  cuisineType)
                .queryParam("price", price)
                .queryParam("radius", 15000)
                .encode()
                .toUriString();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        return response;
    }

}
