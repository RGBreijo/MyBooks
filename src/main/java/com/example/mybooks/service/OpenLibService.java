package com.example.mybooks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenLibService
{
    @Autowired
    private RestTemplate restTemplate;

    public void test()
    {
        String uri = "https://openlibrary.org/search.json?q=the+lord+of+the+rings";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
         System.out.println(result);

    }
}
