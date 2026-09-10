package com.api.tvmaze.tvmaze_webapp.model.request;

import lombok.Data;

@Data
public class Network {

    private Integer id;
    private String name;
    private Country country;
    private String officialSite;
}
