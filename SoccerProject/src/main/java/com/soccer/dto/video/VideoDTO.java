package com.soccer.dto.video;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.soccer.entities.News;
import com.soccer.entities.Videos;

public class VideoDTO {
	private Long videoId;

	private String videoTitle;

	private String linkDirect;

	private String linkUpload;

	private Date createDate;

	private Short status;

	private String linkImage;

	private String videoName;

	private List<NewsDTO> listNews;

	public VideoDTO(Videos video) {
		this.videoId = video.getVideoId();
		this.videoTitle = video.getVideoTitle();
		this.linkDirect = video.getLinkDirect();
		this.linkUpload = video.getLinkUpload();
		this.createDate = video.getCreateDate();
		this.status = video.getStatus();
		this.linkImage = video.getLinkImage();
		this.videoName = video.getVideoName();
		if (!video.getNewsList().isEmpty()) {
			List<NewsDTO> listNewsDTO = new ArrayList<>();
			List<News> listNewses = video.getNewsList();
			for (News news : listNewses) {
				listNewsDTO.add(new NewsDTO(news));
			}
			this.listNews = listNewsDTO;
		}
	}

	public List<NewsDTO> getListNews() {
		return listNews;
	}

	public void setListNews(List<NewsDTO> listNews) {
		this.listNews = listNews;
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
