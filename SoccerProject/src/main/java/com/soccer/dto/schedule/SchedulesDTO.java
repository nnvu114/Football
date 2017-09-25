package com.soccer.dto.schedule;

import java.util.List;

public class SchedulesDTO {
	private Long matchDay;

	private List<Object> listMatchDay;

	public Long getMatchDay() {
		return matchDay;
	}

	public void setMatchDay(Long matchDay) {
		this.matchDay = matchDay;
	}

	public List<Object> getListMatchDay() {
		return listMatchDay;
	}

	public void setListMatchDay(List<Object> listMatchDay) {
		this.listMatchDay = listMatchDay;
	}
}
