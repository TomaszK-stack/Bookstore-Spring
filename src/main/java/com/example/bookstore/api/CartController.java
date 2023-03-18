package com.example.bookstore.api;


import com.example.bookstore.crudservices.CartService;
import com.example.bookstore.requests.BookAddRequest;
import com.example.bookstore.requests.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/list")
    public ResponseEntity<CartResponse> list_of_books(){

        return ResponseEntity.ok(cartService.create_cart_response());
    }

    @PostMapping("/add")
    public void add_book_to_cart(@RequestBody BookAddRequest br){
        cartService.add(br);
    }

    @PostMapping("/delete")
    public void delete_book_from_cart(@RequestBody int id){
        cartService.delete(id);
    }


    @PostMapping("/submit")
    public void submit_order(){
        cartService.submit_order();
    }


}
