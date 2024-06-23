package com.example.entity.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BookInteractVO {
    Interact interact;

    @Data
    @AllArgsConstructor
    public static class Interact {
        Boolean like;
        Boolean collect;
    }
}
