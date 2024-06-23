package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.dto.Book;
import com.example.entity.dto.Interact;
import com.example.entity.vo.response.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("select * from db_author au INNER JOIN db_book bk on bk.aid = au.id where bk.news = 1")
    List<NewBookVO> getAllNewBook();

    @Select("select * from db_author au INNER JOIN db_book bk on bk.aid = au.id where bk.news = 1")
    List<NewBookVO> getAllNewBookPage(Page<NewBookVO> page);

    @Select("select * from db_book bk INNER JOIN db_author au on bk.aid = au.id where bk.hot = 1")
    List<HotBookVO> getAllHotBook();

    @Select("select * from db_book bk INNER JOIN db_author au on bk.aid = au.id where bk.hot = 1")
    List<HotBookVO> getAllHotBookPage(Page<HotBookVO> page);

    @Select("select * from db_book bk INNER JOIN db_author au on bk.aid = au.id where bk.bestseller = 1")
    List<BestSellerVO> getAllBestSeller();

    @Select("select * from db_book bk INNER JOIN db_author au on bk.aid = au.id where bk.top = 1")
    List<TopBookVO> getAllTopBook();

    @Select(" select * from db_book_type bt INNER JOIN db_book bk ON bt.name = bk.type" +
            " INNER JOIN db_book_detail bd ON bd.bid = bk.id" +
            " INNER JOIN db_author au ON au.id = bk.aid" +
            " where bk.top = 1 ORDER BY bk.rate DESC")
    List<BookTypeVO> getTopListBook();

    @Select(" select * from db_book bk INNER JOIN db_book_detail bd ON bd.bid = bk.id" +
            " INNER JOIN db_book_type bt ON bt.name = bk.type" +
            " INNER JOIN db_author au ON au.id = bk.aid" +
            " where bt.id = #{id}")
    List<BookTypeVO> getListByTypeId(@Param("id")int id);

    @Insert("""
            <script>
                insert ignore into db_book_interact_${type} values
                <foreach collection ="interacts" item="item" separator =",">
                    (#{item.bid}, #{item.uid}, #{item.time})
                </foreach>
            </script>
            """)
    void addInteract(List<Interact> interacts, String type);

    @Delete("""
            <script>
                delete from db_book_interact_${type} where
                <foreach collection="interacts" item="item" separator=" or ">
                    (bid = #{item.bid} and uid = #{item.uid})
                </foreach>
            </script>
            """)
    int deleteInteract(List<Interact> interacts, String type);

    @Select("select exists (select * from db_book_interact_like where bid = #{bid} and uid = #{uid})")
    Boolean checkLikeInteract(@Param("bid")int bid, @Param("uid")int uid);

    @Select("select exists (select * from db_book_interact_collect where bid = #{bid} and uid = #{uid})")
    Boolean checkCollectInteract(@Param("bid")int bid, @Param("uid")int uid);

    @Select("select * from db_book_interact_collect bc " +
            " INNER JOIN db_book bk on bc.bid = bk.id " +
            " Inner JOIN db_author au on bk.aid = au.id " +
            " Inner JOIN db_account ac on bc.uid = ac.id" +
            " where ac.id = #{uid}")
    List<CollectVO> getAllCollect(@Param("uid")int uid);

    @Delete("delete from db_book_interact_collect where bid = #{bid} and uid = #{uid}")
    void deleteCollect(@Param("bid") int bid, @Param("uid") int uid);

    @Select(" select * from db_book bk INNER JOIN db_book_detail bd ON bd.bid = bk.id" +
            " INNER JOIN db_book_type bt ON bt.name = bk.type" +
            " INNER JOIN db_author au ON au.id = bk.aid" +
            " where bk.title like CONCAT('%',#{search},'%') ")
    List<BookTypeVO> getListBySearch(@Param("search")String search);
}
