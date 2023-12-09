package com.example.library.Service;

import com.example.library.Repository.BookRepository;
import com.example.library.domain.Book;
import com.example.library.domain.Member;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Transactional
@Service
public class BookService {


    @Autowired
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> searchAll(){

        return bookRepository.findAll();
    }
    public List<Book> search(Object name,Object writer,Object publisher){
        List<Book> list=searchAll();

        if(name!=null){
            list=findByBookName(list,name.toString());

        }
        if(writer!=null){
            list=findByWriter(list,writer.toString());

        }
        if(publisher!=null){
            list=findByPublisher(list,publisher.toString());

        }

        return list;
    }

    //키값별 검색
    public List<Book> findByBookName(List<Book> list, String name){
        return list.stream().filter(n->n.getName().contains(name)).toList();

    }
    public List<Book> findByWriter(List<Book> list, String writer){
        return list.stream().filter(n->n.getWriter().contains(writer)).toList();
    }
    public List<Book> findByPublisher(List<Book> list, String publisher){
        return list.stream().filter(n->n.getPublisher().contains(publisher)).toList();
    }
    public Book findById(int num){
        if(bookRepository.findById(num).isPresent()){
            return bookRepository.findById(num).get();
        }
        else
            return null;
    }

    public String bookBorrow(int num, Member member) {
        Book book= findById(num);
        if(findById(num)!=null){
            if(book.getBookNum()>book.getBorrowNum()){
                book.setBorrowNum(book.getBorrowNum()+1);
                book.setMember(member);
                return "borrow success";
            }
            else
                return "every book is borrowed";
        }
        return "no such book";
    }
    //대출
    public String bookReturn(int num) {
        Book book= findById(num);
        if(findById(num)!=null){
            if(book.getBorrowNum()>0){
                book.setBorrowNum(book.getBorrowNum()-1);
                book.setMember(null);
                return "return success";
            }
            else
                return "there is no borrowed book";
        }
        else return "no such book";

    }

    public List<Book> findByMemberId(String id) {
        return searchAll().stream().filter(n->n.getMember().getId().equals(id)).toList();
    }


    public List<Book> getBestList() {
        List<Book> list=bookRepository.findAll();
        List<Book> returnlist=new ArrayList<>();
        list= list.stream().sorted(Comparator.comparing(Book::getBorrowCount).reversed()).toList();

        for(int i=0;i<100;i++){
           returnlist.add(list.get(i));
        }
        return returnlist;
    }
}
