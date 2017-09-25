package com.soccer.dto.matchdetail;

import com.soccer.entities.Tbmatchdetail;

public class MatchDetailDTO {
	private Long id;

	private String titleMatch;

	private String description;

	public MatchDetailDTO(Tbmatchdetail matchDetail) {
		if (matchDetail != null) {
			this.id = matchDetail.getId();
			this.titleMatch = matchDetail.getTitleMatch();
			this.description = matchDetail.getDescription();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitleMatch() {
		return titleMatch;
	}

	public void setTitleMatch(String titleMatch) {
		this.titleMatch = titleMatch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
