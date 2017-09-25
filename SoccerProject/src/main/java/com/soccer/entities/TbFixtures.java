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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_fixtures")
@NamedQueries({ @NamedQuery(name = "TbFixtures.findAll", query = "SELECT t FROM TbFixtures t") })
public class TbFixtures implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;

	@Column(name = "count")
	private Long count;

	@Column(name = "self")
	private String self;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "tbFixtures", fetch = FetchType.LAZY)
	private List<TbFixturesDetail> tbFixturesDetailList;

	@JoinColumn(name = "competitionId", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private TbCompetitions tbCompetitions;

	public TbFixtures() {
	}

	public TbFixtures(Long id) {
		this.id = id;
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

	public TbCompetitions getTbCompetitions() {
		return tbCompetitions;
	}

	public void setTbCompetitions(TbCompetitions tbCompetitions) {
		this.tbCompetitions = tbCompetitions;
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
		if (!(object instanceof TbFixtures)) {
			return false;
		}
		TbFixtures other = (TbFixtures) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Football.TbFixtures[ id=" + id + " ]";
	}

}
