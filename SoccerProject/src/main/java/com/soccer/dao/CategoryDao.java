package com.soccer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soccer.entities.Categories;

public interface CategoryDao extends JpaRepository<Categories, Long> {
	
	@Query("Select c From Categories c WHERE ?1 IS NULL OR c.categoryName = ?1")
	public List<Categories> getListCategories(String categotyName);
}
