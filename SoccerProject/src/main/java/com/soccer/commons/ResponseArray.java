package com.soccer.commons;

import java.util.List;

public class ResponseArray {
	private boolean status;

	private String message;

	private List<Object> object;

	public ResponseArray() {
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getArrayObject() {
		return object;
	}

	public void setArrayObject(List<Object> object) {
		this.object = object;
	}
}
