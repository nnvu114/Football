package com.soccer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.soccer.dao.FixturesDetailDao;
import com.soccer.dto.schedule.SchedulesDTO;
import com.soccer.dto.tbfixtures.TbFixturesDetailDTO;
import com.soccer.entities.TbFixturesDetail;

@Service
public class FixtureDetailService {

	@Autowired
	private FixturesDetailDao fixturesDetailDao;

	public List<Object> findOneFixtureDetail(String status, Pageable pageable) {
		try {
			List<TbFixturesDetail> fixturesDetails = fixturesDetailDao.findAllFixtureDetailByStatus(status, pageable);
			List<Object> listFixturesDetails = new ArrayList<>();
			for (TbFixturesDetail tbFixturesDetail : fixturesDetails) {
				listFixturesDetails.add(new TbFixturesDetailDTO(tbFixturesDetail));
			}
			return listFixturesDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public Long countAllFixtureDetail(String status) {
		return fixturesDetailDao.countAllFixtureDetailByStatus(status);
	}

	public List<Object> findAllFixtureDetailByCompetition(String status, Long competitionId, Pageable pageable) {
		try {
			List<TbFixturesDetail> fixturesDetails = fixturesDetailDao.findAllFixtureDetailByCompetition(status,
					competitionId, pageable);
			List<Object> listFixturesDetails = new ArrayList<>();
			for (TbFixturesDetail tbFixturesDetail : fixturesDetails) {
				listFixturesDetails.add(new TbFixturesDetailDTO(tbFixturesDetail));
			}
			return listFixturesDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public Long countAllFixtureDetailByComtition(String status, Long competitionId) {
		return fixturesDetailDao.countAllFixtureDetailByComtition(status, competitionId);
	}

	public List<Object> findAllFixtureDetailByMatchDay(Long competitionId, Long limit, Long pages) {
		try {
			List<TbFixturesDetail> fixturesDetails = fixturesDetailDao.findAllFixtureDetailByMatchDay(competitionId,
					limit, pages);

			Long matchDay = (long) 0;
			if (!fixturesDetails.isEmpty()) {
				matchDay = fixturesDetails.get(0).getMatchday();
			}
			List<Object> listFixturesDetails = new ArrayList<>();
			
			List<Object> result = new ArrayList<>();
			for (TbFixturesDetail tbFixturesDetail : fixturesDetails) {
				System.err.println("matchDay " + matchDay);
				SchedulesDTO schedulesDTO = new SchedulesDTO();

				if (matchDay == tbFixturesDetail.getMatchday()) {
					listFixturesDetails.add(new TbFixturesDetailDTO(tbFixturesDetail));
				} else {
					schedulesDTO.setMatchDay(matchDay);
					schedulesDTO.setListMatchDay(listFixturesDetails);
					result.add(schedulesDTO);
					matchDay = tbFixturesDetail.getMatchday();
					listFixturesDetails = new ArrayList<>();
				}

			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
