package com.soccer.dto.tbcompetition;

import java.util.ArrayList;
import java.util.List;

import com.soccer.entities.TbCompetitions;
import com.soccer.entities.TbLeaguetable;
import com.soccer.entities.TbTeams;

public class CompetitionDTO {
	private Long id;

	private String caption;

	private String league;

	private String year;

	private Long currentMatchday;

	private Long numberOfMatchdays;

	private Long numberOfTeams;

	private Long numberOfGames;

	private String lastUpdated;

	private String linkSelf;

	private String linkTeams;

	private String linkFixtures;

	private String linkLeagueTable;

	private Integer statusDelete;

	private List<TeamDTO> tbTeamsList;

	private List<LeaguetableDTO> tbLeaguetableList;

	public CompetitionDTO(TbCompetitions competitions, Boolean checkStatus) {
		this.id = competitions.getId();
		this.caption = competitions.getCaption();
		this.league = competitions.getLeague();
		this.year = competitions.getYear();
		this.currentMatchday = competitions.getCurrentMatchday();
		this.numberOfMatchdays = competitions.getNumberOfMatchdays();
		this.numberOfTeams = competitions.getNumberOfTeams();
		this.numberOfGames = competitions.getNumberOfGames();
		this.lastUpdated = competitions.getLastUpdated();
		this.linkSelf = competitions.getLinkSelf();
		this.linkTeams = competitions.getLinkTeams();
		this.linkFixtures = competitions.getLinkFixtures();
		this.linkLeagueTable = competitions.getLinkLeagueTable();
	}

	public CompetitionDTO(TbCompetitions competitions) {
		this.id = competitions.getId();
		this.caption = competitions.getCaption();
		this.league = competitions.getLeague();
		this.year = competitions.getYear();
		this.currentMatchday = competitions.getCurrentMatchday();
		this.numberOfMatchdays = competitions.getNumberOfMatchdays();
		this.numberOfTeams = competitions.getNumberOfTeams();
		this.numberOfGames = competitions.getNumberOfGames();
		this.lastUpdated = competitions.getLastUpdated();
		this.linkSelf = competitions.getLinkSelf();
		this.linkTeams = competitions.getLinkTeams();
		this.linkFixtures = competitions.getLinkFixtures();
		this.linkLeagueTable = competitions.getLinkLeagueTable();
		if(!competitions.getTbTeamsList().isEmpty()){
			List<TeamDTO> teamsList = new ArrayList<>();
			List<TbTeams> listTbTeams = competitions.getTbTeamsList();
			for (TbTeams tbTeams : listTbTeams) {
				teamsList.add(new TeamDTO(tbTeams));
			}
			this.tbTeamsList = teamsList;
		}
		if (!competitions.getTbLeaguetableList().isEmpty()) {
			List<LeaguetableDTO> leaguetables = new ArrayList<>();
			if(!competitions.getTbLeaguetableList().isEmpty()){
				leaguetables.add(new LeaguetableDTO(competitions.getTbLeaguetableList().get(competitions.getTbLeaguetableList().size() - 1)));
			}
			this.tbLeaguetableList = leaguetables;
		}

	}

	public String getLinkSelf() {
		return linkSelf;
	}

	public void setLinkSelf(String linkSelf) {
		this.linkSelf = linkSelf;
	}

	public String getLinkTeams() {
		return linkTeams;
	}

	public void setLinkTeams(String linkTeams) {
		this.linkTeams = linkTeams;
	}

	public String getLinkFixtures() {
		return linkFixtures;
	}

	public void setLinkFixtures(String linkFixtures) {
		this.linkFixtures = linkFixtures;
	}

	public String getLinkLeagueTable() {
		return linkLeagueTable;
	}

	public void setLinkLeagueTable(String linkLeagueTable) {
		this.linkLeagueTable = linkLeagueTable;
	}

	public Integer getStatusDelete() {
		return statusDelete;
	}

	public void setStatusDelete(Integer statusDelete) {
		this.statusDelete = statusDelete;
	}

	public List<TeamDTO> getTbTeamsList() {
		return tbTeamsList;
	}

	public void setTbTeamsList(List<TeamDTO> tbTeamsList) {
		this.tbTeamsList = tbTeamsList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Long currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public Long getNumberOfMatchdays() {
		return numberOfMatchdays;
	}

	public void setNumberOfMatchdays(Long numberOfMatchdays) {
		this.numberOfMatchdays = numberOfMatchdays;
	}

	public Long getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(Long numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public Long getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(Long numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<LeaguetableDTO> getTbLeaguetableList() {
		return tbLeaguetableList;
	}

	public void setTbLeaguetableList(List<LeaguetableDTO> tbLeaguetableList) {
		this.tbLeaguetableList = tbLeaguetableList;
	}

}
