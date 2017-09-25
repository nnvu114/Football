package com.soccer.dto;

import java.util.Date;
import java.util.List;

import org.dozer.Mapper;

import com.soccer.entities.Categories;

public class CatelogyDTO {

	private Long categoryId;

	private String categoryName;

	private Date createDate;

	private List<NewsDTO> newsList;

	private List<VideosDTO> videosList;

	public CatelogyDTO() {
	}

//	public CatelogyDTO(Categories category) {
//		this.categoryId = category.getCategoryId();
//		this.categoryName = category.getCategoryName();
//		this.createDate = category.getCreateDate();
//	}

	public CatelogyDTO(Mapper mapper, Long categoryId, String categoryName, Date createDate, List<NewsDTO> newsList,
			List<VideosDTO> videosList) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.createDate = createDate;
		this.newsList = newsList;
		this.videosList = videosList;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
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

	public List<NewsDTO> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<NewsDTO> newsList) {
		this.newsList = newsList;
	}

	public List<VideosDTO> getVideosList() {
		return videosList;
	}

	public void setVideosList(List<VideosDTO> videosList) {
		this.videosList = videosList;
	}

}
