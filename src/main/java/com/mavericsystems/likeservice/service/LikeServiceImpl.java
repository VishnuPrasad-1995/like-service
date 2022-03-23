package com.mavericsystems.likeservice.service;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.exception.LikeNotFoundException;
import com.mavericsystems.likeservice.feign.UserFeign;
import com.mavericsystems.likeservice.model.Like;
import com.mavericsystems.likeservice.repo.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;



@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepo likeRepo;

    @Autowired
    UserFeign userFeign;
    @Override
    public List<LikeDto> getLikes(String postOrCommentId, Integer page, Integer pageSize) {
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        List<Like> likes = likeRepo.findByPcId(postOrCommentId,PageRequest.of(page-1, pageSize));
        List<LikeDto> likeDtoList = new ArrayList<>();
        for (Like like : likes){
            likeDtoList.add(new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getLocalDate()));
        }

        return likeDtoList;
    }

    @Override
    public LikeDto createLike(String postOrCommentId, LikeRequest likeRequest) {
        Like like = new Like();
        like.setPcId(likeRequest.getPostOrCommentId());
        like.setLikedBy(likeRequest.getLikedBy());
        like.setLocalDate(LocalDate.now());
        likeRepo.save(like);
        return new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getLocalDate());

    }
    @Override
    public LikeDto getLikeDetails(String postOrCommentId, String likeId) {
        try {
            Like like = likeRepo.findByPcIdAndId(postOrCommentId, likeId);
            return new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getLocalDate());
        }
        catch (Exception e){
            throw new LikeNotFoundException("No like found : "+ likeId);
        }




    }
    @Override
    public LikeDto removeLike(String postOrCommentId, String likeId) {
        Like like = likeRepo.findByPcIdAndId(postOrCommentId,likeId);
        likeRepo.deleteById(likeId);
        return new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getLocalDate());

    }



    @Override
    public Integer getLikesCount(String postOrCommentId) {
        List<Like> likes = likeRepo.findByPcId(postOrCommentId);
        return likes.size();
    }


}
