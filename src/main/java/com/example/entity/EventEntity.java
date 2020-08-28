package com.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event")
public class EventEntity extends BaseEntity {
	
	@Column(columnDefinition="NVARCHAR(255)")
	private String name;

	@Column
	private String code;

	@Column(name = "percents")
	private Integer percent;

	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String description;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY)
	private List<ProductEntity> products = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEntity> products) {
		this.products = products;
	}

	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
