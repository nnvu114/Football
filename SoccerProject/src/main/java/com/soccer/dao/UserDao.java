package com.soccer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.soccer.entities.Users;

public interface UserDao extends JpaRepository<Users, Long> {

	@Query("Select u From Users u WHERE u.userName = ?1 AND u.password = ?2")
	public Users authentication(String userName, String password);
}
