package com.mavericsystems.likeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id;
    private String post;
    private String postedBy;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private int likesCount;
    private int commentsCount;

}
