package com.example.topfiveitunesbest.data;

import lombok.Data;

import java.util.List;

@Data
public class Pojos {
    private int resultCount;
    private List<Pojo> results;
}
