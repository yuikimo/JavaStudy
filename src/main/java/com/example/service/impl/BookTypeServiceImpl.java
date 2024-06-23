package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.BookType;
import com.example.mapper.BookTypeMapper;
import com.example.service.BookTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
    @Override
    public List<BookType> getBookType(int id) {
        return this.query()
                .eq("pid",id)
                .list();
    }
}
