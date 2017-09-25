package com.soccer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.Constants;
import com.soccer.commons.ObjectResponseTotal;
import com.soccer.commons.ResponseObject;
import com.soccer.services.FixtureDetailService;

@RestController
public class FixturesDetailController {

	@Autowired
	private FixtureDetailService fixtureDetailService;

	@RequestMapping("/find-all-fixture-details")
	@ResponseBody
	public ResponseObject findOneFixture(@RequestParam("status") String status, @RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
			Pageable pageable = new PageRequest(offset / limit, limit);
			responseObject.setObject(fixtureDetailService.findOneFixtureDetail(status, pageable));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
		}
		return responseObject;
	}

	@RequestMapping("/find-all-fixture-details-by-status")
	@ResponseBody
	public ResponseObject findAllFixture(@RequestParam("status") String status, @RequestParam("limit") Integer limit,
			@RequestParam("offset") Integer offset) {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
			Pageable pageable = new PageRequest(offset / limit, limit);

			ObjectResponseTotal objectResponseTotal = new ObjectResponseTotal();
			objectResponseTotal.setData(fixtureDetailService.findOneFixtureDetail(status, pageable));
			objectResponseTotal.setTotal(fixtureDetailService.countAllFixtureDetail(status));
			responseObject.setObject(objectResponseTotal);
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
		}
		return responseObject;
	}

	@RequestMapping("/find-all-fixture-details-by-competition")
	@ResponseBody
	public ResponseObject findAllFixtureByCompetitions(@RequestParam("status") String status,
			@RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset,
			@RequestParam("competitionId") Long competitionId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			if (competitionId == 0) {
				competitionId = null;
			}
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
			Pageable pageable = new PageRequest(offset / limit, limit);

			ObjectResponseTotal objectResponseTotal = new ObjectResponseTotal();
			objectResponseTotal
					.setData(fixtureDetailService.findAllFixtureDetailByCompetition(status, competitionId, pageable));
			objectResponseTotal.setTotal(fixtureDetailService.countAllFixtureDetailByComtition(status, competitionId));
			responseObject.setObject(objectResponseTotal);
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
		}
		return responseObject;
	}
	
	@RequestMapping("/find-all-fixture-by-matchday")
	@ResponseBody
	public ResponseObject findAllFixtureByMatch(@RequestParam("competitionId") Long competitionId, @RequestParam("limit") Long limit,
			@RequestParam("pages") Long pages) {
		ResponseObject responseObject = new ResponseObject();
		Long limitCurrent = null;
		Long pageCurrent = null;
		try {
			limitCurrent = limit * pages + limit;
			pageCurrent = limit * pages;
			if( pageCurrent > 0 ){
				pageCurrent--;
			} else {
				limitCurrent++;
			}
			
			
			
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
			responseObject.setObject(fixtureDetailService.findAllFixtureDetailByMatchDay(competitionId, limitCurrent, pageCurrent));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
		}
		return responseObject;
	}

}
