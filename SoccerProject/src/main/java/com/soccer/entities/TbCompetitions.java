/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_competitions")
public class TbCompetitions implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Basic(optional = false)
	@Column(name = "caption")
	private String caption;

	@Column(name = "league")
	private String league;

	@Column(name = "year")
	private String year;

	@Column(name = "currentMatchday")
	private Long currentMatchday;

	@Column(name = "numberOfMatchdays")
	private Long numberOfMatchdays;

	@Column(name = "numberOfTeams")
	private Long numberOfTeams;

	@Column(name = "numberOfGames")
	private Long numberOfGames;

	@Column(name = "lastUpdated")
	private String lastUpdated;

	@Lob
	@Column(name = "linkSelf")
	private String linkSelf;

	@Lob
	@Column(name = "linkTeams")
	private String linkTeams;

	@Lob
	@Column(name = "linkFixtures")
	private String linkFixtures;

	@Lob
	@Column(name = "linkLeagueTable")
	private String linkLeagueTable;

	@Column(name = "statusDelete")
	private Integer statusDelete;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbCompetitions", fetch = FetchType.LAZY)
	private List<TbTeams> tbTeamsList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbCompetitions", fetch = FetchType.LAZY)
	private List<TbFixtures> tbFixturesList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbCompetitions", fetch = FetchType.LAZY)
	private List<TbLeaguetable> tbLeaguetableList;

	public TbCompetitions() {
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


	public List<TbTeams> getTbTeamsList() {
		return tbTeamsList;
	}


	public void setTbTeamsList(List<TbTeams> tbTeamsList) {
		this.tbTeamsList = tbTeamsList;
	}


	public TbCompetitions(Long id) {
		this.id = id;
	}

	public TbCompetitions(Long id, String caption) {
		this.id = id;
		this.caption = caption;
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

	public List<TbFixtures> getTbFixturesList() {
		return tbFixturesList;
	}

	public void setTbFixturesList(List<TbFixtures> tbFixturesList) {
		this.tbFixturesList = tbFixturesList;
	}

	public List<TbLeaguetable> getTbLeaguetableList() {
		return tbLeaguetableList;
	}

	public void setTbLeaguetableList(List<TbLeaguetable> tbLeaguetableList) {
		this.tbLeaguetableList = tbLeaguetableList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TbCompetitions)) {
			return false;
		}
		TbCompetitions other = (TbCompetitions) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Football.TbCompetitions[ id=" + id + " ]";
	}

}
