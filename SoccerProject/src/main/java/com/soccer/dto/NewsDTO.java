package com.soccer.dto;

public class NewsDTO {
	private Integer newsId;

	private String newsTitle;

	private String newsDescription;

	private Integer priorityLevel;

	private Integer statusNews;
	
	private String descriptionCore; 

	public String getDescriptionCore() {
		return descriptionCore;
	}

	public void setDescriptionCore(String descriptionCore) {
		this.descriptionCore = descriptionCore;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsDescription() {
		return newsDescription;
	}

	public void setNewsDescription(String newsDescription) {
		this.newsDescription = newsDescription;
	}

	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public Integer getStatusNews() {
		return statusNews;
	}

	public void setStatusNews(Integer statusNews) {
		this.statusNews = statusNews;
	}
	
	
}
