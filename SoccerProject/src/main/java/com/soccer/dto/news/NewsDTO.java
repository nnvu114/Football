package com.soccer.dto.news;

import java.util.Date;

import com.soccer.dto.ImagesDTO;
import com.soccer.entities.News;

public class NewsDTO {

	private long newsId;

	private String newsTitle;

	private String newsDescription;

	private Integer priorityLevel;

	private Integer statusNews;

	private Date createDate;

	private String sourceArticle;

	private String author;

	private CatelogyDTO catelogyDTO;

	private VideosDTO videosDTO;

	private ImagesDTO imagesDTO;
	
	private String descriptionCore;

	public NewsDTO() {
	}

	public NewsDTO(News news) {
		if (news != null) {
			this.newsId = news.getNewsId();
			this.newsTitle = news.getNewsTitle();
			this.newsDescription = news.getNewsDescription();
			this.priorityLevel = news.getPriorityLevel();
			this.statusNews = news.getStatusNews();
			this.createDate = news.getCreateDate();
			this.sourceArticle = news.getSourceArticle();
			this.author = news.getAuthor();
			this.descriptionCore = news.getDescriptionCore();
			if (news.getCategoryId() != null || news.getCategoryId().getCategoryId() > 0) {
				this.catelogyDTO = new CatelogyDTO(news.getCategoryId());
			}
			if (news.getVideoId() != null) {
				this.videosDTO = new VideosDTO(news.getVideoId());
			}
			if (news.getImagesId() != null) {
				this.imagesDTO = new ImagesDTO(news.getImagesId());
			}
		}
	}

	public String getDescriptionCore() {
		return descriptionCore;
	}

	public void setDescriptionCore(String descriptionCore) {
		this.descriptionCore = descriptionCore;
	}

	public ImagesDTO getImagesDTO() {
		return imagesDTO;
	}

	public void setImagesDTO(ImagesDTO imagesDTO) {
		this.imagesDTO = imagesDTO;
	}

	public long getNewsId() {
		return newsId;
	}

	public void setNewsId(long newsId) {
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getSourceArticle() {
		return sourceArticle;
	}

	public void setSourceArticle(String sourceArticle) {
		this.sourceArticle = sourceArticle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public CatelogyDTO getCatelogyDTO() {
		return catelogyDTO;
	}

	public void setCatelogyDTO(CatelogyDTO catelogyDTO) {
		this.catelogyDTO = catelogyDTO;
	}

	public VideosDTO getVideosDTO() {
		return videosDTO;
	}

	public void setVideosDTO(VideosDTO videosDTO) {
		this.videosDTO = videosDTO;
	}

}
