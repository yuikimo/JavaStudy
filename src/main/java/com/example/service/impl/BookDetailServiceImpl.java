package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.BookDetail;
import com.example.entity.vo.response.BookDetailVO;
import com.example.entity.vo.response.BookExcerptVO;
import com.example.mapper.BookDetailMapper;
import com.example.mapper.BookExcerptMapper;
import com.example.mapper.BookMapper;
import com.example.service.BookDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookDetailServiceImpl extends ServiceImpl<BookDetailMapper, BookDetail> implements BookDetailService {
    @Resource
    private BookDetailMapper bookDetailMapper;
    @Override
    public List<BookDetailVO> getDetailByBid(int bid) {
        return bookDetailMapper.getDetailByBid(bid);
    }
}
