/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_fixtures_detail")
@NamedQueries({
    @NamedQuery(name = "TbFixturesDetail.findAll", query = "SELECT t FROM TbFixturesDetail t")})
public class TbFixturesDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "matchday")
    private Long matchday;
    
    @Column(name = "homeTeamName")
    private String homeTeamName;
    
    @Column(name = "awayTeamName")
    private String awayTeamName;
    
    @Column(name = "odds")
    private String odds;
    
    @Column(name = "statusMatchDetail")
    private Integer statusMatchDetail;
    
    @OneToOne(optional = true, orphanRemoval=true, cascade = CascadeType.ALL, mappedBy = "tbFixturesDetail", fetch = FetchType.LAZY)
    private Tbmatchdetail tbmatchdetail;
    
    @JoinColumn(name = "fixtureId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private TbFixtures tbFixtures;
    
    @JoinColumn(name = "resultId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TbResult tbResult;

    public TbFixturesDetail() {
    }

    public TbFixturesDetail(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMatchday() {
        return matchday;
    }

    public void setMatchday(Long matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public String getOdds() {
        return odds;
    }

    public void setOdds(String odds) {
        this.odds = odds;
    }

    public TbResult getTbResult() {
        return tbResult;
    }

    public void setTbResult(TbResult tbResult) {
        this.tbResult = tbResult;
    }

    public TbFixtures getTbFixtures() {
		return tbFixtures;
	}

	public void setTbFixtures(TbFixtures tbFixtures) {
		this.tbFixtures = tbFixtures;
	}

	public Integer getStatusMatchDetail() {
		return statusMatchDetail;
	}

	public void setStatusMatchDetail(Integer statusMatchDetail) {
		this.statusMatchDetail = statusMatchDetail;
	}

	public Tbmatchdetail getTbmatchdetail() {
		return tbmatchdetail;
	}

	public void setTbmatchdetail(Tbmatchdetail tbmatchdetail) {
		this.tbmatchdetail = tbmatchdetail;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbFixturesDetail)) {
            return false;
        }
        TbFixturesDetail other = (TbFixturesDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Football.TbFixturesDetail[ id=" + id + " ]";
    }
    
}
