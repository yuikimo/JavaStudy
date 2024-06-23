package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("db_book")
@AllArgsConstructor
public class Book {
    @TableId(type = IdType.AUTO)
    Integer id;
    String title;
    Integer aid;
    String image;
    Double rate;
    String type;
    String profile;
    Integer news;
    Integer bestseller;
    Integer top;
}
