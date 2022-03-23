package com.mavericsystems.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "Like")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @Id
    private String id;
    private String pcId;
    private String likedBy;
    private LocalDate localDate;


}
