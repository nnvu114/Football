/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soccer.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tb_standing")
@NamedQueries({
    @NamedQuery(name = "TbStanding.findAll", query = "SELECT t FROM TbStanding t")})
public class TbStanding implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "position")
    private Long position;
    
    @Column(name = "teamName")
    private String teamName;
    
    @Lob
    @Column(name = "crestURI")
    private String crestURI;
    
    @Column(name = "playedGames")
    private Long playedGames;
    
    @Column(name = "points")
    private Long points;
    
    @Column(name = "goals")
    private Long goals;
    
    @Column(name = "goalsAgainst")
    private Long goalsAgainst;
    
    @Column(name = "goalDifference")
    private Long goalDifference;
    
    @Column(name = "wins")
    private Long wins;
    
    @Column(name = "draws")
    private Long draws;
    
    @Column(name = "losses")
    private Long losses;
    
    @JoinColumn(name = "leaguetableId", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TbLeaguetable tbLeaguetable;
    @JoinColumn(name = "homeId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TbHome tbHome;
    @JoinColumn(name = "awayId", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TbAway tbAway;

    public TbStanding() {
    }

    public TbStanding(Long id) {
        this.id = id;
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

    public TbLeaguetable getTbLeaguetable() {
        return tbLeaguetable;
    }

    public void setTbLeaguetable(TbLeaguetable tbLeaguetable) {
        this.tbLeaguetable = tbLeaguetable;
    }

    public TbHome getTbHome() {
        return tbHome;
    }

    public void setTbHome(TbHome tbHome) {
        this.tbHome = tbHome;
    }

    public TbAway getTbAway() {
        return tbAway;
    }

    public void setTbAway(TbAway tbAway) {
        this.tbAway = tbAway;
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
        if (!(object instanceof TbStanding)) {
            return false;
        }
        TbStanding other = (TbStanding) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Football.TbStanding[ id=" + id + " ]";
    }
    
}
