package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.RestBean;
import com.example.entity.dto.Book;
import com.example.entity.dto.Interact;
import com.example.entity.vo.request.AddCommentVO;
import com.example.entity.vo.request.BookRateVO;
import com.example.entity.vo.response.*;

import java.util.Date;
import java.util.List;

public interface BookService extends IService<Book> {
    List<NewBookVO>  getNewBook();
    List<NewBookVO> newListByPage(int pageNumber);
    List<HotBookVO> getHotBook();
    List<HotBookVO> hotListByPage(int pageNumber);
    List<BestSellerVO> getBestSeller();
    List<TopBookVO> getTopBook();
    List<BookTypeVO> getTopBookList();
    List<RelativeBookVO> getRelativeBookByAid(int aid);
    List<BookTypeVO> getListByTypeId(int id);
    List<BookTypeVO> getListBySearch(String search);
//    交互
    void interact(Interact interact, boolean state);
    BookInteractVO getInteract(int bid, int uid);
    List<CollectVO> getAllCollect(int uid);
    void deleteCollect(int bid, int uid);
//    评论
    String createComment(int uid, AddCommentVO vo);
    List<CommentVO> comments(int bid);
}
