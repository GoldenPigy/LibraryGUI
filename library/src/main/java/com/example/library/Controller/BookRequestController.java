package com.example.library.Controller;


import com.example.library.Service.BookRequestService;
import com.example.library.domain.Book;
import com.example.library.domain.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRequestController {
    @Autowired
    private final BookRequestService service;

    public BookRequestController(BookRequestService service) {
        this.service = service;
    }

    @GetMapping("/request")
    public String addRequest(@RequestParam("date")int date, @RequestParam("name")String name,
                           @RequestParam("writer")String writer,@RequestParam("explain") String explain){

        return service.addBookRequest(name, date, writer, explain);
    }
    @GetMapping("/requestList")
    public List<BookRequest> getRequestList(){
        return service.getRequestList();
    }


}
