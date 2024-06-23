package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.BookRate;
import com.example.entity.vo.response.RateVO;
import com.example.mapper.BookRateMapper;
import com.example.service.BookRateService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookRateServiceImpl extends ServiceImpl<BookRateMapper, BookRate> implements BookRateService {
    @Resource
    BookRateMapper rateMapper;
    @Override
    public void insertRate(int bid, int uid, double rate, Date time) {
        BookRate bookRate = new BookRate(bid, uid, rate, time);
        this.saveOrUpdate(bookRate);
    }

    @Override
    public RateVO checkRate(int bid, int uid) {
        return rateMapper.checkRate(bid, uid);
    }
}
