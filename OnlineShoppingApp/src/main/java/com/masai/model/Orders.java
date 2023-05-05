package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer orderId;
	private LocalDate orderDate;

	private String orderStatus;
	
	@ElementCollection
	@CollectionTable(name="order_product",joinColumns = @JoinColumn(name = "orderId"))
	private List<Product> product;
	
	
	
	@ManyToOne
	@JoinColumn(name = "CID")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name="AID")
	private Address address;

}
