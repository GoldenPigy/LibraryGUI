package com.example.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    private int num;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String writer;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false)
    private int publishYear;
    @Column(nullable = false)
    private int bookNum;
    private int borrowNum;
    private int borrowCount;
    private int registerDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;
}
