package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.BookExcerpt;
import com.example.entity.vo.response.BookExcerptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookExcerptMapper extends BaseMapper<BookExcerpt> {
    @Select("select * from db_book_excerpt where bid = #{bid}")
    List<BookExcerptVO> getAllExcerptByBid(@Param("bid")int bid);
}
