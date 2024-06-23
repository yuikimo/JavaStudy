package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@TableName("db_book_campaign")
@AllArgsConstructor
public class Campaign {
    Integer id;
    String name;
    String image;
    String type;
    String time;
}
