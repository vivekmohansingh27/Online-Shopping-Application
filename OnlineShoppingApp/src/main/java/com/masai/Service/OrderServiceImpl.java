package com.masai.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.CartException;
import com.masai.Exception.OrderException;
import com.masai.Exception.UserException;
import com.masai.Repository.CartRepository;
import com.masai.Repository.CustomerRepo;
import com.masai.Repository.OrderRepository;
import com.masai.Repository.UserSession;
import com.masai.model.Address;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.Product;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserSession userRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CartRepository cartRepo;

	@Override
	public Orders addOrder(Orders order,String key) throws OrderException, CartException {

		CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}
		
		Integer id = user.getUserId();
		
		Optional<Customer> cs = customerRepo.findById(id);
		
		Customer customer = cs.get();
		
		
		
		Address address = customer.getAddress();
		
		List<Product> productList = customer.getCart().getProduct();
		if(productList.isEmpty()) {
			throw new CartException("Product List is empty");
		}
		Orders orders  = new Orders();
		
		orders.setAddress(address);
		orders.setCustomer(customer);
		orders.setOrderDate(LocalDate.now());
		orders.setOrderStatus("confirmed");
		orders.setProduct(productList);
		
		return orderRepository.save(orders);
		
	}

	@Override
	public Orders updateOrder(Orders order,String key) throws OrderException {
		
		CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}
		
		
		Optional<Orders> or = orderRepository.findById(order.getOrderId());

		if (or.isPresent()) {

			orderRepository.save(order);

		} else {
			throw new OrderException("Order does not exist with this order id : " + order.getOrderId());
		}

		return order;
	}

	@Override
	public Orders removeOrder(Integer orderId,String key) throws OrderException {

		CurrentUserSession user = userRepo.findByUuid(key);
		
		if(user==null) {
			throw new UserException("Please Login first");
		}
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



		return null;
	}

}
