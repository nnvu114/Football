package com.soccer.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.soccer.entities.News;
import com.soccer.entities.Videos;

public interface NewsDao extends JpaRepository<News, Long> {

	@Modifying
	@Query("Update News n SET n.videoId = ?1 WHERE n.newsId = ?2")
	public void updateNews(Videos videoId, long newsId);

	@Query("Select n From News n")
	public List<News> findAllNews(Pageable pageable);

	@Query("Select n From News n WHERE n.priorityLevel = ?1")
	public List<News> findNewsByPriorityLevel(Integer priorityLevel, Pageable pageable);

	@Query("Select n.newsId From News n WHERE n.priorityLevel = ?1")
	public List<Long> findNewsIdByPriorityLevel(Integer priorityLevel, Pageable pageable);

	@Query("Select n From News n WHERE n.newsId NOT IN ?1")
	public List<News> findNewsByPriorityLevelOther(List<Long> listId, Pageable pageable);

	@Query("Select n From News n WHERE n.categoryId.categoryId = ?1 AND n.newsId != ?2")
	public List<News> findNewsByCategory(Long categoryId, Long newsId, Pageable pageable);
	
	@Query("Select n From News n WHERE n.categoryId.categoryId = ?1")
	public List<News> findNewsByCategoryId(Long categoryId, Pageable pageable);
}
