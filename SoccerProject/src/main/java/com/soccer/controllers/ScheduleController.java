package com.soccer.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.GetDataFromOtherWeb;
import com.soccer.dto.UserDTO;
import com.soccer.dto.schedule.ScheduleDTO;
import com.soccer.exception.UserNotFoundException;

@RestController
public class ScheduleController {

	@RequestMapping("/schedule")
	@ResponseBody
	public String getListChedule(@RequestParam(value = "scheduleId") String scheduleId, final HttpSession session) {
		
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}
		
		try {
			String url = scheduleId;
			JSONObject json = GetDataFromOtherWeb.readJsonObjectFromUrl(url);
			return json.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ScheduleDTO> getScheduleDetail(String url, final HttpSession session){
		
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}
		List<ScheduleDTO> listSchedule = new ArrayList<>();
		
		for (int i = 1; i < 20; i++) {
			try {
				ScheduleDTO scheduleDTO = new ScheduleDTO();
				JSONObject json = GetDataFromOtherWeb.readJsonObjectFromUrl(url + "?matchday=" + i);
				scheduleDTO.setDataEachRound(json.toString());
				scheduleDTO.setNumberOfRounds(i);
				listSchedule.add(scheduleDTO);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listSchedule;
	}

}
