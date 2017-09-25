package com.soccer.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.soccer.entities.Images;

@Repository
public class ImagesReponsitory {
	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Images> getListImage(String imageTitle, String link, int limit, int offset) {

		String query = "Select DISTINCT(i) From Images i ";

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(query);

		if (imageTitle != null && imageTitle != "") {
			queryBuilder.append("Where i.imageTitle Like '%" + imageTitle + "%'");
		}

		if (link != null && link != "") {
			if (imageTitle != null && imageTitle != "") {
				queryBuilder.append(" AND i.link Like '%" + link + "%'");
			} else {
				queryBuilder.append("Where i.link Like '%" + link + "%'");
			}
		}

		queryBuilder.append(" ORDER BY i.imageId DESC");

		List<Images> objectResponse = new ArrayList<>();
		objectResponse = (List<Images>) entityManager.createQuery(queryBuilder.toString()).setFirstResult(offset)
				.setMaxResults(limit).getResultList();

		return objectResponse;
	}

	public Long getTotalListImages(String imageTitle, String link, int limit, int offset) {

		String query = "Select Count(i.imageId) From Images i ";

		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append(query);

		if (imageTitle != null && imageTitle != "") {
			queryBuilder.append("Where i.imageTitle Like '%" + imageTitle + "%'");
		}

		if (link != null && link != "") {
			if (imageTitle != null && imageTitle != "") {
				queryBuilder.append(" AND i.link Like '%" + link + "%'");
			} else {
				queryBuilder.append("Where i.link Like '%" + link + "%'");
			}
		}

		Long count = (Long) entityManager.createQuery(queryBuilder.toString()).getSingleResult();

		return count;
	}
}
