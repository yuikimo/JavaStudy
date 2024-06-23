package com.example.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {
    int id;
    String content;
    User user;
    Date time;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        Integer id;
        String username;
        String avatar;
    }
}
