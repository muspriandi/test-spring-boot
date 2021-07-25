package com.example.demoapi.controllers.json;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class ProductView {
	
	private UUID productId;

    @NotNull(message = "Field 'productName' is required")
	private String productName;

    @NotNull(message = "Field 'productDescription' is required")
	private String productDescription;

    @NotNull(message = "Field 'productPrice' is required")
	private Double productPrice;

    @NotNull(message = "Field 'categoryId' is required")
	private int categoryId;

	public UUID getProductId() {
		return productId;
	}

	public void setProductId(UUID productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
