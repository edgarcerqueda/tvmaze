package com.api.tvmaze.tvmaze_webapp.model.request;

import com.api.tvmaze.tvmaze_webapp.model.response.CommentResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "shows")
public class TvMazeShow {

    @Id
    private Integer id;
    private String url;
    private String name;
    private String type;
    private String language;
    private String status;
    private Integer runtime;
    private Integer averageRuntime;
    private String premiered;
    private String ended;
    private String officialSite;
    private Schedule schedule;
    private Rating rating;
    private Integer weight;
    private Network network;
    private WebChannel webChannel;
    private String dvdCountry;
    private Externals externals;
    private Image image;
    private String summary;
    private Integer updated;
    @JsonProperty("_links")
    private Enlaces enlaces;
    private List<String> genres;
    private List<CommentResponse> comments;
}
