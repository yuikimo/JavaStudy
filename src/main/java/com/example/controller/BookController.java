package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.entity.RestBean;
import com.example.entity.dto.*;
import com.example.entity.vo.request.AddCommentVO;
import com.example.entity.vo.request.BookCollectVO;
import com.example.entity.vo.request.BookRateVO;
import com.example.entity.vo.response.*;
import com.example.service.*;
import com.example.utils.Const;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {
    @Resource
    private BookService bookService;
    @Resource
    private BookTypeService bookTypeService;
    @Resource
    private CampaignService campaignService;
    @Resource
    private BookExcerptService bookExcerptService;
    @Resource
    private BookDetailService bookDetailService;
    @Resource
    private AuthorService authorService;
    @Resource
    private BookRateService bookRateService;


    @GetMapping("/newBooks")
    public RestBean<List<NewBookVO>> getNewBooks() {
        return RestBean.success(bookService.getNewBook());
    }

    @GetMapping("/list-new")
    public RestBean<List<NewBookVO>> listNew(@RequestParam @Min(0) int page) {
        return RestBean.success(bookService.newListByPage(page + 1));
    }

    @GetMapping("/hotBooks")
    public RestBean<List<HotBookVO>> getHotBooks() {
        return RestBean.success(bookService.getHotBook());
    }

    @GetMapping("/list-hot")
    public RestBean<List<HotBookVO>> listHot(@RequestParam @Min(0) int page) {
        return RestBean.success(bookService.hotListByPage(page + 1));
    }

    @GetMapping("/bestSeller")
    public RestBean<List<BestSellerVO>> getBestSellers() {
        return RestBean.success(bookService.getBestSeller());
    }

    @GetMapping("/topBooks")
    public RestBean<List<TopBookVO>> getTopBooks() {
        return RestBean.success(bookService.getTopBook());
    }

    @GetMapping("/topBookList")
    public RestBean<List<BookTypeVO>> getTopBookList() {
        return RestBean.success(bookService.getTopBookList());
    }

    @GetMapping("/bookType")
    public RestBean<List<BookType>> getBookType(@RequestParam @Min(1) int id) {
        return RestBean.success(bookTypeService.getBookType(id));
    }

    @GetMapping("/list-campaign")
    public RestBean<List<Campaign>> getAllCampaign(@RequestParam @Min(0) int page) {
        return RestBean.success(campaignService.getAllCampaign(page + 1));
    }

    @GetMapping("/campaignTotal")
    public RestBean<Long> getTotalCampaign() {
        return RestBean.success(campaignService.getTotalCampaign());
    }

    @GetMapping("/detail")
    public RestBean<List<BookDetailVO>> getDetailByBid(@RequestParam @Min(1) int bid) {
        return RestBean.success(bookDetailService.getDetailByBid(bid));
    }

    @GetMapping("/excerpt")
    public RestBean<List<BookExcerptVO>> getQuoteByBid(@RequestParam @Min(1) int bid) {
        return RestBean.success(bookExcerptService.getExcerptByBid(bid));
    }

    @GetMapping("/author")
    public RestBean<List<Author>> getAuthorByAid(@RequestParam @Min(1) int aid) {
        return RestBean.success(authorService.getAuthorByAid(aid));
    }

    @GetMapping("/relative-book")
    public RestBean<List<RelativeBookVO>> getRelativeBookByAid(@RequestParam @Min(1) int aid) {
        return RestBean.success(bookService.getRelativeBookByAid(aid));
    }

    @GetMapping("/type-book")
    public RestBean<List<BookTypeVO>> getListByTypeId(@RequestParam int id) {
        return RestBean.success(bookService.getListByTypeId(id));
    }

    @GetMapping("/interact")
    public RestBean<Void> interact(@RequestParam @Min(1) int bid,
                                   @RequestParam @Pattern(regexp = "(collect|like)") String type,
                                   @RequestParam boolean state,
                                   @RequestAttribute(Const.ATTR_USER_ID) int id) {
        bookService.interact(new Interact(bid, id, new Date(), type), state);
        return RestBean.success();
    }

    @GetMapping("/check-interact")
    public RestBean<BookInteractVO> checkInteractById(@RequestParam int bid,
                                                      @RequestAttribute(Const.ATTR_USER_ID) int uid) {
        return RestBean.success(bookService.getInteract(bid, uid));
    }

    @PostMapping("/insert-rate")
    public RestBean<Void> insertRate(@Valid @RequestBody BookRateVO vo,
                           @RequestAttribute(Const.ATTR_USER_ID) int id){
        bookRateService.insertRate(vo.getBid(), id, vo.getRate(), new Date());
        return RestBean.success();
    }

    @GetMapping("/check-rate")
    public RestBean<RateVO> checkRateById(@RequestParam int bid,
                                              @RequestAttribute(Const.ATTR_USER_ID) int uid) {
        return RestBean.success(bookRateService.checkRate(bid, uid));
    }

    @GetMapping("/list-collect")
    public RestBean<List<CollectVO>> getAllCollect(@RequestAttribute(Const.ATTR_USER_ID) int uid){
        return RestBean.success(bookService.getAllCollect(uid));
    }

    @PostMapping("/cancel-collect")
    public RestBean<Void> cancelCollect(@Valid @RequestBody BookCollectVO vo,
                                        @RequestAttribute(Const.ATTR_USER_ID) int id){
        bookService.deleteCollect(vo.getBid(), id);
        return RestBean.success();
    }

    @PostMapping("/add-comment")
    public RestBean<Void> addComment(@Valid @RequestBody AddCommentVO vo,
                                     @RequestAttribute(Const.ATTR_USER_ID) int id){
        bookService.createComment(id, vo);
        return RestBean.success();
    }

    @GetMapping("/comments")
    public RestBean<List<CommentVO>> comments(@RequestParam @Min(1) int bid){
        return RestBean.success(bookService.comments(bid));
    }

    @GetMapping("/search")
    public RestBean<List<BookTypeVO>> searchBookList(@RequestParam(defaultValue = "") String search){
        if (!StringUtils.isBlank(search)) {
            return RestBean.success(bookService.getListBySearch(search));
        }
        return null;
    }
}
