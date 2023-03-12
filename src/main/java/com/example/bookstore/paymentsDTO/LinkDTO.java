package com.example.bookstore.paymentsDTO;

import lombok.Data;

@Data
public class LinkDTO {
    private String href;
    private String rel;
    private String method;
}
