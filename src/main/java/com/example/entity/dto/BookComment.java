package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("db_book_comment")
public class BookComment {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer bid;
    Integer uid;
    String content;
    Date time;
}
