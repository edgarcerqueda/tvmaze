package com.api.tvmaze.tvmaze_webapp.service;

import com.api.tvmaze.tvmaze_webapp.exception.DataNotFoundException;
import com.api.tvmaze.tvmaze_webapp.model.response.TvMazeResponse;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class TvMazeClient {

    private final RestClient restClient;

    /**
     * Se crea la instancia de restclienty se inyecta en el constructor
     * */
    public TvMazeClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://api.tvmaze.com").build();
    }


    /**
     * Este metodo obtiene los datos del servicio API search
     * de todos los shows encontrados con el criterio de busqueda
     * y devuelve un arreglo de objetos de tipo TvMazeResponse
     */
    public TvMazeResponse[] searchShows(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/shows")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(TvMazeResponse[].class);
    }

    /**
     * Este metodo recupera los datos del servicio API shows
     * y devuelve un objetos de tipo TvMazeShow
     */
    public TvMazeShow getShowById(Integer showId) {
        try {
            // Seobtienen los datos necesidad de castear ya que se definen las variables con mismo nombre
            return restClient.get()
                    .uri("/shows/{showId}", showId)
                    .retrieve()
                    .body(TvMazeShow.class);

        }
        // Se valida desde el cliente si no encontro resultados
        catch (HttpClientErrorException.NotFound ex) {
            throw new DataNotFoundException("El id " + showId + " no se encontro");
        }
    }
}
