package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbTeams;

public interface TeamsDao extends JpaRepository<TbTeams, Long> {

}
