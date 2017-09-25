package com.soccer.services;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.dao.UserDao;
import com.soccer.dto.UserDTO;
import com.soccer.entities.Users;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DozerBeanMapper dozerBeanMapper;

	public UserDTO authentication(String userName, String password) {
		Users users = userDao.authentication(userName, password);
		
		UserDTO userDTO = dozerBeanMapper.map(users, UserDTO.class);

		return userDTO;
	}
}
