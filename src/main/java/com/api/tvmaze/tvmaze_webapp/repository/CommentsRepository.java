package com.api.tvmaze.tvmaze_webapp.repository;

import com.api.tvmaze.tvmaze_webapp.model.request.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<Comments, String> {
}
