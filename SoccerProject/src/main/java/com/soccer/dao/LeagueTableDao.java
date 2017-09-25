package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbLeaguetable;

public interface LeagueTableDao extends JpaRepository<TbLeaguetable, Long>{

}
