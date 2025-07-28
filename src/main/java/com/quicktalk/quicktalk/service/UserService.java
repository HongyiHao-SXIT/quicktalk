package com.quicktalk.quicktalk.service;

import com.quicktalk.quicktalk.common.exception.ServiceException;
import com.quicktalk.quicktalk.common.utils.AuthUtil;
import com.quicktalk.quicktalk.entity.User;
import com.quicktalk.quicktalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 判断账号是否存在
    public boolean accountExists(String account) {
        return userRepository.existsByAccount(account);
    }

    // 注册新用户
    public User register(User user) {
        if (accountExists(user.getAccount())) {
            throw new ServiceException("账号已存在");
        }
        user.setPassword(AuthUtil.codePassword(user.getPassword()));
        return userRepository.save(user);
    }

    // 用户登录
    public User login(String account, String rawPassword) {
        String encodedPassword = AuthUtil.codePassword(rawPassword);
        return userRepository.findByAccountAndPassword(account, encodedPassword);
    }

    // 更新用户信息
    public User update(User user) {
        return userRepository.save(user);
    }
}
