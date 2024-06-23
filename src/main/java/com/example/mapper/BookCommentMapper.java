package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.Account;
import com.example.entity.dto.BookComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookCommentMapper extends BaseMapper<BookComment> {
    @Select("select * from db_book_comment where bid = #{bid}")
    List<BookComment> getAllComment(@Param("bid") int bid);

    @Select("select * from db_account where id = #{id}")
    Account getAccountById(@Param("id") int id);
}
