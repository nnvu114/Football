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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jame Moriarty
 */
@Entity
@Table(name = " images")
public class Images implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ImageId")
	private Long imageId;

	@Column(name = "ImageTitle")
	private String imageTitle;

	@Column(name = "\tLink")
	private String link;

	@Column(name = "Create_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "Status")
	private Short status;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=false, mappedBy = "imagesId", fetch = FetchType.LAZY)
	private List<News> newsList;

	public Images() {
	}

	public Images(Long imageId) {
		this.imageId = imageId;
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

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (imageId != null ? imageId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Images)) {
			return false;
		}
		Images other = (Images) object;
		if ((this.imageId == null && other.imageId != null)
				|| (this.imageId != null && !this.imageId.equals(other.imageId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "xxxxxxxxxx.Images[ imageId=" + imageId + " ]";
	}

}
