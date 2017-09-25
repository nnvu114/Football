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

/**
 *
 * @author Jame Moriarty
 */
@Entity
@Table(name = "categories")
public class Categories implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoryId")
	private Long categoryId;

	@Column(name = "CategoryName")
	private String categoryName;

	@Column(name = "Create_Date")
	private Date createDate;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=false, mappedBy = "categoryId", fetch = FetchType.LAZY)
	private List<News> newsList;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=false, mappedBy = "categoryId", fetch = FetchType.LAZY)
	private List<Videos> videosList;

	public Categories() {
	}

	public Categories(Long categoryId) {
		this.categoryId = categoryId;
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

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public List<Videos> getVideosList() {
		return videosList;
	}

	public void setVideosList(List<Videos> videosList) {
		this.videosList = videosList;
	}

}
