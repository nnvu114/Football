/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jame Moriarty
 */
@Entity
@Table(name = "views")
public class Views implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\tViewId")
	private Long viewId;

	@Column(name = "NumberViews")
	private String numberViews;

	@Column(name = "Create_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "\tUpload_Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	
	@JoinColumn(name = "NewsId", referencedColumnName = "\tNewsId")
	@ManyToOne(fetch = FetchType.LAZY)

	private News newsId;

	public Views() {
	}

	public Views(Long viewId) {
		this.viewId = viewId;
	}

	public Long getViewId() {
		return viewId;
	}

	public void setViewId(Long viewId) {
		this.viewId = viewId;
	}

	public String getNumberViews() {
		return numberViews;
	}

	public void setNumberViews(String numberViews) {
		this.numberViews = numberViews;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public News getNewsId() {
		return newsId;
	}

	public void setNewsId(News newsId) {
		this.newsId = newsId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (viewId != null ? viewId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Views)) {
			return false;
		}
		Views other = (Views) object;
		if ((this.viewId == null && other.viewId != null)
				|| (this.viewId != null && !this.viewId.equals(other.viewId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "xxxxxxxxxx.Views[ viewId=" + viewId + " ]";
	}

}
