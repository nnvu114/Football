/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "videos")
public class Videos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VideoId")
	private Long videoId;

	@Column(name = "VideoTitle")
	private String videoTitle;

	@Column(name = "LinkDirect")
	private String linkDirect;

	@Column(name = "LinkUpload")
	private String linkUpload;

	@Column(name = "Create_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "\tStatus")
	private Short status;
	
	@Column(name = "LinkImage")
	private String linkImage;
	
	@Column(name = "VideoName")
	private String videoName;
	

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=false, mappedBy = "videoId", fetch = FetchType.LAZY)
	private List<News> newsList;

	@JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Categories categoryId;

	public Videos() {
	}

	public Videos(Long videoId) {
		this.videoId = videoId;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getLinkDirect() {
		return linkDirect;
	}

	public void setLinkDirect(String linkDirect) {
		this.linkDirect = linkDirect;
	}

	public String getLinkUpload() {
		return linkUpload;
	}

	public void setLinkUpload(String linkUpload) {
		this.linkUpload = linkUpload;
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

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public Categories getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Categories categoryId) {
		this.categoryId = categoryId;
	}

	public String getLinkImage() {
		return linkImage;
	}

	public void setLinkImage(String linkImage) {
		this.linkImage = linkImage;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (videoId != null ? videoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Videos)) {
			return false;
		}
		Videos other = (Videos) object;
		if ((this.videoId == null && other.videoId != null)
				|| (this.videoId != null && !this.videoId.equals(other.videoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "xxxxxxxxxx.Videos[ videoId=" + videoId + " ]";
	}

}
