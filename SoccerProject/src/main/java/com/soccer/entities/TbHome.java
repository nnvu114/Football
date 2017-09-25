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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_home")
@NamedQueries({
    @NamedQuery(name = "TbHome.findAll", query = "SELECT t FROM TbHome t")})
public class TbHome implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "goals")
    private Long goals;
    
    @Column(name = "goalsAgainst")
    private Long goalsAgainst;
    
    @Column(name = "wins")
    private Long wins;
    
    @Column(name = "draws")
    private Long draws;
    
    @Column(name = "losses")
    private Long losses;
    
    @OneToMany(mappedBy = "tbHome", fetch = FetchType.LAZY)
    private List<TbStanding> tbStandingList;

    public TbHome() {
    }

    public TbHome(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbHome)) {
            return false;
        }
        TbHome other = (TbHome) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Football.TbHome[ id=" + id + " ]";
    }
    
}
