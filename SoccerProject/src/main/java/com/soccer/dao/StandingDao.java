package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbStanding;

public interface StandingDao extends JpaRepository<TbStanding, Long> {
}
