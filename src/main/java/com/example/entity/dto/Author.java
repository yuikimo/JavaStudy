package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("db_author")
@AllArgsConstructor
public class Author {
    @TableId(type = IdType.AUTO)
    Integer id;
    String author;
    String avatar;
    Integer sexy;
    String birth;
    String address;
    String career;
    String resume;
}
