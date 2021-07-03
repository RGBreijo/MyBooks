package com.example.mybooks.service;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class OpenLibService
{


    public void test()
    {



        String test = "https://www.googleapis.com/books/v1/volumes?q=time&printType=magazines&key=AIzaSyAIHh_8U23Sgm_Ac6o1gLHoSEoEkab4Koc";
        String bookName = "Harry Potter and the Half-Blood Prince";

        final String API_KEY = "AIzaSyAIHh_8U23Sgm_Ac6o1gLHoSEoEkab4Koc";

        String URI = "https://www.googleapis.com/books/v1/volumes?q=\"" + bookName +
                     "”&printType=books&key=" + API_KEY;


        WebClient webClient = WebClient.create();

        Mono<String> what = webClient.get().uri(URI).retrieve().bodyToMono(String.class);

        try
        {
            JSONObject obj = new JSONObject(what.block());

            JSONObject ay = new JSONObject(obj.getJSONArray("items").get(0).toString());

            System.out.println(ay.getJSONObject("volumeInfo").getJSONArray("authors"));
            System.out.println(ay.getJSONObject("volumeInfo").get("description"));


        }catch (JSONException e)
        {

        }

//        System.out.println(what.block());
    }
}
