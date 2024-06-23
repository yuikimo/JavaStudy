package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookTypeMapper extends BaseMapper<BookType> {
    @Select("SELECT name FROM db_book_type WHERE id = #{id}")
    String getTypeNameById(@Param("id") Integer id);
}
