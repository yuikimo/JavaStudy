package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.BookType;

import java.util.List;

public interface BookTypeService extends IService<BookType> {
    List<BookType> getBookType(int id);
}
