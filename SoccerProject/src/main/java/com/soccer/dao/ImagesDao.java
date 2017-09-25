package com.soccer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soccer.entities.Images;

public interface ImagesDao extends JpaRepository<Images, Long> {

	@Query("Select i From Images i WHERE ?1 IS NULL OR i.imageTitle LIKE CONCAT('%', ?1, '%')")
	public List<Images> getListImages(String imageTitle);
}
