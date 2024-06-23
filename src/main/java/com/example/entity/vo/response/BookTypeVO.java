package com.example.entity.vo.response;

import lombok.Data;

@Data
public class BookTypeVO {
    Integer id;
    String name;
    String title;
    String author;
    String publisher;
    String profile;
    String pb_time;
    Double rate;
    String image;
}
