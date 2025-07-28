package com.quicktalk.quicktalk.controller;

import com.quicktalk.quicktalk.common.api.R;
import com.quicktalk.quicktalk.common.utils.AuthUtil;
import com.quicktalk.quicktalk.entity.Room;
import com.quicktalk.quicktalk.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/self/list")
    public R selfList() {
        String account = AuthUtil.getUserAccount();
        List<Room> list = roomRepository.findByAccount(account);
        return R.data(list);
    }

    @GetMapping("/list")
    public R list() {
        List<Room> list = roomRepository.findAll();
        return R.data(list);
    }

    @DeleteMapping("/delete")
    public R delete(@RequestParam("id") Integer id) {
        roomRepository.deleteById(id);
        return R.success("操作成功");
    }

    @PostMapping("/save")
    public R save(@RequestBody Room room) {
        room.setId(null);
        room.setAccount(AuthUtil.getUserAccount());
        roomRepository.save(room);
        return R.success("操作成功");
    }
}
