package com.mavericsystems.likeservice.service;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;

import java.util.List;


public interface LikeService {
    List<LikeDto> getLikes(String postOrCommentId, Integer page, Integer pageSize);

    LikeDto getLikeDetails(String postOrCommentId, String likeId);

    LikeDto createLike(String postOrCommentId, LikeRequest likeRequest);

    LikeDto removeLike(String postOrCommentId, String likeId);

    Integer getLikesCount(String postOrCommentId);

}
