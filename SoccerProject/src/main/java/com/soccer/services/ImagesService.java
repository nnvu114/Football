package com.soccer.services;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.dao.ImagesDao;
import com.soccer.dto.ImagesDTO;
import com.soccer.entities.Images;
import com.soccer.repositories.ImagesReponsitory;

@Service
public class ImagesService {
	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	private ImagesReponsitory imagesReponsitory;

	@Autowired
	private ImagesDao imagesDao;

	public List<Object> getAllImages(String imageTitle, String link, int limit, int offset) {
		List<Object> listImagesDTO = new ArrayList<>();
		List<Images> listImages = imagesReponsitory.getListImage(imageTitle, link, limit, offset);

		for (Images image : listImages) {
			ImagesDTO imagesDTO = new ImagesDTO(image);
			listImagesDTO.add(imagesDTO);
		}

		return listImagesDTO;
	}

	public List<Object> getAllImages(String imageTitle) {
		List<Object> listImagesDTO = new ArrayList<>();
		List<Images> listImages = imagesDao.getListImages(imageTitle);

		for (Images image : listImages) {
			ImagesDTO imagesDTO = new ImagesDTO(image);
			listImagesDTO.add(imagesDTO);
		}

		return listImagesDTO;
	}
}
