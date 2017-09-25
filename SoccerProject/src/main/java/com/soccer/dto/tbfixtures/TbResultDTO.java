package com.soccer.dto.tbfixtures;

import com.soccer.entities.TbResult;

public class TbResultDTO {

	private Long id;

	private Long goalsHomeTeam;

	private Long goalsAwayTeam;

	private TbHalftimeDTO tbHalftime;

	public TbResultDTO(TbResult tbResult) {
		this.id = tbResult.getId();
		this.goalsHomeTeam = tbResult.getGoalsHomeTeam();
		this.goalsAwayTeam = tbResult.getGoalsAwayTeam();
		if (tbResult.getTbHalftime() != null) {
			this.tbHalftime = new TbHalftimeDTO(tbResult.getTbHalftime());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(Long goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public Long getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(Long goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	public TbHalftimeDTO getTbHalftime() {
		return tbHalftime;
	}

	public void setTbHalftime(TbHalftimeDTO tbHalftime) {
		this.tbHalftime = tbHalftime;
	}

}
