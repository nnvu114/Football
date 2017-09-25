package com.soccer.controllers;

import java.io.FileReader;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.Constants;
import com.soccer.commons.GetDataFromOtherWeb;
import com.soccer.commons.ResponseObject;
import com.soccer.dao.AwayDao;
import com.soccer.dao.CompetitionsDao;
import com.soccer.dao.FixturesDao;
import com.soccer.dao.FixturesDetailDao;
import com.soccer.dao.HalftimeDao;
import com.soccer.dao.HomeDao;
import com.soccer.dao.LeagueTableDao;
import com.soccer.dao.ResultDao;
import com.soccer.dao.StandingDao;
import com.soccer.dao.TeamsDao;
import com.soccer.dto.UserDTO;
import com.soccer.entities.TbAway;
import com.soccer.entities.TbCompetitions;
import com.soccer.entities.TbFixtures;
import com.soccer.entities.TbFixturesDetail;
import com.soccer.entities.TbHalftime;
import com.soccer.entities.TbHome;
import com.soccer.entities.TbLeaguetable;
import com.soccer.entities.TbResult;
import com.soccer.entities.TbStanding;
import com.soccer.entities.TbTeams;
import com.soccer.exception.UserNotFoundException;
import com.soccer.services.FixturesService;

@RestController
public class ImportDataController {

	@Autowired
	private CompetitionsDao competitionsDao;

	@Autowired
	private LeagueTableDao leagueTableDao;

	@Autowired
	private StandingDao standingDao;

	@Autowired
	private HomeDao homeDao;

	@Autowired
	private AwayDao awayDao;

	@Autowired
	private FixturesDao fixturesDao;

	@Autowired
	private FixturesDetailDao fixturesDetailDao;

	@Autowired
	private HalftimeDao halftimeDao;

	@Autowired
	private FixturesService fixturesService;

	@Autowired
	private ResultDao resultDao;

	@Autowired
	private TeamsDao teamsDao;

	@RequestMapping("/import-competition")
	@ResponseBody
	public void importCompetition() {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("D:\\Spring\\index.json"));

			JSONArray jsonArray = (JSONArray) obj;
			competitionsDao.deleteAll();
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				TbCompetitions tbCompetitions = new TbCompetitions();
				tbCompetitions.setId((Long) jsonObject.get("id"));
				tbCompetitions.setCaption((String) jsonObject.get("caption"));
				tbCompetitions.setLeague((String) jsonObject.get("league"));
				tbCompetitions.setYear((String) jsonObject.get("year"));
				tbCompetitions.setCurrentMatchday((Long) jsonObject.get("currentMatchday"));
				tbCompetitions.setNumberOfMatchdays((Long) jsonObject.get("numberOfMatchdays"));
				tbCompetitions.setNumberOfTeams((Long) jsonObject.get("numberOfTeams"));
				tbCompetitions.setNumberOfGames((Long) jsonObject.get("numberOfGames"));
				tbCompetitions.setLastUpdated((String) jsonObject.get("lastUpdated"));

				JSONObject objectLinks = (JSONObject) jsonObject.get("links");
				JSONObject objectSelf = (JSONObject) objectLinks.get("self");
				tbCompetitions.setLinkSelf((String) objectSelf.get("href"));

				JSONObject objectTeams = (JSONObject) objectLinks.get("teams");
				tbCompetitions.setLinkTeams((String) objectTeams.get("href"));

				JSONObject objectFixtures = (JSONObject) objectLinks.get("fixtures");
				tbCompetitions.setLinkFixtures((String) objectFixtures.get("href"));

				JSONObject objectLeagueTable = (JSONObject) objectLinks.get("leagueTable");
				tbCompetitions.setLinkLeagueTable((String) objectLeagueTable.get("href"));

