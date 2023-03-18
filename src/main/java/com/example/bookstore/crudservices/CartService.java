package com.example.bookstore.crudservices;

import com.example.bookstore.entities.*;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.OrdersRepository;
import com.example.bookstore.requests.BookAddRequest;
import com.example.bookstore.requests.CartResponse;
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

    private Cart get_cart_from_sec_context(){
        User user = (User) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal();
        var cart = cartRepository.findByUser(user).get();
        return cart;

    }


    public List<Book> get_user_cart(){
        var cart = get_cart_from_sec_context();
        return cart.getBookList();


    }
    @Transactional
    public void add(BookAddRequest br){
        var book = bookRepository.findById(br.getId()).get();
        var cart = get_cart_from_sec_context();
        cart.add_to_cart(book);

    }

    @Transactional
    public void delete(int id){
        var book = bookRepository.findById(id).get();
        var cart = get_cart_from_sec_context();
        cart.delete_from_cart(book);


    }

    @Transactional
    public void submit_order(){
        User user = (User) SecurityContextHolder.
                getContext().
                getAuthentication().
                getPrincipal();
        var cart = get_cart_from_sec_context();

        List<Book> new_book_list = new ArrayList<>(cart.getBookList());

        double totalPrice = new_book_list.stream()
                .mapToDouble(Book::getPrice)
                .sum();

        Orders order = Orders.builder()
                .user(user)
                .state(Order_State.PAID)
                .bookList(new_book_list)
                .price(totalPrice)
                .build();
        cart.delete_all_from_cart();
        ordersRepository.save(order);

    }

    public CartResponse create_cart_response(){
        var cart = get_cart_from_sec_context();
        var book_list = cart.getBookList();
        double total_price = book_list.stream()
                .mapToDouble(book -> book.getPrice()).sum();
        var cart_response =
                CartResponse.builder().
                        book_list(book_list)
                        .total_price(total_price)
                        .build();
        return cart_response;

    }





}
