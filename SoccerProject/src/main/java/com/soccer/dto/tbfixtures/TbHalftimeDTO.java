package com.soccer.dto.tbfixtures;

import com.soccer.entities.TbHalftime;

public class TbHalftimeDTO {
	private Long id;

	private Long goalsHomeTeam;

	private Long goalsAwayTeam;

	public TbHalftimeDTO(TbHalftime tbHalftime) {
		if (tbHalftime != null) {
			this.id = tbHalftime.getId();
			this.goalsHomeTeam = tbHalftime.getGoalsHomeTeam();
			this.goalsAwayTeam = tbHalftime.getGoalsAwayTeam();
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

}
