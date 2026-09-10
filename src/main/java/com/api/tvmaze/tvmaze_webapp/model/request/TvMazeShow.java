package com.api.tvmaze.tvmaze_webapp.model.request;

import lombok.Data;

import java.util.List;

@Data
public class TvMazeShow {

    private Integer id;
    private String name;
    private Network network;
    private WebChannel webChannel;
    private String summary;
    private List<String> genres;
}
