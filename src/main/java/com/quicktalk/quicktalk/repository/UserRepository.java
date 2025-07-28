package com.quicktalk.quicktalk.repository;

import com.quicktalk.quicktalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByAccount(String account);

    User findByAccountAndPassword(String account, String password);

    User findByAccount(String account);
}
