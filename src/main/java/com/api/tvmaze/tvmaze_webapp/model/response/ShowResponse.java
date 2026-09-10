package com.api.tvmaze.tvmaze_webapp.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ShowResponse {

    private Integer id;
    private String name;
    private String channel;
    private String summary;
    private List<String> genres;
}
