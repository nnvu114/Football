package com.soccer.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soccer.entities.Categories;
import com.soccer.entities.Videos;

public interface VideoDao extends JpaRepository<Videos, Long> {

	@Query("Select v From Videos v WHERE (?1 IS NULL OR v.videoTitle = ?1) AND (?2 IS NULL OR v.categoryId = ?2)")
	public List<Videos> findAllVideos(String videoTitle, Categories categories, Pageable pageable);

	@Query("Select Count(v) From Videos v WHERE (?1 IS NULL OR v.videoTitle = ?1) AND (?2 IS NULL OR v.categoryId = ?2)")
	public Long findTotalVideos(String videoTitle, Categories categories);

	@Query("Select v From Videos v")
	public List<Videos> findOneVideo(Pageable pageable);
	
	@Query("Select v From Videos v WHERE v.videoId != ?2 AND (?1 = NULL OR v.categoryId.categoryId = ?1)")
	public List<Videos> findAllVideoByCategory(Long categoryId, Long videoId, Pageable pageable);
	
	@Query("Select v From Videos v WHERE v.videoId NOT IN ?2 AND (?1 = NULL OR v.categoryId.categoryId = ?1)")
	public List<Videos> findAllVideoDetails(Long categoryId, List<Long> videoId, Pageable pageable);
}
