package com.example.library.Repository;

import com.example.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


}
