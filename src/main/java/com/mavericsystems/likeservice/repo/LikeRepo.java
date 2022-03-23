package com.mavericsystems.likeservice.repo;

import com.mavericsystems.likeservice.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikeRepo extends MongoRepository<Like,String> {
    List<Like> findByPcId(String postOrCommentId);


    Like findByPcIdAndId(String postOrCommentId, String likeId);
}
