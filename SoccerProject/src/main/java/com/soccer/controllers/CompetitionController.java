package com.soccer.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.Constants;
import com.soccer.commons.ResponseObject;
import com.soccer.services.CompetitionService;

@RestController
public class CompetitionController {

	@Autowired
	private CompetitionService competitionService;

	@RequestMapping("/find-one-competition")
	@ResponseBody
	public ResponseObject findOneCompetition(@RequestParam("competitionId") long competitionId,
			final HttpSession session) {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(competitionService.findOneCompetitions(competitionId));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/find-all-competitions", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findAllCompetitions() {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(competitionService.getAllCompetitions(0));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}
}
