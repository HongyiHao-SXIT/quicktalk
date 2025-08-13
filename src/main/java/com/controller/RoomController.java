package com.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.common.api.R;
import com.common.utils.AuthUtil;
import com.entity.Room;
import com.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomMapper mapper;


    @RequestMapping("/self/list")
    @ResponseBody
    public R selfList(Room room) {
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Room::getAccount, AuthUtil.getUserAccount());
        List<Room> list = mapper.selectList(wrapper);
        return R.data(list);
    }

    @RequestMapping("/list")
    @ResponseBody
    public R list(Room room) {
        LambdaQueryWrapper<Room> wrapper = new LambdaQueryWrapper<>();
        List<Room> list = mapper.selectList(wrapper);
        return R.data(list);
    }

    @RequestMapping("/delete")
    public R userDelete(@RequestParam("id") Integer id) {
        mapper.deleteById(id);
        return R.success("操作成功");
    }

    @RequestMapping("/save")
    public R save(@RequestBody Room room) {
        room.setId(null);
        room.setAccount(AuthUtil.getUserAccount());
        mapper.insert(room);
        return R.success("操作成功");
    }
}

