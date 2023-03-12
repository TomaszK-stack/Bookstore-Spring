package com.example.bookstore.paymentsserwice;

import com.example.bookstore.paymentsDTO.MoneyDTO;
import lombok.Data;

@Data
public class PurchaseUnit {
    private MoneyDTO amount;
}
