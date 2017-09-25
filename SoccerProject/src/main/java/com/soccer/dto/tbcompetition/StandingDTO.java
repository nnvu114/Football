package com.soccer.dto.tbcompetition;

import com.soccer.entities.TbStanding;

public class StandingDTO {

	private Long id;

	private Long position;

	private String teamName;

	private String crestURI;

	private Long playedGames;

	private Long points;

	private Long goals;

	private Long goalsAgainst;

	private Long goalDifference;

	private Long wins;

	private Long draws;

	private Long losses;

	private HomeDTO tbHome;

	private AwayDTO tbAway;

	public StandingDTO() {
	}

	public StandingDTO(TbStanding standing) {
		this.id = standing.getId();
		this.position = standing.getPosition();
		this.teamName = standing.getTeamName();
		this.crestURI = standing.getCrestURI();
		this.playedGames = standing.getPlayedGames();
		this.points = standing.getPoints();
		this.goals = standing.getGoals();
		this.goalsAgainst = standing.getGoalsAgainst();
		this.goalDifference = standing.getGoalDifference();
		this.wins = standing.getWins();
		this.draws = standing.getDraws();
		this.losses = standing.getLosses();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPosition() {
		return position;
	}

	public void setPosition(Long position) {
		this.position = position;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCrestURI() {
		return crestURI;
	}

	public void setCrestURI(String crestURI) {
		this.crestURI = crestURI;
	}

	public Long getPlayedGames() {
		return playedGames;
	}

	public void setPlayedGames(Long playedGames) {
		this.playedGames = playedGames;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Long getGoals() {
		return goals;
	}

	public void setGoals(Long goals) {
		this.goals = goals;
	}

	public Long getGoalsAgainst() {
		return goalsAgainst;
	}

	public void setGoalsAgainst(Long goalsAgainst) {
		this.goalsAgainst = goalsAgainst;
	}

	public Long getGoalDifference() {
		return goalDifference;
	}

	public void setGoalDifference(Long goalDifference) {
		this.goalDifference = goalDifference;
	}

	public Long getWins() {
		return wins;
	}

	public void setWins(Long wins) {
		this.wins = wins;
	}

	public Long getDraws() {
		return draws;
	}

	public void setDraws(Long draws) {
		this.draws = draws;
	}

	public Long getLosses() {
		return losses;
	}

	public void setLosses(Long losses) {
		this.losses = losses;
	}

	public HomeDTO getTbHome() {
		return tbHome;
	}

	public void setTbHome(HomeDTO tbHome) {
		this.tbHome = tbHome;
	}

	public AwayDTO getTbAway() {
		return tbAway;
	}

	public void setTbAway(AwayDTO tbAway) {
		this.tbAway = tbAway;
	}

}
