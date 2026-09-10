package com.api.tvmaze.tvmaze_webapp.api;

import com.api.tvmaze.tvmaze_webapp.model.response.ShowResponse;
import com.api.tvmaze.tvmaze_webapp.service.ShowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
