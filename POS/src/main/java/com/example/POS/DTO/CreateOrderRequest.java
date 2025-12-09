package com.example.POS.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CreateOrderRequest {
    private List<OrderItemDTO>  orderItem;

}
