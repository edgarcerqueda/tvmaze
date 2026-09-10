package com.api.tvmaze.tvmaze_webapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {

    private String comment;
    private Integer rating;

}
