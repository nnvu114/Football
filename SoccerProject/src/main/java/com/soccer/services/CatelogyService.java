package com.soccer.services;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccer.dao.CategoryDao;
import com.soccer.dto.CatelogyDTO;
import com.soccer.entities.Categories;

@Service
public class CatelogyService {

	@Autowired
	DozerBeanMapper dozerBeanMapper;

	@Autowired
	private CategoryDao catelogyDao;

	public List<CatelogyDTO> getAll(String categoryName) {
		List<CatelogyDTO> listCatelogyDTO = new ArrayList<>();
		List<Categories> listCategories = catelogyDao.getListCategories(categoryName);

		for (Categories categories : listCategories) {
			listCatelogyDTO.add(dozerBeanMapper.map(categories, CatelogyDTO.class));
		}

		return listCatelogyDTO;
	}

}
