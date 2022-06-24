package com.huyvv20.springmongodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Book")
public class Book {

    @Id
    private String _id;
    @TextIndexed
    private String bookName;
    @TextIndexed
    private String author;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date publishDate;
    private String description;
}
