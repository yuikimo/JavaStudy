package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.BookDetail;
import com.example.entity.vo.response.BookDetailVO;

import java.util.List;

public interface BookDetailService extends IService<BookDetail> {
    List<BookDetailVO> getDetailByBid(int bid);
}
