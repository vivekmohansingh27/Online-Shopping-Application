package com.masai.Service;

import java.time.LocalDate;
import java.util.List;

import com.masai.Exception.OrderException;
import com.masai.model.Orders;

public interface OrderService {
	
	public Orders addOrder(Orders order,String key) throws OrderException;
	public Orders updateOrder(Orders order) throws OrderException;
	public Orders removeOrder(Integer orderId) throws OrderException;
	public Orders viewOrderById(Integer orderId) throws OrderException;
	public List<Orders> AllOrder() throws OrderException;
	public List<Orders> AllOrderByDate(LocalDate date) throws OrderException;
	public List<Orders> AllOrderByLocation(String location) throws OrderException;
	
}
