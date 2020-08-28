package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "featuredetail")
public class FeatureDetailEntity extends BaseEntity {

	@Column(columnDefinition="NVARCHAR(255)")
	private String name;

	@Column
	private String code;

	@ManyToOne
	@JoinColumn(name = "featureid")
	private FeatureEntity feature;

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

	public FeatureEntity getFeature() {
		return feature;
	}

	public void setFeature(FeatureEntity feature) {
		this.feature = feature;
	}
}
