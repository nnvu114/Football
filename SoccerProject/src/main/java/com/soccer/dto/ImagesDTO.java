package com.soccer.dto;

import java.util.Date;

import com.soccer.entities.Images;

public class ImagesDTO {
	private Long imageId;

	private String imageTitle;

	private String link;

	private Date createDate;

	private Short status;

	public ImagesDTO() {
	}

	public ImagesDTO(Images image) {
		this.imageId = image.getImageId();
		this.imageTitle = image.getImageTitle();
		this.link = image.getLink();
		this.createDate = image.getCreateDate();
		this.status = image.getStatus();
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
}
