package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.BookExcerpt;
import com.example.entity.vo.response.BookExcerptVO;

import java.util.List;

public interface BookExcerptService extends IService<BookExcerpt> {
    List<BookExcerptVO> getExcerptByBid(int bid);
}
