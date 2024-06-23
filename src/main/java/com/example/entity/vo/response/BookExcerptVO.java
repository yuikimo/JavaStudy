package com.example.entity.vo.response;

import lombok.Data;

@Data
public class BookExcerptVO {
    Integer id;
    String excerpt;
    String author;
    String avatar;
    String time;
}
