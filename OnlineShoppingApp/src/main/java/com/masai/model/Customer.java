package com.masai.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	@Size(min = 3,max = 15)
	@NotBlank
	private String firstName;
	@Size(min = 3,max = 15)
	@NotBlank
	private String lastName;
	@Pattern(regexp = "[6789][0-9]{9}")
	@NotBlank
	private String mobileNumber;
	
	@NotNull
	@JsonIgnore
	private boolean isActive = true;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AID")
	@NotNull
	private	Address address;
	
	@OneToOne
	@JoinColumn(name = "CAID")
	private Cart cart;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	private User user;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "customer")
	private List<Orders> orders;
	
	
	
	
}
