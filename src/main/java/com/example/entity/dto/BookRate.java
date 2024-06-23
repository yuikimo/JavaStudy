package com.example.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@TableName("db_book_interact_rate")
@AllArgsConstructor
public class BookRate {
    @TableId
    Integer bid;
    Integer uid;
    Double rate;
    Date time;

    public String toKey() {
        return bid + ":" + uid;
    }

    public static Interact parseInteract(String str, String type){
        String[] keys = str.split(":");
        return new Interact(Integer.parseInt(keys[0]), Integer.parseInt(keys[1]), new Date(), type);
    }
}
