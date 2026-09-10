package com.api.tvmaze.tvmaze_webapp.repository;

import com.api.tvmaze.tvmaze_webapp.model.request.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends MongoRepository<Comments, String> {

    List<Comments> findByShowId(Integer showId);
}
