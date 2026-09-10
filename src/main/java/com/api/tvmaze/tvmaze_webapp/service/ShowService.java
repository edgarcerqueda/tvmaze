package com.api.tvmaze.tvmaze_webapp.service;

import com.api.tvmaze.tvmaze_webapp.exception.DataNotFoundException;
import com.api.tvmaze.tvmaze_webapp.model.request.Comments;
import com.api.tvmaze.tvmaze_webapp.model.request.CommentsRequest;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeResponse;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import com.api.tvmaze.tvmaze_webapp.model.response.ShowResponse;
import com.api.tvmaze.tvmaze_webapp.repository.CommentsRepository;
import com.api.tvmaze.tvmaze_webapp.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class ShowService {

    private final TvMazeClient tvMazeClient;
    private final ShowRepository showRepository;
    private final CommentsRepository commentsRepository;

    /** Se inyecta la dependencia desde el constructor */
    public ShowService(TvMazeClient tvMazeClient, ShowRepository showRepository, CommentsRepository commentsRepository) {

        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
        this.commentsRepository = commentsRepository;
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

    public TvMazeShow getShowById(Integer showId) {
        /** Se realiza primero la busqueda por id a la BD,
         * si no la encuentra va a la API y la guarda en la BD */
        return showRepository.findById(showId)
                .orElseGet(() -> {
                    TvMazeShow show = tvMazeClient.getShowById(showId);
                    return showRepository.save(show);
                });
    }

    public void saveComments(Integer showId, CommentsRequest request){
        /** Se realiza la busqyeda por id y
         * se envuelve en optional para evitar nullpointer */
        Optional<TvMazeShow> find = showRepository.findById(showId);

        if(find.isPresent()){
            /** Se crea el objeto para guardarlo en la BD */
            Comments review = new Comments(showId, request.getComment(), request.getRating());
            commentsRepository.save(review);
        }else{
            throw new DataNotFoundException("No se encontro el id");
        }

    }
}