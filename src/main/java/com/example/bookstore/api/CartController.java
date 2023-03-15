package com.example.bookstore.api;


import com.example.bookstore.crudservices.CartService;
import com.example.bookstore.requests.BasketResponse;
import com.example.bookstore.requests.BookAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public ResponseEntity<BasketResponse> list_of_books(){

        return ResponseEntity.ok(cartService.create_basket_response());
    }

    @PostMapping("/add")
    public void add_book_to_basket(@RequestBody BookAddRequest br){
        cartService.add(br);
    }

    @PostMapping("/delete")
    public void delete_book_from_basket(@RequestBody int id){
        cartService.delete(id);
    }


    @PostMapping("/submit")
    public void submit_order(){
        cartService.submit_order();
    }


}
