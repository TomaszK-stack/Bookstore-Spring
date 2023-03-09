package com.example.bookstore.crudservices;

import com.example.bookstore.entities.Orders;
import com.example.bookstore.repositories.OrdersRepository;
import com.example.bookstore.requests.OrderChangeStateRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    public List<Orders> get_list_of_orders(){
        return ordersRepository.findAll();

    }

    @Transactional
    public void change_order_state(OrderChangeStateRequest or){
        Orders order = ordersRepository.findById(or.getId()).get();
        order.setState(or.getOrderState());

    }


}
