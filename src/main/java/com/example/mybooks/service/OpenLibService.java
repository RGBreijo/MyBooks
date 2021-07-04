package com.example.mybooks.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


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

    private String getBookCover(JSONObject bookInfoItemJson) throws JSONException
    {
        Object bookCover = bookInfoItemJson.getJSONObject("volumeInfo").getJSONObject("imageLinks").get("thumbnail");
        return (String) bookCover;
    }




    private String getBookAuthor(JSONObject bookInfoItemJson) throws JSONException
    {
        JSONArray authors = bookInfoItemJson.getJSONObject("volumeInfo").getJSONArray("authors");

        List<String> authorList = new ArrayList<>();

        for(int i = 0; i< authors.length(); i++)
        {
            authorList.add(authors.getString(i));
        }

        StringBuilder authorsStringBuilder = new StringBuilder();

        for(int i = 0; i < authorList.size(); i++)
        {
            if(i != authorList.size() - 1)
            {
                authorsStringBuilder.append(authorList.get(i)).append(", ");
            }else
            {
                authorsStringBuilder.append(authorList.get(i)).append(" ");
            }
        }

        getBookCover(bookInfoItemJson);

        return authorsStringBuilder.toString();
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


    public String bookAuthors(String bookName)
    {
        try
        {
            JSONObject bookInfo = cleanBookApi(googleBookApi(bookName));
            return getBookAuthor(bookInfo);

        }catch(JSONException e)
        {
            return "";
        }
    }

    public String bookCover(String bookName)
    {
        try
        {
            JSONObject bookCover = cleanBookApi(googleBookApi(bookName));
            return getBookCover(bookCover);

        }catch(JSONException e)
        {
            return "";
        }
    }


}
