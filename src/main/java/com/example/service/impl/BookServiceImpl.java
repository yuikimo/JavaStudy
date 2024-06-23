package com.example.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RestBean;
import com.example.entity.dto.Account;
import com.example.entity.dto.Book;
import com.example.entity.dto.BookComment;
import com.example.entity.dto.Interact;
import com.example.entity.vo.request.AddCommentVO;
import com.example.entity.vo.request.BookRateVO;
import com.example.entity.vo.response.*;
import com.example.mapper.BookCommentMapper;
import com.example.mapper.BookMapper;
import com.example.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    StringRedisTemplate template;
    @Resource
    BookCommentMapper commentMapper;

    @Override
    public List<NewBookVO> getNewBook() {
        return bookMapper.getAllNewBook();
    }

    @Override
    public List<NewBookVO> newListByPage(int pageNumber) {
        Page<NewBookVO> page = Page.of(pageNumber, 4);
        return bookMapper.getAllNewBookPage(page);
    }

    @Override
    public List<HotBookVO> getHotBook() {
        return bookMapper.getAllHotBook();
    }

    @Override
    public List<HotBookVO> hotListByPage(int pageNumber) {
        Page<HotBookVO> page = Page.of(pageNumber, 6);
        return bookMapper.getAllHotBookPage(page);
    }

    @Override
    public List<BestSellerVO> getBestSeller() {
        return bookMapper.getAllBestSeller();
    }

    @Override
    public List<TopBookVO> getTopBook() {
        return bookMapper.getAllTopBook();
    }

    @Override
    public List<BookTypeVO> getTopBookList() {
        return bookMapper.getTopListBook();
    }

    @Override
    public List<RelativeBookVO> getRelativeBookByAid(int aid) {
        return this.query()
                .eq("aid", aid)
                .list().stream().map(dto -> {
                    RelativeBookVO vo = new RelativeBookVO();
                    BeanUtils.copyProperties(dto, vo);
                    return vo;
                }).
                toList();
    }

    @Override
    public List<BookTypeVO> getListByTypeId(int id) {
        return bookMapper.getListByTypeId(id);
    }

    @Override
    public BookInteractVO getInteract(int bid, int uid) {
        BookInteractVO vo = new BookInteractVO();
        BookInteractVO.Interact interact = new BookInteractVO.Interact(
                hasInteract(bid, uid, "like"),
                hasInteract(bid, uid, "collect")
        );
        vo.setInteract(interact);
        return vo;
    }

    @Override
    public void interact(Interact interact, boolean state) {
        String type = interact.getType();
        synchronized (type.intern()) {
            template.opsForHash().put(type, interact.toKey(), Boolean.toString(state));
            this.saveInteractSchedule(type);
        }
    }

    private boolean hasInteract(int tid, int uid, String type) {
        String key = tid + ":" + uid;
        if (template.opsForHash().hasKey(type, key))
            return Boolean.parseBoolean(template.opsForHash().entries(type).get(key).toString());
        if (type.equals("like")) {
            return bookMapper.checkLikeInteract(tid, uid);
        }
        if (type.equals("collect")) {
            return bookMapper.checkCollectInteract(tid, uid);
        }
        return false;
    }

    private final Map<String, Boolean> state = new HashMap<>();
    ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

    private void saveInteractSchedule(String type) {
        if (!state.getOrDefault(type, false)) {
            state.put(type, true);
            service.schedule(() -> {
                this.saveInteract(type);
                state.put(type, false);
            }, 5, TimeUnit.SECONDS);
        }
    }

    private void saveInteract(String type) {
        synchronized (type.intern()) {
            List<Interact> check = new LinkedList<>();
            List<Interact> uncheck = new LinkedList<>();
            template.opsForHash().entries(type).forEach((k, v) -> {
                if (Boolean.parseBoolean(v.toString()))
                    check.add(Interact.parseInteract(k.toString(), type));
                else
                    uncheck.add(Interact.parseInteract(k.toString(), type));
            });
            if (!check.isEmpty())
                bookMapper.addInteract(check, type);
            if (!uncheck.isEmpty())
                bookMapper.deleteInteract(uncheck, type);
            template.delete(type);
        }
    }

    @Override
    public List<CollectVO> getAllCollect(int uid) {
        return bookMapper.getAllCollect(uid);
    }

    @Override
    public void deleteCollect(int bid, int uid) {
        bookMapper.deleteCollect(bid, uid);
    }

    @Override
    public String createComment(int uid, AddCommentVO vo) {
        if (!textLimitCheck(JSONObject.parseObject(vo.getContent()), 500))
            return "评论内容太多，发表失败!";
        BookComment comment = new BookComment();
        comment.setUid(uid);
        BeanUtils.copyProperties(vo, comment);
        comment.setTime(new Date());
        commentMapper.insert(comment);
        return null;
    }

    private boolean textLimitCheck(JSONObject object, int max) {
        if (object == null) return false;
        long length = 0;
        for (Object op : object.getJSONArray("ops")) {
            length += JSONObject.from(op).getString("insert").length();
            if (length > max) return false;
        }
        return true;
    }

    @Override
    public List<CommentVO> comments(int bid) {
        List<BookComment> commentList = commentMapper.getAllComment(bid);
        return commentList.stream().map(dto -> {
            CommentVO vo = new CommentVO();
            BeanUtils.copyProperties(dto, vo);
            CommentVO.User user = new CommentVO.User();
            Account account = commentMapper.getAccountById(dto.getUid());
            user.setId(account.getId());
            user.setUsername(account.getUsername());
            user.setAvatar(account.getAvatar());
            vo.setUser(user);
            return vo;
        }).toList();
    }

    @Override
    public List<BookTypeVO> getListBySearch(String search) {
        return bookMapper.getListBySearch(search);
    }
}
