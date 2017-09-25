package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbHalftime;

public interface HalftimeDao extends JpaRepository<TbHalftime, Long> {

}
