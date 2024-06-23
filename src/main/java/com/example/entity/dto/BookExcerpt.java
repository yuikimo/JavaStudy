package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_book_excerpt")
@AllArgsConstructor
public class BookExcerpt {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer bid;
    String excerpt;
    String author;
    String avatar;
    String time;
}
