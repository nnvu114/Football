package com.soccer.commons;

import java.util.List;

public class ObjectResponseTotal {
	private List<Object> data;

	private Long total;

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> list) {
		this.data = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}