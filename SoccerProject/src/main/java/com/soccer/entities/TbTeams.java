package com.soccer.entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_teams")
@NamedQueries({ @NamedQuery(name = "TbTeams.findAll", query = "SELECT t FROM TbTeams t") })
public class TbTeams implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "shortName")
	private String shortName;
	
	@Column(name = "squadMarketValue")
	private String squadMarketValue;
	
	@Column(name = "crestUrl")
	private String crestUrl;
	
	@Column(name = "tb_teamscol")
	private String tbTeamscol;
	
	@JoinColumn(name = "competitionId", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private TbCompetitions tbCompetitions;

	public TbTeams() {
	}

	public TbTeams(Integer id) {
		this.id = id;
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

	public TbCompetitions getTbCompetitions() {
		return tbCompetitions;
	}

	public void setTbCompetitions(TbCompetitions tbCompetitions) {
		this.tbCompetitions = tbCompetitions;
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
		if (!(object instanceof TbTeams)) {
			return false;
		}
		TbTeams other = (TbTeams) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Football.TbTeams[ id=" + id + " ]";
	}

}
