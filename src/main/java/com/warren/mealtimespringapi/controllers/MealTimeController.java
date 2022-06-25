package com.warren.mealtimespringapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpRequest;

@RestController
public class MealTimeController {
    @Value("${api.key}")
    private String apiKey;
    @Value("${api.address}")
    private String api_Address;

    @GetMapping("/randomInCity/{cityName}/{price}")
    public ResponseEntity<?> getRandomRestaurantInCity(@PathVariable String cityName, @PathVariable int price){
        RestTemplate restTemplate = new RestTemplate();
        String uri = UriComponentsBuilder.fromHttpUrl(api_Address)
                .queryParam("location", cityName)
                .queryParam("limit", 5)
                .queryParam("price", price)
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
