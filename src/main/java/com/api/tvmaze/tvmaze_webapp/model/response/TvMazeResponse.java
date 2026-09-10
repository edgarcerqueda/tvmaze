package com.api.tvmaze.tvmaze_webapp.model.response;

import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import lombok.Data;

@Data
public class TvMazeResponse {

    private TvMazeShow show;
}
