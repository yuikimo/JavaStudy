package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.dto.Author;
import com.example.entity.dto.Book;
import com.example.mapper.AuthorMapper;
import com.example.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author> implements AuthorService {
    @Override
    public List<Author> getAuthorByAid(int aid) {
        return this.query()
                .eq("id",aid)
                .list();
    }
}
