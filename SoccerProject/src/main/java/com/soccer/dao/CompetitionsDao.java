package com.soccer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soccer.entities.TbCompetitions;

public interface CompetitionsDao extends JpaRepository<TbCompetitions, Long> {
	
	@Query("Select c From TbCompetitions c WHERE c.statusDelete = ?1")
	public List<TbCompetitions> findAll(Integer statusDelete);
}
