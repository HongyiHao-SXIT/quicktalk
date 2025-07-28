package com.quicktalk.quicktalk.controller;

import com.quicktalk.quicktalk.common.api.R;
import com.quicktalk.quicktalk.common.utils.AuthUtil;
import com.quicktalk.quicktalk.entity.Comment;
import com.quicktalk.quicktalk.entity.User;
import com.quicktalk.quicktalk.repository.CommentRepository;
import com.quicktalk.quicktalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public R save(@RequestBody Comment comment) {
        comment.setAccount(AuthUtil.getUserAccount());
        comment.setCreateTime(new Date());
        commentRepository.save(comment);
        return R.success("");
    }

    @GetMapping("/list")
    public R list(Comment comment) {
        // 查询所有指定 roomId 的评论，按时间排序
        List<Comment> comments = commentRepository.findByRoomIdOrderByCreateTimeAsc(comment.getRoomId());

        // 设置用户头像
        for (Comment item : comments) {
            User user = userRepository.findByAccount(item.getAccount());
            if (user != null) {
                item.setIcon(user.getIcon());
            }
        }

        return R.data(comments);
    }
}
