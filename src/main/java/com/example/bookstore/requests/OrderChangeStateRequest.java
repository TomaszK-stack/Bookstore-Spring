package com.example.bookstore.requests;

import com.example.bookstore.entities.Order_State;
import lombok.Getter;

@Getter
public class OrderChangeStateRequest {

    private long id;

    private Order_State orderState;


}
