package com.soccer.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soccer.entities.TbFixturesDetail;

public interface FixturesDetailDao extends JpaRepository<TbFixturesDetail, Long> {

	@Query("Select fd From TbFixturesDetail fd WHERE fd.status = :status")
	public List<TbFixturesDetail> findAllFixtureDetailByStatus(@Param("status") String status, Pageable pageable);
	
	@Query("Select Count(fd) From TbFixturesDetail fd WHERE fd.status = :status")
	public Long countAllFixtureDetailByStatus(@Param("status") String status);
	
	@Query("Select fd From TbFixturesDetail fd WHERE fd.status = :status AND (:competitionId = null OR fd.tbFixtures.tbCompetitions.id = :competitionId)"
			+ " And fd.matchday = (SELECT max(fd.matchday) FROM TbFixturesDetail fd WHERE fd.status = :status)")
	public List<TbFixturesDetail> findAllFixtureDetailByCompetition(@Param("status") String status, @Param("competitionId") Long competitionId, Pageable pageable);

	@Query("Select Count(fd) From TbFixturesDetail fd WHERE fd.status = :status AND (:competitionId = null OR fd.tbFixtures.tbCompetitions.id = :competitionId)"
			+ " And fd.matchday = (SELECT max(fd.matchday) FROM TbFixturesDetail fd WHERE fd.status = :status)")
	public Long countAllFixtureDetailByComtition(@Param("status") String status, @Param("competitionId") Long competitionId);

	
	@Query("Select fd From TbFixturesDetail fd WHERE fd.status != 'FINISHED' AND (:competitionId = null OR fd.tbFixtures.tbCompetitions.id = :competitionId)"
			+ " AND (fd.matchday > (SELECT max(fd.matchday) FROM TbFixturesDetail fd WHERE fd.status = 'FINISHED') + :pages"
			+ " AND fd.matchday <= (SELECT max(fd.matchday) FROM TbFixturesDetail fd WHERE fd.status = 'FINISHED') + :limit)")
	public List<TbFixturesDetail> findAllFixtureDetailByMatchDay(@Param("competitionId") Long competitionId, @Param("limit") Long limit, @Param("pages") Long pages);


	@Modifying
	@Query("Update TbFixturesDetail n SET n.statusMatchDetail = 1 WHERE n.id = ?1")
	public void updateStatusFixtureDetail(long fixtureDetailId);
	
	@Modifying
	@Query("DELETE FROM TbFixturesDetail f WHERE f.tbFixtures.id = :competitionId")
	public int deleteFixture(@Param("competitionId") Long competitionId);
}
