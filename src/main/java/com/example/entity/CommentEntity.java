package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity extends BaseEntity {
	
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String content;

	@ManyToOne
	@JoinColumn(name = "productid")
	private ProductEntity productComment;

	@ManyToOne
	@JoinColumn(name = "userid")
	private UserEntity userComment;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ProductEntity getProductComment() {
		return productComment;
	}

	public void setProductComment(ProductEntity productComment) {
		this.productComment = productComment;
	}

	public UserEntity getUserComment() {
		return userComment;
	}

	public void setUserComment(UserEntity userComment) {
		this.userComment = userComment;
	}
}
