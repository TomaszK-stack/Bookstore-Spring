package com.example.bookstore.crudservices;

import com.example.bookstore.entities.*;
import com.example.bookstore.repositories.BasketRepository;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrdersRepository ordersRepository;

    private Basket get_basket_from_sec_context(){
        User user = (User) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal();
        var basket = basketRepository.findByUser(user).get();
        return basket;

    }


    public List<Book> get_user_basket(){
        var basket = get_basket_from_sec_context();
        return basket.getBookList();


    }
    @Transactional
    public void add(int id){
        var book = bookRepository.findById(id).get();
        var basket = get_basket_from_sec_context();
        basket.add_to_basket(book);

    }

    @Transactional
    public void delete(int id){
        var book = bookRepository.findById(id).get();
        var basket = get_basket_from_sec_context();
        basket.delete_from_basket(book);


    }

    @Transactional
    public void submit_order(){
        User user = (User) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal();
        var basket = get_basket_from_sec_context();
        List<Book> new_book_list = new ArrayList<>(basket.getBookList());
        double totalPrice = new_book_list.stream()
                .mapToDouble(Book::getPrice)
                .sum();

        Orders order = Orders.builder()
                .user(user)
                .state(Order_State.SUBMITTED)
                .bookList(new_book_list)
                .price(totalPrice)
                .build();
        basket.delete_all_from_basket();
        ordersRepository.save(order);

    }




}
