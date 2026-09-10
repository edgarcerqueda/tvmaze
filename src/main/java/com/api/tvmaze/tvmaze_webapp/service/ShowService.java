package com.api.tvmaze.tvmaze_webapp.service;

import com.api.tvmaze.tvmaze_webapp.exception.DataNotFoundException;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeResponse;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import com.api.tvmaze.tvmaze_webapp.model.response.ShowResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;

    /** Se inyecta la dependencia desde el constructor */
    public ShowService(TvMazeClient tvMazeClient) {
        this.tvMazeClient = tvMazeClient;
    }

    public List<ShowResponse> searchShows(String query) {
        /** Se obtienen los resultados desde el cliente Rest */
        TvMazeResponse[] results = tvMazeClient.searchShows(query);
        /** Se valida que no este vacio */
        if (results == null || results.length == 0) {
            throw new DataNotFoundException("No se encontraron resultados");
        }
        /** Se castea a la clase ShowResponse y se devuelve en lista cada resultado */
        return Arrays.stream(results)
                .map(result -> {
                    TvMazeShow show = result.getShow();
                    ShowResponse response = new ShowResponse();
                    response.setId(show.getId());
                    response.setName(show.getName());
                    response.setSummary(show.getSummary());
                    response.setGenres(show.getGenres());
                    if (show.getNetwork() != null) {
                        response.setChannel(show.getNetwork().getName());
                    } else if (show.getWebChannel() != null) {
                        response.setChannel(show.getWebChannel().getName());
                    } else {
                        response.setChannel(null);
                    }
                    return response;
                }).toList();
    }
}