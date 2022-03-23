package com.mavericsystems.likeservice.controller;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.model.Like;
import com.mavericsystems.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/postsOrComments")
public class LikeController {
    @Autowired
    LikeService likeService;

    @GetMapping("/{postOrCommentId}/likes")
    public ResponseEntity<List<Like>> getLikes(@PathVariable("postOrCommentId") String postOrCommentId, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize){
        return new ResponseEntity<>(likeService.getLikes(postOrCommentId,page,pageSize), HttpStatus.OK);
    }
    @PostMapping("/{postOrCommentId}/likes")
    public ResponseEntity<Like> createLike(@PathVariable("postOrCommentId") String postOrCommentId,@RequestBody LikeRequest likeRequest){
        return new ResponseEntity<>(likeService.createLike(postOrCommentId,likeRequest), HttpStatus.CREATED);
    }
    @GetMapping("/{postOrCommentId}/likes/{likeId}")
    public ResponseEntity<LikeDto> getLikeDetails(@PathVariable("postOrCommentId") String postOrCommentId, @PathVariable("likeId") String likeId){
        return new ResponseEntity<>(likeService.getLikeDetails(postOrCommentId,likeId), HttpStatus.OK);
    }
    @DeleteMapping("/{postOrCommentId}/likes/{likeId}")
    public ResponseEntity<Map<String,Like>> removeLike(@PathVariable("postOrCommentId") String postOrCommentId, @PathVariable("likeId") String likeId){
        return new ResponseEntity<>(likeService.removeLike(postOrCommentId,likeId), HttpStatus.OK);
    }
    @GetMapping("/{postOrCommentId}/likes/count")
    public ResponseEntity<Integer> getLikesCount(@PathVariable("postOrCommentId") String postOrCommentId){
        return new ResponseEntity<>(likeService.getLikesCount(postOrCommentId),HttpStatus.OK);
    }
}
