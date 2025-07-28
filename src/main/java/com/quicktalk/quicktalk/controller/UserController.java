package com.quicktalk.quicktalk.controller;

import com.quicktalk.quicktalk.common.api.R;
import com.quicktalk.quicktalk.common.utils.AuthUtil;
import com.quicktalk.quicktalk.entity.User;
import com.quicktalk.quicktalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        userService.register(user);
        return R.success("注册成功");
    }

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        AuthUtil.clear();
        User exist = userService.login(user.getAccount(), user.getPassword());
        if (exist == null) {
            return R.fail("用户名或密码错误");
        }
        AuthUtil.setToken(exist);
        return R.success("登录成功");
    }

    @PostMapping("/update")
    public R update(@RequestBody User user) {
        User updated = userService.update(user);
        AuthUtil.setToken(updated);
        return R.success("");
    }
}
