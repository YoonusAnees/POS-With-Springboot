package com.example.POS.Services;

import com.example.POS.DTO.CreateOrderRequest;
import com.example.POS.DTO.OrderItemDTO;
import com.example.POS.Model.Order;
import com.example.POS.Model.OrderItem;
import com.example.POS.Model.Product;
import com.example.POS.Repository.OrderRepository;
import com.example.POS.Repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;
@Service
public class OrderService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    public Order  createOrder(CreateOrderRequest orderRequest) {
        Order  order = new Order();
        order.setStatus("PENDING");
        double totalItemsAmount = 0;
        for (OrderItemDTO item: orderRequest.getOrderItem()) {

            OrderItem orderItem = new OrderItem();
            orderItem.setName(item.getName());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setImage(item.getImage());

            Product product = productRepository.findById(item.getProductId()).orElseThrow(()->new RuntimeException("Product Not Found"));
            orderItem.setProduct(product);
            totalItemsAmount = totalItemsAmount + item.getPrice()*item.getQuantity();

            order.getOrderItems().add(orderItem);



        }
        order.setTotalItemAmount(totalItemsAmount);
        double totalAmount = 0;
        double taxAmount = 10;
        totalAmount = totalItemsAmount + taxAmount;
        order.setTotalAmount(totalAmount);
        order.setTaxAmount(taxAmount);
        order.setReferenceNo(UUID.randomUUID().toString());
        return orderRepository.save(order);



    }

    public Order getOrder(String referenceId) {
       return orderRepository.findByReferenceNo(referenceId).orElseThrow(()->new RuntimeException("Order Not Found"));
    }
}
