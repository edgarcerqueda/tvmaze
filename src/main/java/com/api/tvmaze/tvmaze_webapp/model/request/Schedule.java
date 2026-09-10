package com.api.tvmaze.tvmaze_webapp.model.request;

import lombok.Data;

import java.util.List;

@Data
public class Schedule {

    private String time;
    private List<String> days;

}
