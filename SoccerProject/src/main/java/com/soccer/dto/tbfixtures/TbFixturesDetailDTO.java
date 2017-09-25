package com.soccer.dto.tbfixtures;

import java.util.ArrayList;
import java.util.List;

import com.soccer.dao.TeamsDao;
import com.soccer.dto.tbcompetition.TeamDTO;
import com.soccer.entities.TbFixturesDetail;
import com.soccer.entities.TbTeams;

public class TbFixturesDetailDTO {
	private Long id;

	private String date;

	private String status;

	private Long matchday;

	private String homeTeamName;

	private String awayTeamName;

	private String odds;

	private int statusMatchDetail;

	private TbResultDTO tbResult;

	private List<TeamDTO> listTeamDTO;

	public TbFixturesDetailDTO(TbFixturesDetail fixturesDetail) {
		if (fixturesDetail != null) {
			this.id = fixturesDetail.getId();
			this.date = fixturesDetail.getDate();
			this.status = fixturesDetail.getStatus();
			this.matchday = fixturesDetail.getMatchday();
			this.homeTeamName = fixturesDetail.getHomeTeamName();
			this.awayTeamName = fixturesDetail.getAwayTeamName();
			this.odds = fixturesDetail.getOdds();
			if (fixturesDetail.getStatusMatchDetail() == null) {
				this.statusMatchDetail = 0;
			} else {
				this.statusMatchDetail = fixturesDetail.getStatusMatchDetail();
			}
			if (fixturesDetail.getTbResult() != null) {
				this.tbResult = new TbResultDTO(fixturesDetail.getTbResult());
			}

			if (!fixturesDetail.getTbFixtures().getTbCompetitions().getTbTeamsList().isEmpty()) {
				List<TeamDTO> listTeams = new ArrayList<>();
				List<TbTeams> listTbTeams = fixturesDetail.getTbFixtures().getTbCompetitions().getTbTeamsList();
				for (TbTeams tbTeams : listTbTeams) {
					listTeams.add(new TeamDTO(tbTeams));
				}
				this.listTeamDTO = listTeams;
			}
		}
	}

	public int getStatusMatchDetail() {
		return statusMatchDetail;
	}

	public void setStatusMatchDetail(int statusMatchDetail) {
		this.statusMatchDetail = statusMatchDetail;
	}



	public List<TeamDTO> getListTeamDTO() {
		return listTeamDTO;
	}

	public void setListTeamDTO(List<TeamDTO> listTeamDTO) {
		this.listTeamDTO = listTeamDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMatchday() {
		return matchday;
	}

	public void setMatchday(Long matchday) {
		this.matchday = matchday;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public String getOdds() {
		return odds;
	}

	public void setOdds(String odds) {
		this.odds = odds;
	}

	public TbResultDTO getTbResult() {
		return tbResult;
	}

	public void setTbResult(TbResultDTO tbResult) {
		this.tbResult = tbResult;
	}

}
