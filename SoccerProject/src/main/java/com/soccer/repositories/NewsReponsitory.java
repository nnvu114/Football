package com.soccer.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soccer.entities.News;

@Repository
public class NewsReponsitory {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<News> getListNews(String content, long category, int limit, int offset) {

		String query = "Select DISTINCT(n) From News n ";

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(query);

		if (content != null && content != "") {
			queryBuilder.append("Where n.newsTitle Like '%" + content + "%'");
		}
		
		if (category > 0) {
			if(content != null && content != "") {
				queryBuilder.append(" AND n.categoryId =" + category);
			} else {
				queryBuilder.append("Where n.categoryId =" + category);
			}
		}

		queryBuilder.append(" ORDER BY n.newsId DESC");
		
		List<News> objectResponse = new ArrayList<>();
		objectResponse = (List<News>) entityManager.createQuery(queryBuilder.toString())
				.setFirstResult(offset)
				.setMaxResults(limit).getResultList();

		return objectResponse;
	}
	
	
	public Long getTotalListNews(String content, long category, int limit, int offset) {

		String query = "Select Count(n.newsId) From News n ";

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(query);

		if (content != null && content != "") {
			queryBuilder.append("Where n.newsTitle Like '%" + content + "%'");
		}
		
		if (category > 0) {
			if(content != null && content != "") {
				queryBuilder.append(" AND n.categoryId =" + category);
			} else {
				queryBuilder.append("Where n.categoryId =" + category);
			}
		}
		
		System.err.println("offset" + offset);
		System.err.println("limit" + limit);
		Long count = (Long) entityManager.createQuery(queryBuilder.toString()).getSingleResult();
		
		return count;
	}
}
