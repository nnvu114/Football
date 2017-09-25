package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbAway;

public interface AwayDao extends JpaRepository<TbAway, Long> {

}
