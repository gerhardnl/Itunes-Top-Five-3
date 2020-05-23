package com.example.topfiveitunesbest.resource;


import com.example.topfiveitunesbest.data.DataFromApi;
import com.example.topfiveitunesbest.data.Pojo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/artist")
public class HelloResource {
    List<String> favoriteArtists = new ArrayList<>();

    @GetMapping("/top five")
    public List hello(String artist) throws IOException {
        if (favoriteArtists.contains(artist.toLowerCase())){
            return DataFromApi.toObject(artist);
        }else {
            return Collections.singletonList("this artist is not i your list of favorites");
        }
    }
    @GetMapping("/favorite")
    public List helloPost(String artist) throws IOException{
        if (DataFromApi.toObject(artist) != null) {
            if (!favoriteArtists.contains(artist)) {
                favoriteArtists.add(artist.toLowerCase());
            }

        }else {
            return Collections.singletonList("not valid search");
        }
        return favoriteArtists;
    }
    @GetMapping("/Delete")
    public List helloPut(String artist) throws IOException{
        if (favoriteArtists.contains(artist.toLowerCase())){
            favoriteArtists.remove(artist.toLowerCase());
            return favoriteArtists;
        }else {
            return Collections.singletonList("this artist is not i your list of favorites");
        }
    }
}
