package com.api.tvmaze.tvmaze_webapp.model.request;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comments")
public class Comments {

    @Id
    private String  id;

    private Integer showId;

    private String comment;

    private Integer rating;

    public Comments(Integer showId, String comment, Integer rating) {
        this.showId = showId;
        this.comment = comment;
        this.rating = rating;
    }
}
