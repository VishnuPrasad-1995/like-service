package com.mavericsystems.likeservice.service;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.model.Like;

import java.util.List;
import java.util.Map;

public interface LikeService {
    List<Like> getLikes(String postOrCommentId);

}
