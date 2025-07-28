package com.quicktalk.quicktalk.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String account;
}

