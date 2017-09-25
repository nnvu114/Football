package com.soccer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.Constants;
import com.soccer.commons.ResponseObject;
import com.soccer.services.FixturesService;

@RestController
public class FixturesController {

	@Autowired
	private FixturesService fixturesService;

	@RequestMapping("/find-one-fixture")
	@ResponseBody
	public ResponseObject findOneFixture(@RequestParam("competitionId") Long competitionId,
			@RequestParam("match") Long match) {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
			responseObject.setObject(fixturesService.findOneFixture(competitionId, match));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
		}
		return responseObject;
	}
}
