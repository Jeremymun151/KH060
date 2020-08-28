package com.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "feature")
public class FeatureEntity extends BaseEntity {

	@Column(columnDefinition="NVARCHAR(255)")
	private String name;

	@Column
	private String code;

	@Column(name = "productcategorycode")
	private String productCategoryCode;

	@OneToMany(mappedBy = "feature", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<FeatureDetailEntity> featureDetails = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<FeatureDetailEntity> getFeatureDetails() {
		return featureDetails;
	}

	public void setFeatureDetails(List<FeatureDetailEntity> featureDetails) {
		this.featureDetails = featureDetails;
	}

	public String getProductCategoryCode() {
		return productCategoryCode;
	}

	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}
}
