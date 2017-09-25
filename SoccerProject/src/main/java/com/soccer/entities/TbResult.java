/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_result")
@NamedQueries({ @NamedQuery(name = "TbResult.findAll", query = "SELECT t FROM TbResult t") })
public class TbResult implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Column(name = "goalsHomeTeam")
	private Long goalsHomeTeam;
	@Column(name = "goalsAwayTeam")
	private Long goalsAwayTeam;
	@JoinColumn(name = "halfTimeId", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private TbHalftime tbHalftime;
	@OneToMany(mappedBy = "tbResult", fetch = FetchType.LAZY)
	private List<TbFixturesDetail> tbFixturesDetailList;

	public TbResult() {
	}

	public TbResult(Long id) {
		this.id = id;
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

	public TbHalftime getTbHalftime() {
		return tbHalftime;
	}

	public void setTbHalftime(TbHalftime tbHalftime) {
		this.tbHalftime = tbHalftime;
	}

	public List<TbFixturesDetail> getTbFixturesDetailList() {
		return tbFixturesDetailList;
	}

	public void setTbFixturesDetailList(List<TbFixturesDetail> tbFixturesDetailList) {
		this.tbFixturesDetailList = tbFixturesDetailList;
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
		if (!(object instanceof TbResult)) {
			return false;
		}
		TbResult other = (TbResult) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Football.TbResult[ id=" + id + " ]";
	}

}
