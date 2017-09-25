/**
 * 
 */
package com.soccer.commons;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.soccer.dto.UserDTO;

public class CommonFunction {

	public static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();

	public static UserDTO getCurrentUser(HttpSession session) {
		if (session != null && session.getAttribute(Constants.SESSION_USER) != null) {
			UserDTO currentUser = (UserDTO) session.getAttribute(Constants.SESSION_USER);
			if (sessions.containsKey(currentUser.getUserName())) {
				return currentUser;
			}
		}

		return null;
	}

	public static void removeSession(String userName) {
		sessions.remove(userName);
	}
	
	public static void addSession(String userName, HttpSession session) {
        sessions.put(userName, session);

    }
}
