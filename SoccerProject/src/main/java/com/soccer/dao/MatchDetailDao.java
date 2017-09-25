package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.Tbmatchdetail;

public interface MatchDetailDao extends JpaRepository<Tbmatchdetail, Long> {

}
