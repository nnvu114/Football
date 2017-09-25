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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author quancd.ht
 */
@Entity
@Table(name = "tbmatchdetail")
@NamedQueries({
    @NamedQuery(name = "Tbmatchdetail.findAll", query = "SELECT t FROM Tbmatchdetail t")})
public class Tbmatchdetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    
    @Lob
    @Column(name = "titleMatch")
    private String titleMatch;
    
    @Lob
    @Column(name = "Description")
    private String description;
    
    @JoinColumn(name = "Id", referencedColumnName = "id", insertable = false, updatable = true)
    @OneToOne(optional = true,orphanRemoval=false,fetch = FetchType.LAZY)
    private TbFixturesDetail tbFixturesDetail;

    public Tbmatchdetail() {
    }

    public Tbmatchdetail(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleMatch() {
        return titleMatch;
    }

    public void setTitleMatch(String titleMatch) {
        this.titleMatch = titleMatch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TbFixturesDetail getTbFixturesDetail() {
        return tbFixturesDetail;
    }

    public void setTbFixturesDetail(TbFixturesDetail tbFixturesDetail) {
        this.tbFixturesDetail = tbFixturesDetail;
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
        if (!(object instanceof Tbmatchdetail)) {
            return false;
        }
        Tbmatchdetail other = (Tbmatchdetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Football.Tbmatchdetail[ id=" + id + " ]";
    }
  }
