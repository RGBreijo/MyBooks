package com.example.mybooks.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class OpenLibService
{

    private Mono<String> googleBookApi(String bookName)
    {
        final String API_KEY = "AIzaSyAIHh_8U23Sgm_Ac6o1gLHoSEoEkab4Koc";

        WebClient webClient = WebClient.create();

        String URI = "https://www.googleapis.com/books/v1/volumes?q=\"" + bookName +
                "”&printType=books&key=" + API_KEY;

        return  webClient.get().uri(URI).retrieve().bodyToMono(String.class);
    }


    private JSONObject cleanBookApi(Mono<String> bookInfo) throws JSONException
    {
        JSONObject bookInfoJson = new JSONObject(bookInfo.block());
        return new JSONObject(bookInfoJson.getJSONArray("items").get(0).toString());
    }

    private String getBookDescription(JSONObject bookInfoItemJson) throws JSONException
    {
        Object description = bookInfoItemJson.getJSONObject("volumeInfo").get("description");
        return (String) description;
    }

    public String bookDescription(String bookName)
    {
        try
        {
            JSONObject bookInfo = cleanBookApi(googleBookApi(bookName));
            return getBookDescription(bookInfo);

        }catch(JSONException e)
        {
           return "";
        }
    }

}
