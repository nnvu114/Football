package com.soccer.dto.tbcompetition;

import java.util.ArrayList;
import java.util.List;

import com.soccer.entities.TbLeaguetable;
import com.soccer.entities.TbStanding;

public class LeaguetableDTO {

	private Long id;

	private String leagueCaption;

	private Long matchday;

	private List<StandingDTO> tbStandingList;

	public LeaguetableDTO() {
	}

	public LeaguetableDTO(TbLeaguetable leaguetable) {
		this.id = leaguetable.getId();
		this.leagueCaption = leaguetable.getLeagueCaption();
		this.matchday = leaguetable.getMatchday();
		if (!leaguetable.getTbStandingList().isEmpty()) {
			List<StandingDTO> standings = new ArrayList<>();
			for (TbStanding standing : leaguetable.getTbStandingList()) {
				standings.add(new StandingDTO(standing));
			}
			this.tbStandingList = standings;
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeagueCaption() {
		return leagueCaption;
	}

	public void setLeagueCaption(String leagueCaption) {
		this.leagueCaption = leagueCaption;
	}

	public Long getMatchday() {
		return matchday;
	}

	public void setMatchday(Long matchday) {
		this.matchday = matchday;
	}

	public List<StandingDTO> getTbStandingList() {
		return tbStandingList;
	}

	public void setTbStandingList(List<StandingDTO> tbStandingList) {
		this.tbStandingList = tbStandingList;
	}

}
