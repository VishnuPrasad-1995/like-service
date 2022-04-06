package com.mavericsystems.likeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private String id;
    private String postOrCommentId;
    private UserDto likedBy;
    private LocalDate createdAt;
}
