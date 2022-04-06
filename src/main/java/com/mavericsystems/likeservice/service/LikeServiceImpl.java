package com.mavericsystems.likeservice.service;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.exception.CustomFeignException;
import com.mavericsystems.likeservice.exception.LikeNotFoundException;
import com.mavericsystems.likeservice.feign.UserFeign;
import com.mavericsystems.likeservice.model.Like;
import com.mavericsystems.likeservice.repo.LikeRepo;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.mavericsystems.likeservice.constant.LikeConstant.*;


@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepo likeRepo;

    @Autowired
    UserFeign userFeign;
    @Override
    public List<LikeDto> getLikes(String postOrCommentId, Integer page, Integer pageSize) {
        try{
            if(page==null){
                page=1;
            }
            if(pageSize==null){
                pageSize=10;
            }
            List<Like> likes = likeRepo.findByPcId(postOrCommentId,PageRequest.of(page-1, pageSize));
            List<LikeDto> likeDtoList = new ArrayList<>();
            for (Like like : likes){
                likeDtoList.add(new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getCreatedAt()));
            }
            if(likeDtoList.isEmpty()){
                throw new LikeNotFoundException(LIKENOTFOUNDFORPOSTORCOMMENT + postOrCommentId);
            }
            return likeDtoList;
        }
        catch (FeignException | HystrixRuntimeException e){
            throw new CustomFeignException(FEIGNEXCEPTON);
        }
    }

    @Override
    public LikeDto createLike(String postOrCommentId, LikeRequest likeRequest) {
        try{
            Like like = new Like();
            like.setPcId(likeRequest.getPostOrCommentId());
            like.setLikedBy(likeRequest.getLikedBy());
            like.setCreatedAt(LocalDate.now());
            likeRepo.save(like);
            return new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getCreatedAt());
        }
        catch (FeignException | HystrixRuntimeException e){
            throw new CustomFeignException(FEIGNEXCEPTON);
        }

    }

    @Override
    public LikeDto getLikeDetails(String postOrCommentId, String likeId) {
        try {
            Like like = likeRepo.findByPcIdAndId(postOrCommentId, likeId);
            if(like == null){
                throw new LikeNotFoundException(LIKENOTFOUNDFORPOSTORCOMMENT + postOrCommentId + LIKEID + likeId);
            }
            return new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getCreatedAt());
        }
        catch (FeignException | HystrixRuntimeException e){
            throw new CustomFeignException(FEIGNEXCEPTON);
        }
    }

    @Override
    public LikeDto removeLike(String postOrCommentId, String likeId) {
        try{
            Like like = likeRepo.findByPcIdAndId(postOrCommentId,likeId);
            if(like == null){
                throw new LikeNotFoundException(LIKENOTFOUNDFORPOSTORCOMMENT + postOrCommentId + LIKEID + likeId);
            }
            likeRepo.deleteById(likeId);
            return new LikeDto(like.getId(), like.getPcId(), userFeign.getUserById(like.getLikedBy()), like.getCreatedAt());
        }
        catch (FeignException | HystrixRuntimeException e){
            throw new CustomFeignException(FEIGNEXCEPTON);
        }
    }

    @Override
    public Integer getLikesCount(String postOrCommentId) {
        try{
            List<Like> likes = likeRepo.findByPcId(postOrCommentId);
            return likes.size();
        }
        catch (Exception e){
            throw new LikeNotFoundException(LIKENOTFOUNDFORPOSTORCOMMENT+ postOrCommentId);
        }
    }
}
