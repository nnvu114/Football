package com.soccer.controllers;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.Constants;
import com.soccer.commons.ObjectResponseTotal;
import com.soccer.commons.ResponseObject;
import com.soccer.dao.CategoryDao;
import com.soccer.dao.ImagesDao;
import com.soccer.dao.NewsDao;
import com.soccer.dao.VideoDao;
import com.soccer.dto.UserDTO;
import com.soccer.dto.news.NewsDTO;
import com.soccer.entities.News;
import com.soccer.entities.Videos;
import com.soccer.exception.UserNotFoundException;
import com.soccer.repositories.NewsReponsitory;
import com.soccer.services.NewsService;

@RestController
public class NewsController {

	@Autowired
	private NewsService newsService;

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private VideoDao videoDao;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ImagesDao imagesDao;

	@Autowired
	private CategoryDao catelogyDao;

	@Autowired
	private NewsReponsitory newsReponsitory;

	@RequestMapping("/news")
	@ResponseBody
	public ResponseObject getAllNews(@RequestParam("content") String content, @RequestParam("category") long category,
			@RequestParam("limit") int limit, @RequestParam("offset") int offset, final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();

		ObjectResponseTotal listNewsDTO = new ObjectResponseTotal();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			listNewsDTO.setTotal(newsReponsitory.getTotalListNews(content, category, limit, offset));
			listNewsDTO.setData(newsService.getAllHaveCondition(content, category, limit, offset));
			responseObject.setObject(listNewsDTO);
			return responseObject;
		} catch (Exception e) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			e.printStackTrace();
			return responseObject;
		}
	}

	@RequestMapping("/get-news-detail")
	@ResponseBody
	public NewsDTO getOneNews(@RequestParam("newsId") long newsId, final HttpSession session) {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		NewsDTO newsDTO = new NewsDTO();
		try {
			newsDTO = newsService.getOne(newsId);
			return newsDTO;
		} catch (Exception e) {
			e.printStackTrace();
			return newsDTO;
		}
	}

	@RequestMapping(value = "/insertNews", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertNews(@RequestBody Map<String, Object> objectNews, final HttpSession session) {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		String newsTitle = (String) objectNews.get("newstitle");
		String newsDescription = (String) objectNews.get("newsDescription");
		String descriptionCore = (String) objectNews.get("DescriptionCore");
		int priorityLevel = Integer.parseInt((String) objectNews.get("priorityLevel"));
		long categoryId = Long.parseLong((String) objectNews.get("category"));
		long imageId = Long.parseLong((String) objectNews.get("imageId"));
		int statusNews = Integer.parseInt((String) objectNews.get("statusNews"));
		String author = (String) objectNews.get("author");

		Date date = new Date();
		ResponseObject responseObject = new ResponseObject();

		try {
			News news = new News();
			news.setNewsTitle(newsTitle);
			news.setNewsDescription(newsDescription);
			news.setImagesId(imagesDao.getOne(imageId));
			news.setPriorityLevel(priorityLevel);
			news.setStatusNews(statusNews);
			news.setAuthor(author);
			news.setCategoryId(catelogyDao.getOne(categoryId));
			news.setCreateDate(date);
			news.setDescriptionCore(descriptionCore);
			responseObject.setMessage(Constants.MESSAGE_INSERT_SUCCESS);
			responseObject.setStatus(Constants.STATUS_INSERT_SUCCESS);
			responseObject.setObject(new NewsDTO(newsDao.save(news)));
			return responseObject;
		} catch (Exception e) {
			responseObject.setMessage(Constants.MESSAGE_INSERT_FAILD);
			responseObject.setStatus(Constants.STATUS_INSERT_FAILD);
			e.printStackTrace();
			return responseObject;
		}

	}

	@RequestMapping(value = "/updateNews", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateNews(@RequestBody Map<String, Object> objectNews, @RequestParam("newsId") long newsId,
			final HttpSession session) {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		String newsTitle = (String) objectNews.get("newstitle");
		String newsDescription = (String) objectNews.get("newsDescription");
		int priorityLevel = Integer.parseInt((String) objectNews.get("priorityLevel"));
		long categoryId = Long.parseLong((String) objectNews.get("category"));
		long imageId = Long.parseLong((String) objectNews.get("imageId"));
		int statusNews = Integer.parseInt((String) objectNews.get("statusNews"));
		String descriptionCore = (String) objectNews.get("DescriptionCore");
		String author = (String) objectNews.get("author");
		ResponseObject responseObject = new ResponseObject();
		try {
			News news = new News();
			news.setNewsId(newsId);
			news.setNewsTitle(newsTitle);
			news.setNewsDescription(newsDescription);
			news.setImagesId(imagesDao.getOne(imageId));
			news.setPriorityLevel(priorityLevel);
			news.setStatusNews(statusNews);
			news.setAuthor(author);
			news.setCategoryId(catelogyDao.getOne(categoryId));
			news.setCreateDate(new Date());
			news.setDescriptionCore(descriptionCore);
			responseObject.setMessage(Constants.MESSAGE_INSERT_SUCCESS);
			responseObject.setStatus(Constants.STATUS_INSERT_SUCCESS);
			newsDao.save(news);
			return responseObject;
		} catch (Exception e) {
			responseObject.setMessage(Constants.MESSAGE_INSERT_FAILD);
			responseObject.setStatus(Constants.STATUS_INSERT_FAILD);
			e.printStackTrace();
			return responseObject;
		}
	}

	@RequestMapping(value = "/deleteNews", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteNews(@RequestParam("newsId") long newsId, @RequestParam("videoId") long videoId,
			final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		if (newsId < 0) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_DELETE_DATA_FAILD);
		} else {
			try {
				// get path
//				String realPathtoUploads = request.getServletContext().getRealPath("/");
				Videos video = videoDao.findOne(videoId);
				if (video != null) {

					String pathDelete = request.getServletContext().getResource("/") + video.getLinkUpload();
					deleteFile(new File(pathDelete));
					newsDao.delete(newsId);

				} else {
					newsDao.delete(newsId);
				}
				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_DELETE_DATA_SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_DELETE_DATA_FAILD);
			}
		}
		return responseObject;
	}

	private void deleteFile(File file) {
		System.err.println(file + " pathDelete");
		boolean success = file.delete();
		if (success) {
			System.out.println(file.getAbsoluteFile() + " Deleted");
		} else {
			System.out.println(file.getAbsoluteFile() + " Deletion failed!!!");
		}
	}

	public static void main(String[] args) {
		try {

			File file = new File(
					"D:/Spring/SoccerProject/src/main/webapp/upload/videos_football/1503565965226-Yeu-Mot-Hoang-Tu-Hoang-Yen-Chibi.mp4");
			System.err.println(file.getPath());
			System.err.println(file.exists());
			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// --------------------USER-----------------------------------------//

	@RequestMapping("/get-list-news")
	@ResponseBody
	public ResponseObject getAllNews(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {

		ResponseObject responseObject = new ResponseObject();

		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			Pageable pageable = new PageRequest(offset / limit, limit, Direction.DESC, "newsId");
			responseObject.setObject(newsService.findAllNews(pageable));
			return responseObject;
		} catch (Exception e) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			e.printStackTrace();
			return responseObject;
		}
	}

	@RequestMapping("/get-news-by-priority")
	@ResponseBody
	public ResponseObject getNewsByPriorityLevel(@RequestParam("priorityLevel") int priorityLevel,
			@RequestParam("limit") int limit, @RequestParam("offset") int offset) {

		ResponseObject responseObject = new ResponseObject();

		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			Pageable pageable = new PageRequest(offset / limit, limit, Direction.DESC, "newsId");
			responseObject.setObject(newsService.findNewsByPriorityLevel(priorityLevel, pageable));
			return responseObject;
		} catch (Exception e) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			e.printStackTrace();
			return responseObject;
		}
	}

	@RequestMapping("/get-news-by-priority-other-level")
	@ResponseBody
	public ResponseObject getNewsByPriorityLevelOther(@RequestParam("priorityLevel") int priorityLevel,
			@RequestParam("limit") int limit, @RequestParam("limitNews") int limitNews,
			@RequestParam("offset") int offset) {

		ResponseObject responseObject = new ResponseObject();

		try {
			Pageable pageableLimit = new PageRequest(offset / limitNews, limitNews, Direction.DESC, "newsId");
			List<Long> listNewsId = newsService.findNewsIdByPriorityLevel(priorityLevel, pageableLimit);

			Pageable pageable = new PageRequest(offset / limit, limit, Direction.DESC, "newsId");
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);

			responseObject.setObject(newsService.findNewsByPriorityLevelOther(listNewsId, pageable));
			return responseObject;
		} catch (Exception e) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			e.printStackTrace();
			return responseObject;
		}
	}

	@RequestMapping("/find-one-news")
	@ResponseBody
	public ResponseObject findOneNews(@RequestParam("newsId") long newsId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(newsService.getOne(newsId));
			return responseObject;
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			return responseObject;
		}
	}

	@RequestMapping("/find-news-by-category")
	@ResponseBody
	public ResponseObject findNewsByCategory(@RequestParam("newsId") long newsId,
			@RequestParam("categoryId") long categoryId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			Pageable pageable = new PageRequest(0, 6, Direction.DESC, "newsId");

			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(newsService.findNewsByCategory(categoryId, newsId, pageable));
			return responseObject;
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			return responseObject;
		}
	}

	@RequestMapping("/find-news-by-category-id")
	@ResponseBody
	public ResponseObject findNewsByCategoryId(@RequestParam("categoryId") long categoryId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			Pageable pageable = new PageRequest(0, 20, Direction.DESC, "newsId");

			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(newsService.findNewsByCategoryId(categoryId, pageable));
			return responseObject;
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			return responseObject;
		}
	}
}
