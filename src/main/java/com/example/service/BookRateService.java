package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.BookRate;
import com.example.entity.vo.response.RateVO;

import java.util.Date;

public interface BookRateService extends IService<BookRate> {
    void insertRate(int bid, int uid, double rate, Date time);
    RateVO checkRate(int bid, int uid);
}
