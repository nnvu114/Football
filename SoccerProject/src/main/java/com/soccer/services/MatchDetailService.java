package com.soccer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.dao.MatchDetailDao;
import com.soccer.dto.matchdetail.MatchDetailDTO;

@Service
public class MatchDetailService {

	@Autowired
	private MatchDetailDao matchDetailDao;

	public MatchDetailDTO getOne(Long matchId) {

		return new MatchDetailDTO(matchDetailDao.findOne(matchId));
	}

}
