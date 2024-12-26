package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Product")

public class ProductEntity {
	@Id
	@GeneratedValue
	private long id;
	private String productname;
	private String brand;
	private String madeIn;
	private double price;
	private int quantity;
	private double discountRate;
	private double taxPrice;
	private double discountPrice;
	private double offerPrice;
	private double stockValue;
	private double finalValue;

}
