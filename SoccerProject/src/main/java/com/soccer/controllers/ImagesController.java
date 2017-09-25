package com.soccer.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.soccer.dao.ImagesDao;
import com.soccer.dto.ImagesDTO;
import com.soccer.dto.UserDTO;
import com.soccer.entities.Images;
import com.soccer.exception.UserNotFoundException;
import com.soccer.repositories.ImagesReponsitory;
import com.soccer.services.ImagesService;

@RestController
public class ImagesController {

	@Autowired
	private ImagesService imagesService;

	@Autowired
	private ImagesReponsitory imagesReponsitory;

	@Autowired
	private ImagesDao imagesDao;

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/images", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject getListImagese(@RequestParam("imageTitle") String imageTitle,
			@RequestParam("link") String link, @RequestParam("limit") int limit, @RequestParam("offset") int offset,
			final HttpSession session) throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		ObjectResponseTotal objectResponse = new ObjectResponseTotal();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			objectResponse.setTotal(imagesReponsitory.getTotalListImages(imageTitle, link, limit, offset));
			objectResponse.setData(imagesService.getAllImages(imageTitle, link, limit, offset));
			responseObject.setObject(objectResponse);
		} catch (Exception e) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/get-list-images", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject getListImagese(@RequestParam(value = "search") String search, final HttpSession session)
			throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			if (search == "") {
				search = null;
			}
			responseObject.setObject(imagesService.getAllImages(search));
		} catch (Exception e) {
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/insert-image", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertImage(@RequestParam("fileImages") MultipartFile file, final HttpSession session,
			@RequestParam("imageTitle") String imagetitle, @RequestParam("statusImages") Short statusImages)
			throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);

			// get path
			String realPathtoUploads = request.getServletContext().getRealPath(Constants.UPLOADED_IMAGES);
			if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
			}
			Date dte = new Date();
			long milliSeconds = dte.getTime();
			Path path = Paths.get(realPathtoUploads + milliSeconds + "-" + file.getOriginalFilename());

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Files.write(path, bytes);

			Images images = new Images();
			images.setCreateDate(dte);
			images.setImageTitle(imagetitle);
			images.setLink(Constants.UPLOADED_IMAGES + milliSeconds + "-" + file.getOriginalFilename());
			images.setStatus(statusImages);

			responseObject.setObject(new ImagesDTO(imagesDao.save(images)));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/find-one-image", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findOneImage(@RequestParam(value = "imageId") Long imageId, final HttpSession session)
			throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setObject(new ImagesDTO(imagesDao.findOne(imageId)));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/update-image", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateImage(@RequestParam("fileImages") MultipartFile file,
			@RequestParam("imageId") Long imageId, @RequestParam("imageTitle") String imageTitle,
			@RequestParam("statusImages") Short statusImages, final HttpSession session) throws IOException {
		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {

			Images imageDelete = imagesDao.findOne(imageId);
			if (imageDelete != null) {
				// delete file existed
				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_GET_DATA);
				
				String realPathtoDelete = request.getServletContext().getRealPath("");
				Path pathDelete = Paths.get(realPathtoDelete + imageDelete.getLink());
				File fileDelete = new File(pathDelete.toString());
				fileDelete.delete();
				
				// get path
				String realPathtoUploads = request.getServletContext().getRealPath(Constants.UPLOADED_IMAGES);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}
				Date dte = new Date();
				long milliSeconds = dte.getTime();
				Path path = Paths.get(realPathtoUploads + milliSeconds + "-" + file.getOriginalFilename());

				// Get the file and save it somewhere
				byte[] bytes = file.getBytes();
				Files.write(path, bytes);

				Images images = new Images();
				images.setCreateDate(dte);
				images.setImageTitle(imageTitle);
				images.setLink(Constants.UPLOADED_IMAGES + milliSeconds + "-" + file.getOriginalFilename());
				images.setStatus(statusImages);
				images.setImageId(imageId);
				// Save recode
				responseObject.setObject(new ImagesDTO(imagesDao.save(images)));
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/update-not-image", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateNotImage(@RequestParam("imageId") Long imageId, @RequestBody ImagesDTO image,
			final HttpSession session) throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);

			// get path
			String realPathtoUploads = request.getServletContext().getRealPath(Constants.UPLOADED_IMAGES);
			if (!new File(realPathtoUploads).exists()) {
				new File(realPathtoUploads).mkdir();
			}

			Images images = new Images();
			images.setCreateDate(new Date());
			images.setImageTitle(image.getImageTitle());
			images.setLink(imagesDao.findOne(imageId).getLink());
			images.setStatus(image.getStatus());
			images.setImageId(imageId);
			responseObject.setObject(new ImagesDTO(imagesDao.save(images)));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}

	@RequestMapping(value = "/deleteImage", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteImage(@RequestParam(value = "imageId") Long imageId, final HttpSession session)
			throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {

			Images images = imagesDao.findOne(imageId);
			if (images != null) {
				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_GET_DATA);
				
				String realPathtoUploads = request.getServletContext().getRealPath("");
				Path path = Paths.get(realPathtoUploads + images.getLink());
				File file = new File(path.toString());
				file.delete();
				imagesDao.delete(imageId);
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			}

		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(false);
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
		}
		return responseObject;
	}
}
