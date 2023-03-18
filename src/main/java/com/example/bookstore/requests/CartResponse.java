package com.example.bookstore.requests;

import com.example.bookstore.entities.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartResponse  {

    public List<Book> book_list;

    public double total_price;

}