				tbCompetitions.setStatusDelete(0);
				tbCompetitions.setTbLeaguetableList(new ArrayList<>());
				Long competitionId = competitionsDao.save(tbCompetitions).getId();
				if ((Long) jsonObject.get("id") == 445 || (Long) jsonObject.get("id") == 452) {
					importleagueTable(competitionId, jsonObject.get("id").toString());
					importfixtures(competitionId, jsonObject.get("id").toString());
					importTeams(competitionId, jsonObject.get("id").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	@RequestMapping("/import-fixtures")
	@ResponseBody
	public ResponseObject importFixtures(@RequestParam(value = "competitionId") Long competition,
			@RequestParam(value = "linkUpdate") String linkUpdate, final HttpSession session) {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}
		ResponseObject responseObject = new ResponseObject();
		try {
			importfixtures(competition, linkUpdate);
			responseObject.setMessage(Constants.MESSAGE_UPDATE_SUCCESS);
			responseObject.setStatus(true);
		} catch (Exception e) {
			responseObject.setMessage(Constants.MESSAGE_UPDATE_FAILD);
			responseObject.setStatus(false);
			e.printStackTrace();
		}
		return responseObject;
	}

	@RequestMapping("/import-league-table")
	@ResponseBody
	public ResponseObject importLeagueTable(@RequestParam(value = "competitionId") Long competition,
			@RequestParam(value = "linkUpdate") String linkUpdate, final HttpSession session) {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}
		ResponseObject responseObject = new ResponseObject();
		try {
			importleagueTable(competition, linkUpdate);
			responseObject.setMessage(Constants.MESSAGE_UPDATE_SUCCESS);
			responseObject.setStatus(true);
		} catch (Exception e) {
			responseObject.setMessage(Constants.MESSAGE_UPDATE_FAILD);
			responseObject.setStatus(false);
			e.printStackTrace();
		}
		return responseObject;
	}

