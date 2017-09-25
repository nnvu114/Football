/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jame Moriarty
 */
@Entity
@Table(name = "news")
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NewsId")
	private Long newsId;

	@Column(name = "NewsTitle")
	private String newsTitle;

	@Lob
	@Column(name = "NewsDescription")
	private String newsDescription;
	
	@Lob
	@Column(name = "DescriptionCore")
	private String descriptionCore;

	@Column(name = "PriorityLevel")
	private Integer priorityLevel;

	@Column(name = "StatusNews")
	private Integer statusNews;

	@Column(name = "\tCreate_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "SourceArticle")
	private String sourceArticle;

	@Column(name = "Author")
	private String author;

	@JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Categories categoryId;

	@JoinColumn(name = "VideoId", referencedColumnName = "VideoId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Videos videoId;

	@JoinColumn(name = "ImagesId", referencedColumnName = "ImageId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Images imagesId;

	@OneToMany(mappedBy = "newsId", fetch = FetchType.LAZY)
	private List<Views> viewsList;

	public News() {
	}

	public News(Long newsId) {
		this.newsId = newsId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
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

	public Categories getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}

	public Videos getVideoId() {
		return videoId;
	}

	public void setVideoId(Videos videoId) {
		this.videoId = videoId;
	}

	public Images getImagesId() {
		return imagesId;
	}

	public void setImagesId(Images imagesId) {
		this.imagesId = imagesId;
	}

	public List<Views> getViewsList() {
		return viewsList;
	}

	public void setViewsList(List<Views> viewsList) {
		this.viewsList = viewsList;
	}

	public String getDescriptionCore() {
		return descriptionCore;
	}

	public void setDescriptionCore(String descriptionCore) {
		this.descriptionCore = descriptionCore;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (newsId != null ? newsId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof News)) {
			return false;
		}
		News other = (News) object;
		if ((this.newsId == null && other.newsId != null)
				|| (this.newsId != null && !this.newsId.equals(other.newsId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "xxxxxxxxxx.News[ newsId=" + newsId + " ]";
	}

}
