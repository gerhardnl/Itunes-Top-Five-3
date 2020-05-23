package com.example.topfiveitunesbest.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFromApi {

    public static HttpURLConnection connect(String artist) throws IOException {
        URL url = new URL("https://itunes.apple.com/search?entity=album&term=" + artist + "&limit=5");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }
    public static String getString(HttpURLConnection connection){
        BufferedReader in;
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = in.readLine()) != null){
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static List toObject(String artist) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Pojos pojos = objectMapper.readValue(getString(connect(artist)), Pojos.class);
        if (pojos.getResults().get(0).getArtistName() != null) {
            for (int i = 0; i < pojos.getResults().size(); i++) {
                list.add(pojos.getResults().get(i).getCollectionName());
            }
        }else {
            return null;
        }
        return list;
    }

}
