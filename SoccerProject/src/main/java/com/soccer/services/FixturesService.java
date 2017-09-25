package com.soccer.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.dao.FixturesDao;
import com.soccer.dto.tbfixtures.TbFixturesDTO;
import com.soccer.entities.TbFixtures;

@Service
public class FixturesService {

	@Autowired
	private FixturesDao fixturesDao;

	public TbFixturesDTO findOneFixture(Long competitionId, Long match) {
		try {
			List<TbFixtures> fixtures = fixturesDao.findOneFixture(competitionId, match);
			if (!fixtures.isEmpty()) {
				return new TbFixturesDTO(fixtures.get(fixtures.size() - 1));
			} else {
				return new TbFixturesDTO();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new TbFixturesDTO();
		}

	}

	public int deleteFixture(Long competitionId) {
		return fixturesDao.deleteFixture(competitionId);

	}
}
