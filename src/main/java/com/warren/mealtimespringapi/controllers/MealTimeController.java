package com.warren.mealtimespringapi.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class MealTimeController {
    @Value("${yelp.api.ley}")
    private String apiKey;

    private String api_Address="https://api.yelp.com/v3/businesses/search";

}
