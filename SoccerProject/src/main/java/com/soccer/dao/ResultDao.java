package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbResult;

public interface ResultDao extends JpaRepository<TbResult, Long> {

}
