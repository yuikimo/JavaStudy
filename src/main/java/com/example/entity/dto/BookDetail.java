package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_book_detail")
@AllArgsConstructor
public class BookDetail {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer bid;
    String publisher;
    String pb_time;
    Integer page;
    String isbn;
    String quote;
}
