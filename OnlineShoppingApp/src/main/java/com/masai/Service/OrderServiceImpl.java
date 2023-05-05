package com.masai.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.OrderException;
import com.masai.Repository.OrderRepository;
import com.masai.model.Orders;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Orders addOrder(Orders order) throws OrderException {

		Optional<Orders> or = orderRepository.findById(order.getOrderId());

		if (or.isPresent()) {
			throw new OrderException("Order already present with this order id : " + order.getOrderId());
		}

		return orderRepository.save(order);
	}

	@Override
	public Orders updateOrder(Orders order) throws OrderException {
		Optional<Orders> or = orderRepository.findById(order.getOrderId());

		if (or.isPresent()) {

			orderRepository.save(order);

		} else {
			throw new OrderException("Order does not exist with this order id : " + order.getOrderId());
		}

		return order;
	}

	@Override
	public Orders removeOrder(Integer orderId) throws OrderException {

		Orders or = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order does not exist with id : " + orderId));

		orderRepository.delete(or);
		return or;
	}

	@Override
	public Orders viewOrderById(Integer orderId) throws OrderException {
		Orders or = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderException("Order does not exist with id : " + orderId));

		return or;
	}

	@Override
	public List<Orders> AllOrder() throws OrderException {

		List<Orders> allOrders = orderRepository.findAll();

		if (allOrders.isEmpty()) {
			throw new OrderException("There is No Order in Database");
		}

		return allOrders;
	}

	@Override
	public List<Orders> AllOrderByDate(LocalDate date) throws OrderException {

		List<Orders> allOrderdate = orderRepository.findByOrderDate(date);

		if (allOrderdate.isEmpty()) {
			throw new OrderException("There is No Order On this date : " + date);
		}

		return allOrderdate;
	}

	@Override
	public List<Orders> AllOrderByLocation(String location) throws OrderException {

//		List<Orders> allOrderLocation= orderRepository.findByOrderByCity(location);
//		
//		if(allOrderLocation.isEmpty()) {
//			throw new OrderException("There is No Order on this location : "+ location);
//		}

		return null;
	}

}
