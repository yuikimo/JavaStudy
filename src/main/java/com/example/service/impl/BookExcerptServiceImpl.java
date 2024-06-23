package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.BookExcerpt;
import com.example.entity.vo.response.BookExcerptVO;
import com.example.mapper.BookExcerptMapper;
import com.example.service.BookExcerptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookExcerptServiceImpl extends ServiceImpl<BookExcerptMapper, BookExcerpt> implements BookExcerptService {
    @Resource
    private BookExcerptMapper bookExcerptMapper;
    @Override
    public List<BookExcerptVO> getExcerptByBid(int bid) {
        return bookExcerptMapper.getAllExcerptByBid(bid);
    }
}
