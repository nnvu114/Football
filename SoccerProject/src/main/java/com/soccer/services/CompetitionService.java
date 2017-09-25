package com.soccer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.dao.CompetitionsDao;
import com.soccer.dto.tbcompetition.CompetitionDTO;
import com.soccer.entities.TbCompetitions;

@Service
public class CompetitionService {

	@Autowired
	private CompetitionsDao competitionsDao;

	public List<Object> getAllCompetitions(Integer statusDelete) {
		List<TbCompetitions> listCompetitions = competitionsDao.findAll(statusDelete);
		List<Object> list = new ArrayList<>();
		for (TbCompetitions competition : listCompetitions) {

			list.add(new CompetitionDTO(competition, true));
		}
		return list;
	}

	public CompetitionDTO findOneCompetitions(Long competitionId) {
		CompetitionDTO competitionDTO = new CompetitionDTO(competitionsDao.findOne(competitionId));
		return competitionDTO;
	}
}
