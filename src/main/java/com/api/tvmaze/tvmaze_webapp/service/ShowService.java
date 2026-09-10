package com.api.tvmaze.tvmaze_webapp.service;

import com.api.tvmaze.tvmaze_webapp.exception.DataNotFoundException;
import com.api.tvmaze.tvmaze_webapp.model.request.Comments;
import com.api.tvmaze.tvmaze_webapp.model.request.CommentsRequest;
import com.api.tvmaze.tvmaze_webapp.model.response.TvMazeResponse;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import com.api.tvmaze.tvmaze_webapp.model.response.CommentResponse;
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

    /**
     * Se inyecta la dependencia desde el constructor
     * */
    public ShowService(TvMazeClient tvMazeClient, ShowRepository showRepository, CommentsRepository commentsRepository) {

        this.tvMazeClient = tvMazeClient;
        this.showRepository = showRepository;
        this.commentsRepository = commentsRepository;
    }

    /**
     * Esta metodo sirve para buscar los shows por criterio de busqueda.
     */
    public List<ShowResponse> searchShows(String query) {
        // Se obtienen los resultados desde el cliente Rest
        TvMazeResponse[] results = tvMazeClient.searchShows(query);
        // Se valida que no este vacio
        if (results == null || results.length == 0) {
            throw new DataNotFoundException("No se encontraron resultados");
        }

        // Se castea a la clase ShowResponse y se devuelve en lista cada resultado
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
                    // Se agregan los comentarios encontrados mediante la busqueda del id
                    response.setComments(getCommentsById(show.getId()));
                    return response;
                }).toList();
    }

    /**
     * Esta metodo busca el show por id en la BD,
     * si no la encuentra, va a la API y la realiza el insert
     */
    public TvMazeShow getShowById(Integer showId) {
        TvMazeShow show =  showRepository.findById(showId)
                .orElseGet(() -> {
                    TvMazeShow apiShow = tvMazeClient.getShowById(showId);
                    return showRepository.save(apiShow);
                });
        show.setComments(getCommentsById(show.getId()));
        return show;
    }

    /**
     * Esta metodo realiza la busqueda del show por id
     * si lo encuentra realiza el insert de los comentarios en la BD
     */
    public void saveComments(Integer showId, CommentsRequest request){
        // Se envuelve en optional para evitar nullpointer
        Optional<TvMazeShow> find = showRepository.findById(showId);

        if(find.isPresent()){
            // Se crea el objeto para guardarlo en la BD
            Comments review = new Comments(showId, request.getComment(), request.getRating());
            commentsRepository.save(review);
        }else{
            throw new DataNotFoundException("No se encontraron resultados");
        }

    }

    /**
     * Esta metodo realiza la busqueda de los comentarios por el id del show
     * y regresa una lista de comentarios, si no hay regresa una lista vacia
     */
    public List<CommentResponse> getCommentsById(Integer showId){
        List<Comments> comments = commentsRepository.findByShowId(showId);
        if (comments == null || comments.isEmpty()) {
            return List.of();
        }
        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getComment(),
                        comment.getRating()
                ))
                .toList();

    }
}