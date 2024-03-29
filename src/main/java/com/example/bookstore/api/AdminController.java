package com.example.bookstore.api;

import com.example.bookstore.crudservices.Bookservice;
import com.example.bookstore.crudservices.OrdersService;
import com.example.bookstore.entities.Orders;
import com.example.bookstore.requests.BookRequest;
import com.example.bookstore.requests.OrderChangeStateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    Bookservice bookservice;

    @Autowired
    OrdersService ordersService;

    @PostMapping("/book")
    public ResponseEntity<String> add_book(@RequestBody BookRequest bookRequest,@RequestParam("file") MultipartFile photo ){

        return bookservice.add(bookRequest);

    }
    @DeleteMapping("/book")
    public ResponseEntity<String> delete_book(@RequestBody int id){

        return bookservice.delete(id);

    }

    @PostMapping("book/cover-upload/{book-id}")
    public ResponseEntity<String> upload_cover(@PathVariable("book-id") int id, @RequestParam("file") MultipartFile file) throws IOException {
        bookservice.upload_cover(id, file);
        return ResponseEntity.ok("Pomyślnie");
    }


    @GetMapping("/orders/list")
    public List<Orders> get_list_of_orders(){
        return ordersService.get_list_of_orders();
    }

    @PostMapping("/orders/state/change")
    public void change_order_state(@RequestBody OrderChangeStateRequest or){
        ordersService.change_order_state(or);
        }




}
