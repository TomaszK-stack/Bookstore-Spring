package com.example.bookstore.crudservices;

import com.example.bookstore.entities.*;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.OrdersRepository;
import com.example.bookstore.requests.BasketResponse;
import com.example.bookstore.requests.BookAddRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    OrdersRepository ordersRepository;

    private Cart get_basket_from_sec_context(){
        User user = (User) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal();
        var basket = cartRepository.findByUser(user).get();
        return basket;

    }


    public List<Book> get_user_basket(){
        var basket = get_basket_from_sec_context();
        return basket.getBookList();


    }
    @Transactional
    public void add(BookAddRequest br){
        var book = bookRepository.findById(br.getId()).get();
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
                .state(Order_State.PAID)
                .bookList(new_book_list)
                .price(totalPrice)
                .build();
        basket.delete_all_from_basket();
//        ordersRepository.save(order);

    }

    public BasketResponse create_basket_response(){
        var basket = get_basket_from_sec_context();
        var book_list = basket.getBookList();
        double total_price = book_list.stream()
                .mapToDouble(book -> book.getPrice()).sum();
        var basket_response =
                BasketResponse.builder().
                        book_list(book_list)
                        .total_price(total_price)
                        .build();
        return basket_response;

    }





}
