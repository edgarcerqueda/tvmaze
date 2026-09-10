package com.api.tvmaze.tvmaze_webapp.repository;

import com.api.tvmaze.tvmaze_webapp.model.request.TvMazeShow;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ShowRepository extends MongoRepository<TvMazeShow, Integer> {

}
