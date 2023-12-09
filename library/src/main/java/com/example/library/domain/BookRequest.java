package com.example.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int num=0;
    @Column(nullable = false)
    int date;
    @Column(nullable = false)
    String name;
    String writer;
    String explanation;
}
