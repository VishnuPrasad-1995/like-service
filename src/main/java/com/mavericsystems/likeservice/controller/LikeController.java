package com.mavericsystems.likeservice.controller;

import com.mavericsystems.likeservice.dto.LikeDto;
import com.mavericsystems.likeservice.dto.LikeRequest;
import com.mavericsystems.likeservice.exception.PostIdMismatchException;
import com.mavericsystems.likeservice.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;

import static com.mavericsystems.likeservice.constant.LikeConstant.POST_ID_MISMATCH;


@RestController
@RequestMapping("/postsOrComments")
public class LikeController {
    @Autowired
    LikeService likeService;

    @GetMapping("/{postOrCommentId}/likes")
    public ResponseEntity<List<LikeDto>> getLikes(@PathVariable("postOrCommentId") String postOrCommentId, @QueryParam("page") Integer page, @QueryParam("pageSize") Integer pageSize) {
        return new ResponseEntity<>(likeService.getLikes(postOrCommentId, page, pageSize), HttpStatus.OK);
    }

    @PostMapping("/{postOrCommentId}/likes")
    public ResponseEntity<LikeDto> createLike(@PathVariable("postOrCommentId") String postOrCommentId, @RequestBody LikeRequest likeRequest) {
        if (postOrCommentId.equals(likeRequest.getPostOrCommentId()))
            return new ResponseEntity<>(likeService.createLike(postOrCommentId, likeRequest), HttpStatus.CREATED);
        else
            throw new PostIdMismatchException(POST_ID_MISMATCH);
    }

    @GetMapping("/{postOrCommentId}/likes/{likeId}")
    public ResponseEntity<LikeDto> getLikeDetails(@PathVariable("postOrCommentId") String postOrCommentId, @PathVariable("likeId") String likeId) {
        return new ResponseEntity<>(likeService.getLikeDetails(postOrCommentId, likeId), HttpStatus.OK);
    }

    @DeleteMapping("/{postOrCommentId}/likes/{likeId}")
    public ResponseEntity<LikeDto> removeLike(@PathVariable("postOrCommentId") String postOrCommentId, @PathVariable("likeId") String likeId) {
        return new ResponseEntity<>(likeService.removeLike(postOrCommentId, likeId), HttpStatus.OK);
    }

    @GetMapping("/{postOrCommentId}/likes/count")
    public Integer getLikesCount(@PathVariable("postOrCommentId") String postOrCommentId) {
        return likeService.getLikesCount(postOrCommentId);
    }
}
