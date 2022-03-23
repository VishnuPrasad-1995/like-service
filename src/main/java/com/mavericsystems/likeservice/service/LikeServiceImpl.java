package com.mavericsystems.likeservice.service;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.exception.LikeNotFoundException;
import com.mavericsystems.likeservice.feign.UserFeign;
import com.mavericsystems.likeservice.model.Like;
import com.mavericsystems.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<Like> getLikes(String postOrCommentId, Integer page, Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        List<Like> Likes = likeRepo.findByPcId(postOrCommentId,PageRequest.of(page-1, pageSize));

        return Likes;
    }

    @Override
    public Like createLike(String postOrCommentId, LikeRequest likeRequest) {
        Like like = new Like();
        like.setPcId(likeRequest.getPostOrCommentId());
        like.setLikedBy(likeRequest.getLikedBy());
        like.setLocalDate(LocalDate.now());
        return likeRepo.save(like);
    }
    @Override
    public LikeDto getLikeDetails(String postOrCommentId, String likeId) {
        try {
            Like like = likeRepo.findByPcIdAndId(postOrCommentId, likeId);
            return new LikeDto(like.getId(), like.getPcId(), like.getLikedBy(), like.getLocalDate(), userFeign.getUserById(like.getLikedBy()));
        }
        catch (Exception e){
            throw new LikeNotFoundException("No like found : "+ likeId);
        }




    }
    @Override
    public Map<String,Like> removeLike(String postOrCommentId, String likeId) {
        Like like = likeRepo.findByPcIdAndId(postOrCommentId,likeId);
        likeRepo.deleteById(likeId);
        Map<String,Like> body = new HashMap<>();
        body.put(DELETELIKE,like);
        return body;
    }



    @Override
    public int getLikesCount(String postOrCommentId) {
        List<Like> likes = likeRepo.findByPcId(postOrCommentId);
        return likes.size();
    }


}
