package com.soccer.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.Constants;
import com.soccer.commons.ResponseObject;
import com.soccer.dto.UserDTO;
import com.soccer.exception.UserNotFoundException;
import com.soccer.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject login(@RequestBody Map<String, Object> user, final HttpSession session,
			HttpServletRequest request) {
		ResponseObject responseObject = new ResponseObject();
		String userName = (String) user.get("userName");
		String password = (String) user.get("password");

		try {
			// set time to timeout for session
			session.setMaxInactiveInterval(900);
			UserDTO userDTO = userService.authentication(userName, password);

			if (userDTO != null) {
				session.setAttribute(Constants.SESSION_USER, userDTO);
				CommonFunction.addSession(userDTO.getUserName(), session);

				responseObject.setMessage(Constants.MESSAGE_LOGIN_SUCCESS);
				responseObject.setStatus(true);
				responseObject.setObject(userDTO);
			} else {
				responseObject.setMessage(Constants.MESSAGE_LOGIN_FAILD);
				responseObject.setStatus(false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_LOGIN_FAILD);
			responseObject.setStatus(false);
		}

		return responseObject;
	}

	/**
	 * remove current user from session
	 * 
	 * @param session:
	 *            content current user information
	 * @return void
	 */
	@RequestMapping(value = Constants.RQ_LOGOUT, method = RequestMethod.POST)
	@ResponseBody
	public void logout(final HttpSession session) {
		
		UserDTO userSession = CommonFunction.getCurrentUser(session);

		if (userSession == null) {
			throw new UserNotFoundException();
		}

		UserDTO currentUser = CommonFunction.getCurrentUser(session);
		if (currentUser != null) {
			session.removeAttribute(Constants.SESSION_USER);
			CommonFunction.removeSession(currentUser.getUserName());
		}

	}
}
