package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.dto.BookRate;
import com.example.entity.vo.response.RateVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookRateMapper extends BaseMapper<BookRate> {
    @Select("select * from db_book_interact_rate where bid = #{bid} and uid = #{uid}")
    RateVO checkRate(@Param("bid")int bid,
                     @Param("uid")int uid);
}
