package com.soccer.dto.tbfixtures;

import java.util.ArrayList;
import java.util.List;

import com.soccer.dto.tbcompetition.TeamDTO;
import com.soccer.entities.TbFixtures;
import com.soccer.entities.TbFixturesDetail;
import com.soccer.entities.TbTeams;

public class TbFixturesDTO {

	private Long id;

	private Long count;

	private String self;

	private List<TbFixturesDetailDTO> tbFixturesDetailList;
	
	private List<TeamDTO> listTeamDTO;

	public TbFixturesDTO() {
	}

	public TbFixturesDTO(TbFixtures fixtures) {
		if (fixtures != null) {
			this.id = fixtures.getId();
			this.count = fixtures.getCount();
			this.self = fixtures.getSelf();
			if (!fixtures.getTbFixturesDetailList().isEmpty()) {
				List<TbFixturesDetailDTO> tbFixturesDetails = new ArrayList<>();
				for (TbFixturesDetail fixturesDetail : fixtures.getTbFixturesDetailList()) {
					tbFixturesDetails.add(new TbFixturesDetailDTO(fixturesDetail));
				}
				this.tbFixturesDetailList = tbFixturesDetails;
			}
			
			if(fixtures.getTbCompetitions() != null){
				if(!fixtures.getTbCompetitions().getTbTeamsList().isEmpty()){
					List<TeamDTO> listTeams = new ArrayList<>();
					List<TbTeams> listTbTeams = fixtures.getTbCompetitions().getTbTeamsList();
					for (TbTeams tbTeams : listTbTeams) {
						listTeams.add(new TeamDTO(tbTeams));
					}
					this.listTeamDTO = listTeams;
				}
			}
		}
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public List<TbFixturesDetailDTO> getTbFixturesDetailList() {
		return tbFixturesDetailList;
	}

	public void setTbFixturesDetailList(List<TbFixturesDetailDTO> tbFixturesDetailList) {
		this.tbFixturesDetailList = tbFixturesDetailList;
	}
}
