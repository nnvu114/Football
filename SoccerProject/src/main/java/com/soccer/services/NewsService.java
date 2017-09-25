package com.soccer.services;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccer.dao.NewsDao;
import com.soccer.dto.news.NewsDTO;
import com.soccer.entities.News;
import com.soccer.entities.Videos;
import com.soccer.repositories.NewsReponsitory;

@Service
public class NewsService {
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private NewsReponsitory newsReponsitory;

	public List<NewsDTO> getAll() {
		List<NewsDTO> listNewsDTO = new ArrayList<>();
		List<News> listNews = newsDao.findAll();

		for (News news : listNews) {
			listNewsDTO.add(dozerBeanMapper.map(news, NewsDTO.class));
		}

		return listNewsDTO;
	}

	public List<Object> getAllHaveCondition(String content, long category, int limit, int offset) {

		List<Object> listNewsDTO = new ArrayList<>();

		List<News> listNews = newsReponsitory.getListNews(content, category, limit, offset);

		for (News news : listNews) {
			listNewsDTO.add(new NewsDTO(news));
		}

		return listNewsDTO;
	}

	public NewsDTO getOne(long newsId) {
		News news = newsDao.findOne(newsId);
		NewsDTO newsDTO = new NewsDTO(news);
		return newsDTO;
	}

	public void updateNews(Videos videoId, long newsId) {
		newsDao.updateNews(videoId, newsId);
	}

	// -------------------------USER----------------------------//

	public List<Object> findAllNews(Pageable pageable) {

		List<Object> listNewsDTO = new ArrayList<>();

		List<News> listNews = newsDao.findAllNews(pageable);

		for (News news : listNews) {
			listNewsDTO.add(new NewsDTO(news));
		}

		return listNewsDTO;
	}

	public List<Object> findNewsByPriorityLevel(Integer priorityLevel, Pageable pageable) {

		List<Object> listNewsDTO = new ArrayList<>();

		List<News> listNews = newsDao.findNewsByPriorityLevel(priorityLevel, pageable);

		for (News news : listNews) {
			listNewsDTO.add(new NewsDTO(news));
		}

		return listNewsDTO;
	}

	public List<Long> findNewsIdByPriorityLevel(Integer priorityLevel, Pageable pageable) {
		return newsDao.findNewsIdByPriorityLevel(priorityLevel, pageable);
	}

	public List<Object> findNewsByPriorityLevelOther(List<Long> listId, Pageable pageable) {

		List<Object> listNewsDTO = new ArrayList<>();

		List<News> listNews = newsDao.findNewsByPriorityLevelOther(listId, pageable);

		for (News news : listNews) {
			listNewsDTO.add(new NewsDTO(news));
		}

		return listNewsDTO;
	}

	public List<Object> findNewsByCategory(Long categoryId, Long newsId, Pageable pageable) {
		List<Object> listNewsDTO = new ArrayList<>();

		List<News> listNews = newsDao.findNewsByCategory(categoryId, newsId, pageable);

		for (News news : listNews) {
			listNewsDTO.add(new NewsDTO(news));
		}

		return listNewsDTO;
	}
	
	public List<Object> findNewsByCategoryId(Long categoryId, Pageable pageable) {
		List<Object> listNewsDTO = new ArrayList<>();

		List<News> listNews = newsDao.findNewsByCategoryId(categoryId, pageable);

		for (News news : listNews) {
			listNewsDTO.add(new NewsDTO(news));
		}

		return listNewsDTO;
	}

}