	public void importleagueTable(Long competitionId, String fileJson) {
		try {
			org.json.JSONObject json = GetDataFromOtherWeb.readJsonObjectFromUrl(fileJson);

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json.toString().replaceAll("_links", "links"));
//			leagueTableDao.delete(competitionId);
			JSONObject jsonObject = (JSONObject) obj;
			TbLeaguetable tbLeaguetable = new TbLeaguetable();
			tbLeaguetable.setId(competitionId);
			tbLeaguetable.setLeagueCaption((String) jsonObject.get("leagueCaption"));
			tbLeaguetable.setMatchday((Long) jsonObject.get("matchday"));
			tbLeaguetable.setTbCompetitions(competitionsDao.findOne(competitionId));
			// insert league table
			long leaguetableId = leagueTableDao.save(tbLeaguetable).getId();

			// update standing
			JSONArray msgStanding = (JSONArray) jsonObject.get("standing");
			for (Object object : msgStanding) {
				JSONObject objectStanding = (JSONObject) object;
				TbStanding tbStanding = new TbStanding();
				tbStanding.setPosition((Long) objectStanding.get("position"));
				tbStanding.setTeamName((String) objectStanding.get("teamName"));
				tbStanding.setCrestURI((String) objectStanding.get("crestURI"));
				tbStanding.setPlayedGames((Long) objectStanding.get("playedGames"));
				tbStanding.setPoints((Long) objectStanding.get("points"));
				tbStanding.setGoals((Long) objectStanding.get("goals"));
				tbStanding.setGoalsAgainst((Long) objectStanding.get("goalsAgainst"));
				tbStanding.setGoalDifference((Long) objectStanding.get("goalDifference"));
				tbStanding.setWins((Long) objectStanding.get("wins"));
				tbStanding.setDraws((Long) objectStanding.get("draws"));
				tbStanding.setLosses((Long) objectStanding.get("losses"));

				// set home
				JSONObject homeObject = (JSONObject) objectStanding.get("home");
				if (homeObject != null) {
					TbHome home = new TbHome();
					home.setGoals((Long) homeObject.get("goals"));
					home.setGoalsAgainst((Long) homeObject.get("goalsAgainst"));
					home.setWins((Long) homeObject.get("wins"));
					home.setDraws((Long) homeObject.get("draws"));
					home.setLosses((Long) homeObject.get("losses"));
					// get home ID
					Long homeId = homeDao.save(home).getId();
					tbStanding.setTbHome(homeDao.findOne(homeId));
				}

				JSONObject awayObject = (JSONObject) objectStanding.get("away");
				if (awayObject != null) {
					TbAway away = new TbAway();
					away.setGoals((Long) awayObject.get("goals"));
					away.setGoalsAgainst((Long) awayObject.get("goalsAgainst"));
					away.setWins((Long) awayObject.get("wins"));
					away.setDraws((Long) awayObject.get("draws"));
					away.setLosses((Long) awayObject.get("losses"));
					// get home ID
					Long awayId = awayDao.save(away).getId();
					tbStanding.setTbAway(awayDao.findOne(awayId));
				}

				tbStanding.setTbLeaguetable(leagueTableDao.findOne(leaguetableId));
				standingDao.save(tbStanding);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	public void importfixtures(Long competitionId, String fileJson) {
		try {
			org.json.JSONObject json = GetDataFromOtherWeb.readJsonObjectFromUrl(fileJson);

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json.toString().replaceAll("_links", "links"));
			
			TbCompetitions competitions = competitionsDao.findOne(competitionId);
			
			if(!competitions.getTbFixturesList().isEmpty()){
				for (TbFixtures fixture : competitions.getTbFixturesList()) {
					fixturesDetailDao.deleteFixture(fixture.getId());
				}
				fixturesService.deleteFixture(competitionId);
			}
			
			JSONObject jsonObject = (JSONObject) obj;
			TbFixtures fixtures = new TbFixtures();
			fixtures.setCount((Long) jsonObject.get("count"));
			JSONObject linksObject = (JSONObject) jsonObject.get("links");
			JSONObject selfObject = (JSONObject) linksObject.get("self");
			fixtures.setSelf((String) selfObject.get("href"));
			fixtures.setTbCompetitions(competitions);
			long fixturesId = fixturesDao.save(fixtures).getId();

			// import fixtureDetail
			JSONArray arrayFixtures = (JSONArray) jsonObject.get("fixtures");
			for (Object object : arrayFixtures) {
				JSONObject objectFixturesDetail = (JSONObject) object;
				TbFixturesDetail tbFixturesDetail = new TbFixturesDetail();
				tbFixturesDetail.setDate((String) objectFixturesDetail.get("date"));
				tbFixturesDetail.setStatus((String) objectFixturesDetail.get("status"));
				tbFixturesDetail.setMatchday((Long) objectFixturesDetail.get("matchday"));
				tbFixturesDetail.setHomeTeamName((String) objectFixturesDetail.get("homeTeamName"));
				tbFixturesDetail.setAwayTeamName((String) objectFixturesDetail.get("awayTeamName"));

				JSONObject objectResult = (JSONObject) objectFixturesDetail.get("result");
				if (objectResult != null) {
					TbResult result = new TbResult();
					result.setGoalsHomeTeam((Long) objectResult.get("goalsHomeTeam"));
					result.setGoalsAwayTeam((Long) objectResult.get("goalsAwayTeam"));
					JSONObject objectHalfTime = (JSONObject) objectResult.get("halfTime");
					if (objectHalfTime != null) {
						TbHalftime halfTime = new TbHalftime();
						halfTime.setGoalsAwayTeam((Long) objectHalfTime.get("awayTeamName"));
						halfTime.setGoalsHomeTeam((Long) objectHalfTime.get("goalsHomeTeam"));
						// save half time
						long halfTimeId = halftimeDao.save(halfTime).getId();
						result.setTbHalftime(halftimeDao.findOne(halfTimeId));
					}

					// save result
					long resultId = resultDao.save(result).getId();
					tbFixturesDetail.setTbResult(resultDao.findOne(resultId));
				}
				tbFixturesDetail.setTbFixtures(fixturesDao.findOne(fixturesId));
				fixturesDetailDao.save(tbFixturesDetail);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void importTeams(Long competitionId, String fileJson) {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("D:\\Spring\\JSON\\teams\\" + fileJson + ".json"));
			JSONObject jsonObject = (JSONObject) obj;

			// import fixtureDetail
			JSONArray arrayTeams = (JSONArray) jsonObject.get("teams");
			for (Object object : arrayTeams) {
				JSONObject objectTeamsDetail = (JSONObject) object;
				TbTeams team = new TbTeams();
				team.setName((String) objectTeamsDetail.get("name"));
				team.setCrestUrl((String) objectTeamsDetail.get("crestUrl"));
				team.setCode((String) objectTeamsDetail.get("code"));
				team.setShortName((String) objectTeamsDetail.get("shortName"));
				team.setSquadMarketValue((String) objectTeamsDetail.get("squadMarketValue"));
				team.setTbCompetitions(competitionsDao.findOne(competitionId));
				teamsDao.save(team);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
