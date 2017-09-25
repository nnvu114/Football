package com.soccer.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.Constants;
import com.soccer.commons.ObjectResponseTotal;
import com.soccer.commons.ResponseObject;
import com.soccer.dao.CategoryDao;
import com.soccer.dao.VideoDao;
import com.soccer.dto.UserDTO;
import com.soccer.dto.video.VideoDTO;
import com.soccer.entities.Categories;
import com.soccer.entities.Videos;
import com.soccer.exception.UserNotFoundException;
import com.soccer.services.NewsService;
import com.soccer.services.VideoService;

@RestController
public class VideoController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private VideoDao videoDao;

	@Autowired
	private VideoService videoService;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "/find-all-videos", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject getListVideos(@RequestParam("videoTitle") String videoTitle,
			@RequestParam("categoryId") long categoryId, @RequestParam("limit") int limit,
			@RequestParam("offset") int offset, final HttpSession session) {

		UserDTO userSession = CommonFunction.getCurrentUser(session);

		if (userSession == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		ObjectResponseTotal ObjectResponseTotal = new ObjectResponseTotal();
		try {
			if (videoTitle == "") {
				videoTitle = null;
			}

			Categories category = null;

			if (categoryId > 0) {
				category = categoryDao.findOne(categoryId);
			}

			Pageable pageable = new PageRequest(offset / limit, limit, Direction.DESC, "videoId");
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);

			ObjectResponseTotal.setData(videoService.findAllVideos(videoTitle, category, pageable));
			ObjectResponseTotal.setTotal(videoService.findTotalVideos(videoTitle, category));
			responseObject.setObject(ObjectResponseTotal);
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@RequestMapping(value = "/upload-not-video", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject uploadNotVideo(@RequestParam("newsId") long newsId, final HttpSession session,
			@RequestParam("categoryId") long categoryId, @RequestParam("videoId") long videoId,
			@RequestParam("linkDirect") String linkDirect, @RequestParam("videoUpload") String videoUpload,
			@RequestParam("titleVideo") String titleVideo, @RequestParam("linkImageVideo") String linkImageVideo) {

		UserDTO userSession = CommonFunction.getCurrentUser(session);

		if (userSession == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {
			if (newsId > 0 && categoryId > 0) {

				Videos video = new Videos();
				if (videoId > 0) {
					video.setVideoId(videoId);
					video.setLinkUpload(videoUpload);
				}
				video.setCategoryId(categoryDao.findOne(categoryId));
				video.setCreateDate(new Date());
				video.setLinkDirect(linkDirect);
				video.setVideoTitle(titleVideo);
				video.setLinkImage(linkImageVideo);
				Long videoIdRes = videoDao.save(video).getVideoId();

				if (videoId == 0) {
					// Update newsId
					newsService.updateNews(videoDao.findOne(videoIdRes), newsId);
				} else {
					newsService.updateNews(videoDao.findOne(videoId), newsId);
				}

				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_UPDATE_SUCCESS);
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_UPDATE_FAILD);
		}

		return responseObject;
	}

	@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {
			Exception.class })
	@RequestMapping(value = "/upload-video", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject uploadVideo(@RequestParam("fileVideo") MultipartFile file, final HttpSession session,
			@RequestParam("videoId") long videoId, @RequestParam("newsId") long newsId,
			@RequestParam("categoryId") long categoryId, @RequestParam("linkDirect") String linkDirect,
			@RequestParam("videoUpload") String videoUpload, @RequestParam("titleVideo") String titleVideo,
			@RequestParam("linkImageVideo") String linkImageVideo) {

		UserDTO userSession = CommonFunction.getCurrentUser(session);

		if (userSession == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();

		if (file.isEmpty()) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			return responseObject;
		}

		try {
			if (newsId > 0 && categoryId > 0) {

				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				Date dte = new Date();
				long milliSeconds = dte.getTime();
				// get path
				String realPathtoUploads = request.getServletContext().getRealPath(Constants.UPLOADED_FOLDER);

				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				Path path = Paths.get(realPathtoUploads + milliSeconds + "-" + file.getOriginalFilename());
				Files.write(path, bytes);

				Videos video = new Videos();
				if (videoId > 0) {
					video.setVideoId(videoId);
					video.setLinkUpload(videoUpload);
				}
				video.setCategoryId(categoryDao.findOne(categoryId));
				video.setCreateDate(new Date());

				video.setLinkUpload(Constants.UPLOADED_FOLDER + milliSeconds + "-" + file.getOriginalFilename());
				video.setVideoName(file.getOriginalFilename());
				video.setVideoTitle(titleVideo);
				video.setLinkImage(linkImageVideo);
				Long videoIdRes = videoDao.save(video).getVideoId();

				if (videoId == 0) {
					// Update newsId
					newsService.updateNews(videoDao.findOne(videoIdRes), newsId);
				} else {
					newsService.updateNews(videoDao.findOne(videoId), newsId);
				}

				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_UPDATE_SUCCESS);
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_UPDATE_FAILD);
		}

		return responseObject;
	}

	@RequestMapping(value = "/deleteVideos", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteVideos(@RequestParam("videoId") Long videoId, final HttpSession session) {
		UserDTO userSession = CommonFunction.getCurrentUser(session);

		if (userSession == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_DELETE_DATA_SUCCESS);
			if (videoId > 0) {
				java.util.Timer timer = new java.util.Timer();
				timer.schedule(new java.util.TimerTask() {

					@Override
					public void run() {
						System.err.println("Run delete file: " + new Date(System.currentTimeMillis()));
						Videos video = videoDao.findOne(videoId);
						System.err.println(video);
						if (video != null) {
							responseObject.setStatus(true);
							responseObject.setMessage(Constants.MESSAGE_GET_DATA);

							String realPathtoUploads = request.getServletContext().getRealPath("");
							Path path = Paths.get(realPathtoUploads + video.getLinkUpload());
							File file = new File(path.toString());
							file.delete();
							System.out.println(path.toString());
							videoDao.delete(videoId);
						}
					}

				}, 120000); // 120 second
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_DELETE_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/findOneVideo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findOneVideo() {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			Pageable pageable = new PageRequest(0, 1, Direction.DESC, "videoId");

			responseObject.setObject(videoService.findOneVideo(pageable));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/findAllVideoByCategory", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findAllVideoByCategory(@RequestParam(value = "categoryId") Long categoryId,
			@RequestParam(value = "videoId") Long videoId) {
		ResponseObject responseObject = new ResponseObject();
		if (categoryId == 0) {
			categoryId = null;
		}
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			Pageable pageable = new PageRequest(0, 10, Direction.DESC, "videoId");
			responseObject.setObject(videoService.findAllVideoByCategory(categoryId, videoId, pageable));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/find-one-video-detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findOneVideoDetail(@RequestParam(value = "videoId") Long videoId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(new VideoDTO(videoDao.findOne(videoId)));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/find-all-video-detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findAllVideoDetail(@RequestParam(value = "videoId") List<Long> videoId,
			@RequestParam(value = "categoryId") Long categoryId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			if (categoryId == 0) {
				categoryId = null;
			}
			Pageable pageable = new PageRequest(0, 20, Direction.DESC, "videoId");
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(videoService.findAllVideoDetails(categoryId, videoId, pageable));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/find-two-video-detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findTwoVideoDetail(@RequestParam(value = "videoId") List<Long> videoId,
			@RequestParam(value = "categoryId") Long categoryId) {
		ResponseObject responseObject = new ResponseObject();
		try {
			if (categoryId == 0) {
				categoryId = null;
			}

			Pageable pageable = new PageRequest(0, 2, Direction.DESC, "videoId");
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(videoService.findAllVideoDetails(categoryId, videoId, pageable));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

}
