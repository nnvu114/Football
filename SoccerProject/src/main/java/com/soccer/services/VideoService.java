package com.soccer.services;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccer.dao.VideoDao;
import com.soccer.dto.video.VideoDTO;
import com.soccer.entities.Categories;
import com.soccer.entities.Videos;

@Service
public class VideoService {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	private VideoDao videoDao;

	public List<Object> findAllVideos(String videoTitle, Categories categories, Pageable pageable) {
		List<Object> listVideoDTO = new ArrayList<>();
		List<Videos> listVideo = videoDao.findAllVideos(videoTitle, categories, pageable);

		for (Videos video : listVideo) {
			VideoDTO videoDTO = new VideoDTO(video);
			listVideoDTO.add(videoDTO);
		}

		return listVideoDTO;
	}

	public Long findTotalVideos(String videoTitle, Categories categories) {
		return videoDao.findTotalVideos(videoTitle, categories);
	}

	public VideoDTO findOneVideo(Pageable pageable) {
		List<Videos> videos = videoDao.findOneVideo(pageable);

		if (!videos.isEmpty()) {
			return new VideoDTO(videos.get(0));
		}
		return null;
	}

	public List<VideoDTO> findAllVideoByCategory(Long categoryId, Long videoId, Pageable pageable) {
		List<Videos> videoses = videoDao.findAllVideoByCategory(categoryId, videoId, pageable);

		List<VideoDTO> listVideoDTO = new ArrayList<>();
		for (Videos videos : videoses) {
			listVideoDTO.add(new VideoDTO(videos));
		}
		return listVideoDTO;
	}
	
	public List<Object> findAllVideoDetails(Long categoryId, List<Long> videoId, Pageable pageable) {
		List<Object> listVideoDTO = new ArrayList<>();
		List<Videos> listVideo = videoDao.findAllVideoDetails(categoryId, videoId, pageable);

		for (Videos video : listVideo) {
			VideoDTO videoDTO = new VideoDTO(video);
			listVideoDTO.add(videoDTO);
		}

		return listVideoDTO;
	}

}
