package com.example.library.Service;

import com.example.library.Repository.BookRequestRepository;
import com.example.library.domain.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BookRequestService {

    @Autowired
    private final BookRequestRepository bookRequestRepository;

    public BookRequestService(BookRequestRepository bookRequestRepository){
        this.bookRequestRepository=bookRequestRepository;
    }

    public String addBookRequest(String name, int date,String writer,String explain){
        BookRequest request=new BookRequest();
        request.setDate(date);
        request.setName(name);
        request.setExplanation(explain);
        request.setWriter(writer);

        bookRequestRepository.save(request);
        return "success";
    }
    public List<BookRequest> getRequestList(){
        return bookRequestRepository.findAll();
    }
    public void deleteRequest(String id){
        bookRequestRepository.deleteById(id);
    }
}
