package com.soccer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soccer.entities.TbFixtures;

public interface FixturesDao extends JpaRepository<TbFixtures, Long> {

	@Query("Select f From TbFixtures f INNER JOIN FETCH f.tbFixturesDetailList fd WHERE f.tbCompetitions.id = :competitionId AND fd.matchday = :matchday")
	public List<TbFixtures> findOneFixture(@Param("competitionId") Long competitionId,
			@Param("matchday") Long matchday);

	@Modifying
	@Query("DELETE FROM TbFixtures f WHERE f.tbCompetitions.id = :competitionId")
	public int deleteFixture(@Param("competitionId") Long competitionId);
}
