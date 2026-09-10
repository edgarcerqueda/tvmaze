package com.api.tvmaze.tvmaze_webapp.service;

import com.api.tvmaze.tvmaze_webapp.exception.DataNotFoundException;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeResponse;
import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class TvMazeClient {

    private final RestClient restClient;

    public TvMazeClient(RestClient.Builder builder) {
        /** Se crea la instancia de restclienty se inyecta en el constructor */
        this.restClient = builder.baseUrl("http://api.tvmaze.com").build();
    }

    public TvMazeResponse[] searchShows(String query) {
        /** Utilizamos GET para recuperar los datos del servicio API
         *  y se castea a la clase de respuesta TvMazeResponse */
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/shows")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(TvMazeResponse[].class);
    }

    public TvMazeShow getShowById(Integer showId) {
        try {
            /** Utilizamos GET para recuperar los datos del servicio API
             *  sin necesidad de castear ya que se definen variables con mismo nombre */
            return restClient.get()
                    .uri("/shows/{showId}", showId)
                    .retrieve()
                    .body(TvMazeShow.class);

        }
        /** Se valida desde el cliente si no encontro resultados */
        catch (HttpClientErrorException.NotFound ex) {
            throw new DataNotFoundException("El id " + showId + " no se encontro");
        }
    }
}
