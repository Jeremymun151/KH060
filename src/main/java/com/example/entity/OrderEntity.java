package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {
	
	@Column
	private Integer quantity;

	@Column
	private Integer price;

	@Column
	private String status;

	@ManyToOne
	@JoinColumn(name = "productid")
	private ProductEntity productOrder;

	@ManyToOne
	@JoinColumn(name = "userid")
	private UserEntity userOrder;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserEntity getUserOrder() {
		return userOrder;
	}

	public void setUserOrder(UserEntity userOrder) {
		this.userOrder = userOrder;
	}

	public ProductEntity getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductEntity productOrder) {
		this.productOrder = productOrder;
	}
}
