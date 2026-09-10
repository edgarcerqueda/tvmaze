package com.api.tvmaze.tvmaze_webapp.api;

import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import com.api.tvmaze.tvmaze_webapp.model.response.ShowResponse;
import com.api.tvmaze.tvmaze_webapp.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class TvMazeController {

    private final ShowService showService;

    public TvMazeController(ShowService showService) {
        this.showService = showService;
    }
    /** API para obtener la busqueda a partir de un criterio */
    @GetMapping("/search")
    public List<ShowResponse> search(@RequestParam("search_query") String searchQuery) {
        /** Se valida que el criterio de busqueda no este vacio */
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            throw new IllegalArgumentException("El criterio de busqueda no puede ser vacio");
        }
        return showService.searchShows(searchQuery);
    }

    @GetMapping("/{show_id}")
    public TvMazeShow getShowById(@PathVariable("show_id") Integer showId) {
        /** Se valida que el id de busqueda no este vacio o menor a cero */
        if (showId == null || showId <= 0) {
            throw new IllegalArgumentException("El id no puede ser nulo o menor a cero");
        }

        return showService.getShowById(showId);
    }

}
