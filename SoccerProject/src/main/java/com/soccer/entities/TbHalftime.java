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
@Table(name = "tb_halftime")
@NamedQueries({
    @NamedQuery(name = "TbHalftime.findAll", query = "SELECT t FROM TbHalftime t")})
public class TbHalftime implements Serializable {
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
    @OneToMany(mappedBy = "tbHalftime", fetch = FetchType.LAZY)
    private List<TbResult> tbResultList;

    public TbHalftime() {
    }

    public TbHalftime(Long id) {
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

    public List<TbResult> getTbResultList() {
        return tbResultList;
    }

    public void setTbResultList(List<TbResult> tbResultList) {
        this.tbResultList = tbResultList;
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
        if (!(object instanceof TbHalftime)) {
            return false;
        }
        TbHalftime other = (TbHalftime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Football.TbHalftime[ id=" + id + " ]";
    }
    
}
