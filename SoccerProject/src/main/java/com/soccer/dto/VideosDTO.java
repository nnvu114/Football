package com.soccer.dto;

import java.util.Date;

public class VideosDTO {
	private Long videoId;

	private String videoTitle;

	private String linkDirect;

	private String linkUpload;

	private Date createDate;

	private Short status;

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

}
