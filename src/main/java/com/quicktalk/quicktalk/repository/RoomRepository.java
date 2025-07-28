package com.quicktalk.quicktalk.repository;

import com.quicktalk.quicktalk.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByAccount(String account);
}
