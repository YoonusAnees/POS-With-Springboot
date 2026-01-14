package com.example.POS.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CreateOrderRequest {
    public List<OrderItemDTO> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItemDTO> orderItem) {
		this.orderItem = orderItem;
	}

	private List<OrderItemDTO>  orderItem;

}
