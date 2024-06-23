package com.example.entity.vo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BookDetailVO {
    Integer bid;
    String title;
    String author;
    String aid;
    String avatar;
    String image;
    String publisher;
    Integer page;
    String isbn;
    String excerpt;
    String quote;
    Double rate;
    String pb_time;
    String profile;
    String resume;
}
