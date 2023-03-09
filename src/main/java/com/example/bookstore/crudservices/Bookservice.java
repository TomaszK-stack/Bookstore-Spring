package com.example.bookstore.crudservices;

import com.example.bookstore.entities.Book;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.requests.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Bookservice {
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<String> add(BookRequest bookRequest){
          try {
              var book = Book.builder()
                      .title(bookRequest.getTitle())
                      .author(bookRequest.getAuthor())
                      .publicationyear(bookRequest.getPublicationDate())
                      .build();
              bookRepository.save(book);
          }catch (Exception e){
              return ResponseEntity.status(435).body("Invalid data");
          }
            return ResponseEntity.ok("Transaction succed");

    }
    public ResponseEntity<String> delete(int id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }


    }
