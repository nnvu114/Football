package com.soccer.dto.news;

import java.util.Date;

import com.soccer.entities.Videos;

public class VideosDTO {

	private Long videoId;

	private String videoTitle;

	private String linkDirect;

	private String linkUpload;

	private Date createDate;

	private Short status;
	
	private String linkImage;

	private String videoName;

	public VideosDTO() {
	}

	public VideosDTO(Videos video) {
		this.videoId = video.getVideoId();
		this.videoTitle = video.getVideoTitle();
		this.linkDirect = video.getLinkDirect();
		this.linkUpload = video.getLinkUpload();
		this.createDate = video.getCreateDate();
		this.status = video.getStatus();
		this.linkImage = video.getLinkImage();
		this.videoName = video.getVideoName();
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
}
