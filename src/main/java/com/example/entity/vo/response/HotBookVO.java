package com.example.entity.vo.response;

import lombok.Data;

@Data
public class HotBookVO {
    Integer id;
    Integer aid;
    String title;
    String author;
    String avatar;
    Double rate;
    String image;
    String type;
}
