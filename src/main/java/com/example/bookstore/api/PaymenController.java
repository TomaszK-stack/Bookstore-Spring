package com.example.bookstore.api;

import com.example.bookstore.crudservices.CartService;
import com.example.bookstore.entities.PaypalOrder;
import com.example.bookstore.paymentsDTO.*;
import com.example.bookstore.paymentsserwice.*;
import com.example.bookstore.repositories.PaypalOrderRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/checkout")
public class PaymenController {
    private final PayPalHttpClient payPalHttpClient;
    private final PaypalOrderRepository orderDAO;

    private final CartService cartService;



    @PostMapping
    public ResponseEntity<OrderResponseDTO> checkout(@RequestBody OrderDTO orderDTO) throws Exception {

        var appContext = new PayPalAppContextDTO();
        appContext.setReturnUrl("http://localhost:8080/api/v1/checkout/success");
        appContext.setBrandName("Bookstore");
        appContext.setLandingPage(PaymentLandingPage.BILLING);
        orderDTO.setApplicationContext(appContext);

        var orderResponse = payPalHttpClient.createOrder(orderDTO);


        var entity = new PaypalOrder();
        try {
            entity.setPaypalOrderId(orderResponse.getId());
            entity.setPaypalOrderStatus(orderResponse.getStatus().toString());
            var out = orderDAO.save(entity);

        }catch(NullPointerException n){
            return ResponseEntity.ok(orderResponse);


        }
        cartService.submit_order();
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(value = "/success")
    public ResponseEntity<String> paymentSuccess(HttpServletRequest request) {


        var orderId = request.getParameter("token");
        var out = orderDAO.findByPaypalOrderId(orderId);
        out.setPaypalOrderStatus(OrderStatus.APPROVED.toString());
        orderDAO.save(out);
        return ResponseEntity.ok("Payment Succeed");
    }



}
