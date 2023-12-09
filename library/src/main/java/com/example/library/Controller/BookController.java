package com.example.library.Controller;

import com.example.library.Service.BookService;
import com.example.library.Service.MemberService;
import com.example.library.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private final BookService service;
    @Autowired
    private final MemberService memberService;


    public BookController(BookService service, MemberService memberService) {
        this.service = service;
        this.memberService = memberService;
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam("name")Object name, @RequestParam("writer")Object writer,
                             @RequestParam("publisher")Object publisher){

        return service.search(name, writer, publisher);
    }

    @GetMapping("/bookBorrow")
    public String bookBorrow(@RequestParam("num")int num,@RequestParam("member") String member){

       return service.bookBorrow(num, memberService.findById(member));
    }

    @GetMapping("/bookReturn")
    public String bookReturn(@RequestParam("num")int num){
        //멤버의 책 배열에서 찾고 있을 경우, 없을 경우
        return  service.bookReturn(num);
    }

    @GetMapping("/memberBookBorrowList")
    public List<Book> memberBookBorrowList(@RequestParam("id")String id){

        List<Book> bookList= new ArrayList<>();

        if(memberService.findById(id)==null){
            return bookList;
        }
        bookList= service.findByMemberId(id);

        return bookList;
    }
    @GetMapping("/bestBook")
    public List<Book> getBestBook(){

        return service.getBestList();
    }

}
