package com.abhi.electro.services.admin.adminOrder;

import java.util.List;

import com.abhi.electro.dto.AnalyticsResponse;
import com.abhi.electro.dto.OrderDto;

public interface AdminOrderService {
	
	List<OrderDto> getAllPlacedOrders();
	
	OrderDto changeOrderStatus(Long orderId, String status);
	
	AnalyticsResponse calculateAnalytics();
}
