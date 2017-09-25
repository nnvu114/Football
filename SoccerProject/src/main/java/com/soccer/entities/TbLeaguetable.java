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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_leaguetable")
public class TbLeaguetable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Column(name = "leagueCaption")
	private String leagueCaption;

	@Column(name = "matchday")
	private Long matchday;

	@JoinColumn(name = "comperitionsId", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private TbCompetitions tbCompetitions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tbLeaguetable", fetch = FetchType.LAZY)
	private List<TbStanding> tbStandingList;

	public TbLeaguetable() {
	}

	public TbLeaguetable(Long id) {
		this.id = id;
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

	public TbCompetitions getTbCompetitions() {
		return tbCompetitions;
	}

	public void setTbCompetitions(TbCompetitions tbCompetitions) {
		this.tbCompetitions = tbCompetitions;
	}

	public List<TbStanding> getTbStandingList() {
		return tbStandingList;
	}

	public void setTbStandingList(List<TbStanding> tbStandingList) {
		this.tbStandingList = tbStandingList;
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
		if (!(object instanceof TbLeaguetable)) {
			return false;
		}
		TbLeaguetable other = (TbLeaguetable) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Football.TbLeaguetable[ id=" + id + " ]";
	}

}
