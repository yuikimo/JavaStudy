package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.dto.Author;
import com.example.entity.dto.Book;

import java.util.List;

public interface AuthorService extends IService<Author> {
    List<Author> getAuthorByAid(int aid);
}
