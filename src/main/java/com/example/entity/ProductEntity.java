package com.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {
	
	@Column(columnDefinition = "NVARCHAR(255)")
	private String name;
	
	@Column
	private String code;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String content;
	
	@Column(name = "shortdescription", columnDefinition = "NVARCHAR(MAX)")
	private String shortDescription;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String images;

	@Column(name = "seourl", columnDefinition = "NVARCHAR(MAX)")
	private String seoUrl;
	
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String specification;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String feature;

	@Column
	private Integer price;

	@Column
	private Integer quantity;

	@Column(columnDefinition = "NVARCHAR(MAX)", name = "pricedescription")
	private String priceDescription;

	@ManyToOne
	@JoinColumn(name = "productcategoryid")
	private ProductCategoryEntity productCategory;

	@ManyToOne
	@JoinColumn(name = "brandid")
	private BrandEntity brand;

	@ManyToOne
	@JoinColumn(name = "eventid")
	private EventEntity event;

	@OneToMany(mappedBy = "productComment", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<CommentEntity> comments = new ArrayList<>();

	@OneToMany(mappedBy = "productOrder", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderEntity> orders = new ArrayList<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public ProductCategoryEntity getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategoryEntity productCategory) {
		this.productCategory = productCategory;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPriceDescription() {
		return priceDescription;
	}

	public void setPriceDescription(String priceDescription) {
		this.priceDescription = priceDescription;
	}

	public String getSeoUrl() {
		return seoUrl;
	}

	public void setSeoUrl(String seoUrl) {
		this.seoUrl = seoUrl;
	}

	public EventEntity getEvent() {
		return event;
	}

	public void setEvent(EventEntity event) {
		this.event = event;
	}

	public BrandEntity getBrand() {
		return brand;
	}

	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}
}
