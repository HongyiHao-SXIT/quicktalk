package com.quicktalk.quicktalk.repository;

import com.quicktalk.quicktalk.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByRoomIdOrderByCreateTimeAsc(Integer roomId);
}
