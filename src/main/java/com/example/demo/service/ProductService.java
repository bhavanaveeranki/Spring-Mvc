package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;



@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public void saveProducDetails(ProductModel productModel) 
	{
		double stockValue;
		stockValue = productModel.getPrice()*productModel.getQuantity();
		
		double discountPrice;
		discountPrice = productModel.getPrice()*productModel.getDiscountrate()/100;
		
		double offerPrice;
		offerPrice = productModel.getPrice()-discountPrice;
		
		double taxPrice;
		taxPrice = productModel.getPrice()*0.18;
		
		double finalPrice;
		finalPrice = offerPrice+taxPrice;
		
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductname(productModel.getProductname());
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadeIn(productModel.getMadeIn());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setDiscountRate(productModel.getDiscountrate());
		productEntity.setStockValue(stockValue);
		productEntity.setDiscountPrice(discountPrice);
		productEntity.setOfferPrice(offerPrice);
		productEntity.setTaxPrice(taxPrice);
		productEntity.setFinalValue(finalPrice);
		
		productRepository.save(productEntity);
	}
		
		public List<ProductEntity> getAllProducts()
		{
			List<ProductEntity>products = productRepository.findAll();
			return products;
		}
		public ProductEntity searchById(Long id)
		{
			Optional<ProductEntity> optionalData = productRepository.findById(id);
			if(optionalData.isPresent())
			{
				ProductEntity product = optionalData.get();
				return product;
			}
			else
			{
				return null;
			}
		}

		public void deleteProductById(Long id) {
			productRepository.deleteById(id);
			
		}
		
		
		public ProductModel getProductById(Long id) {
	        return productRepository.findById(id)
	                .map(productEntity -> {
	                    ProductModel productModel = new ProductModel();
	                    productModel.setProductname(productEntity.getProductname());
	                    productModel.setBrand(productEntity.getBrand());
	                    productModel.setMadeIn(productEntity.getMadeIn());
	                    productModel.setQuantity(productEntity.getQuantity());
	                    productModel.setPrice(productEntity.getPrice());
	                    productModel.setDiscountrate(productEntity.getDiscountRate());
	                    return productModel;
	                })
	                .orElse(null); // Return null if no product is found
	    }
	    public void updateProduct(Long id, ProductModel productModel) {
	        ProductEntity Product = productRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Product not found for ID: " + id));

	        // Update fields with the data from ProductModel
	        Product.setProductname(productModel.getProductname());
	        Product.setBrand(productModel.getBrand());
	        Product.setMadeIn(productModel.getMadeIn());
	        Product.setQuantity(productModel.getQuantity());
	        Product.setPrice(productModel.getPrice());
	        Product.setDiscountRate(productModel.getDiscountrate());

	        // Save the updated product
	        productRepository.save(Product);
	    }
		
		
}

