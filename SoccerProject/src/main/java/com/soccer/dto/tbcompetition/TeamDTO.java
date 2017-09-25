package com.soccer.dto.tbcompetition;

import com.soccer.entities.TbTeams;

public class TeamDTO {
	private Integer id;

	private String name;

	private String code;

	private String shortName;

	private String squadMarketValue;

	private String crestUrl;

	private String tbTeamscol;

	public TeamDTO(TbTeams team) {
		if (team != null) {
			this.id = team.getId();
			this.name = team.getName();
			this.code = team.getCode();
			this.shortName = team.getShortName();
			this.squadMarketValue = team.getSquadMarketValue();
			this.crestUrl = team.getCrestUrl();
			this.tbTeamscol = team.getTbTeamscol();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSquadMarketValue() {
		return squadMarketValue;
	}

	public void setSquadMarketValue(String squadMarketValue) {
		this.squadMarketValue = squadMarketValue;
	}

	public String getCrestUrl() {
		return crestUrl;
	}

	public void setCrestUrl(String crestUrl) {
		this.crestUrl = crestUrl;
	}

	public String getTbTeamscol() {
		return tbTeamscol;
	}

	public void setTbTeamscol(String tbTeamscol) {
		this.tbTeamscol = tbTeamscol;
	}

}
