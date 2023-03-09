package com.example.bookstore.api;


import com.example.bookstore.crudservices.BasketService;
import com.example.bookstore.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;

    @GetMapping("/list")
    public List<Book> list_of_books(){

        return basketService.get_user_basket();
    }

    @PostMapping("/add")
    public void add_book_to_basket(@RequestBody int id){
        basketService.add(id);
    }

    @PostMapping("/delete")
    public void delete_book_from_basket(@RequestBody int id){
        basketService.delete(id);
    }


    @PostMapping("/submit")
    public void submit_order(){
        basketService.submit_order();
    }


}
