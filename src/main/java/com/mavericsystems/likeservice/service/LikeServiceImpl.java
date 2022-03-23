package com.mavericsystems.likeservice.service;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.exception.LikeNotFoundException;
import com.mavericsystems.likeservice.feign.UserFeign;
import com.mavericsystems.likeservice.model.Like;
import com.mavericsystems.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mavericsystems.likeservice.constant.LikeConstant.DELETELIKE;


@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepo likeRepo;

    @Autowired
    UserFeign userFeign;
    @Override
    public List<Like> getLikes(String postOrCommentId) {
        return likeRepo.findByPcId(postOrCommentId);
    }

}
