package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.BookDetail;
import com.example.entity.vo.response.BookDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookDetailMapper extends BaseMapper<BookDetail> {
    @Select("select * from db_book bk INNER JOIN db_book_detail bd on bk.id = bd.bid " +
            "INNER JOIN db_author au on bk.aid = au.id where bd.bid = #{bid}")
    List<BookDetailVO> getDetailByBid(@Param("bid") Integer bid);
}
