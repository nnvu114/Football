package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccer.entities.TbHome;

public interface HomeDao extends JpaRepository<TbHome, Long> {

}
