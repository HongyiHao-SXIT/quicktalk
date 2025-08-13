package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.common.api.R;
import com.common.exception.ServiceException;
import com.common.utils.AuthUtil;
import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/loginOut")
    public R loginOut() {
        AuthUtil.clear();
        return R.success("操作成功");
    }

    @GetMapping("/current")
    public R current() {
        User user = AuthUtil.getUser();
        return R.data(user);
    }

    @PostMapping("/register")
    public R register(@RequestBody User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, user.getAccount());
        Long count = userMapper.selectCount(wrapper);

        if (count > 0) {
            throw new ServiceException("账号已存在");
        }

        user.setPassword(AuthUtil.codePassword(user.getPassword()));
        userMapper.insert(user);
        return R.success("注册成功");
    }

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        AuthUtil.clear();

        user.setPassword(AuthUtil.codePassword(user.getPassword()));

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, user.getAccount());
        wrapper.eq(User::getPassword, user.getPassword());
        User exist = userMapper.selectOne(wrapper);

        if (exist == null) {
            return R.fail("用户名或密码错误");
        }

        AuthUtil.setToken(exist);
        return R.success("登录成功");
    }

    @PostMapping("/update")
    public R update(@RequestBody User user) {
        userMapper.updateById(user);
        AuthUtil.setToken(user);
        return R.success("");
    }
}

