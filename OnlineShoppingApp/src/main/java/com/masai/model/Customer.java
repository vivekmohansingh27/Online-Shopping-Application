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
import jakarta.validation.constraints.Email;
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
	@Size(min = 3, max = 15)
	@NotBlank
	private String firstName;
	@Size(min = 3, max = 15)
	@NotBlank
	private String lastName;

	@NotNull
	@Pattern(regexp = "[6789][0-9]{9}")
	@NotBlank
	private String mobileNumber;

	@NotBlank
	@Email
	private String email;

	@NotNull
	@NotBlank
	@Size(min = 5, max = 10, message = "password length should be between 5 to 10")
	private String password;

	@NotNull
	@JsonIgnore
	private boolean isActive = true;

	@OneToOne
	@JoinColumn(name = "AID")
	@JsonIgnore
	private Address address;

	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "CAID")
	private Cart cart;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private User user;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Orders> orders;

}
