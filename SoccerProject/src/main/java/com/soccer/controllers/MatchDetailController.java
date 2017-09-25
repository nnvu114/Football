package com.soccer.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.Constants;
import com.soccer.commons.ResponseObject;
import com.soccer.dao.FixturesDetailDao;
import com.soccer.dao.MatchDetailDao;
import com.soccer.dto.UserDTO;
import com.soccer.entities.Tbmatchdetail;
import com.soccer.exception.UserNotFoundException;
import com.soccer.services.MatchDetailService;

@RestController
public class MatchDetailController {

	@Autowired
	private MatchDetailDao matchDetailDao;
	
	@Autowired
	private FixturesDetailDao fixturesDetailDao;

	@Autowired
	private MatchDetailService matchDetailService;

	@RequestMapping(value = "/create-match-detail", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject updateMatchDetail(@RequestParam(value = "matchId") Long matchId,
			@RequestBody Map<String, Object> objectMatchDetail, final HttpSession session) {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}
		ResponseObject responseObject = new ResponseObject();
		try {
			String titleMatch = (String) objectMatchDetail.get("titleMatch");
			String description = (String) objectMatchDetail.get("description");
			Tbmatchdetail matchDetail = new Tbmatchdetail();
			matchDetail.setId(matchId);
			matchDetail.setTitleMatch(titleMatch);
			matchDetail.setDescription(description);
			responseObject.setObject(matchDetailDao.save(matchDetail));
			responseObject.setMessage(Constants.MESSAGE_UPDATE_SUCCESS);
			responseObject.setStatus(true);
			
			fixturesDetailDao.updateStatusFixtureDetail(matchId);
		} catch (Exception e) {
			responseObject.setMessage(Constants.MESSAGE_UPDATE_FAILD);
			responseObject.setStatus(false);
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping(value = "/get-one-match-detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject getOneMatchDetail(@RequestParam(value = "matchId") Long matchId) {

		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setObject(matchDetailService.getOne(matchId));
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
		} catch (Exception e) {
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
			e.printStackTrace();
		}
		return responseObject;
	}
}
