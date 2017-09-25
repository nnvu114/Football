package com.soccer.dto.news;

import java.util.Date;

import com.soccer.entities.Categories;

public class CatelogyDTO {
	private long categoryId;

	private String categoryName;

	private Date createDate;

	public CatelogyDTO() {
	}

	public CatelogyDTO(Categories category) {
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getCategoryName();
		this.createDate = category.getCreateDate();
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
