package com.soccer.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soccer.commons.CommonFunction;
import com.soccer.commons.Constants;
import com.soccer.commons.GetDataFromOtherWeb;
import com.soccer.commons.ResponseObject;
import com.soccer.dao.CategoryDao;
import com.soccer.dto.CatelogyDTO;
import com.soccer.dto.UserDTO;
import com.soccer.entities.Categories;
import com.soccer.exception.UserNotFoundException;
import com.soccer.services.CatelogyService;

@RestController
public class CategoryController {

	@Autowired
	private CatelogyService catelogyService;

	@Autowired
	private CategoryDao catelogyDao;

	@RequestMapping("/categories-other-web")
	@ResponseBody
	public String getListCategories(final HttpSession session) throws IOException {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		try {
			// Initialize your Date however you like it.
			Date date = new Date();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			// Add one to month {0 - 11}
			int month = calendar.get(Calendar.MONTH) + 1;
			long viewYear = 0;
			if (month < 6) {
				viewYear = year - 1;
			} else {
				viewYear = year;
			}
			String url = "http://api.football-data.org/v1/competitions/?season=" + viewYear;
			JSONArray json = GetDataFromOtherWeb.readJsonFromUrl(url);
			return json.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	@ResponseBody
	public List<CatelogyDTO> categories(@RequestParam(value = "search") String search, final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);

		if (user == null) {
			throw new UserNotFoundException();
		}

		try {
			if (search == "") {
				search = null;
			}
			return catelogyService.getAll(search);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/insert-category", method = RequestMethod.POST)
	@ResponseBody
	public ResponseObject insertcategory(@RequestBody Map<String, Object> category, final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);
		String categoryName = (String) category.get("categoryName");
		if (user == null) {
			throw new UserNotFoundException();
		}

		ResponseObject responseObject = new ResponseObject();
		try {

			if (categoryName != "") {
				Categories categories = new Categories();
				categories.setCategoryName(categoryName);
				categories.setCreateDate(new Date());

				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_INSERT_SUCCESS);
				responseObject.setObject(catelogyDao.save(categories));
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObject;

	}

	@RequestMapping(value = "/find-one-category", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findOneCategory(@RequestParam(value = "categoryId") long categoryId,
			final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);
		if (user == null) {
			throw new UserNotFoundException();
		}
		ResponseObject responseObject = new ResponseObject();
		try {

			if (categoryId > 0) {
				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_INSERT_SUCCESS);
				responseObject.setObject(catelogyDao.findOne(categoryId));
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObject;

	}

	@RequestMapping(value = "/update-category", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseObject updateCategory(@RequestParam(value = "categoryId") long categoryId,
			@RequestBody Map<String, Object> category, final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);
		String categoryName = (String) category.get("categoryName");
		if (user == null) {
			throw new UserNotFoundException();
		}
		ResponseObject responseObject = new ResponseObject();
		try {

			if (categoryName != "" && categoryId > 0) {
				Categories categories = new Categories();
				categories.setCategoryName(categoryName);
				categories.setCreateDate(new Date());
				categories.setCategoryId(categoryId);

				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_UPDATE_SUCCESS);
				responseObject.setObject(catelogyDao.save(categories));
			} else {
				responseObject.setStatus(false);
				responseObject.setMessage(Constants.MESSAGE_MISS_PARAM);
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setStatus(true);
			responseObject.setMessage(Constants.MESSAGE_UPDATE_FAILD);
		}
		return responseObject;

	}

	@RequestMapping(value = "/delete-catelogy", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseObject deleteCatelogy(@RequestParam(value = "categoryId") long categoryId,
			final HttpSession session) {

		UserDTO user = CommonFunction.getCurrentUser(session);
		if (user == null) {
			throw new UserNotFoundException();
		}
		ResponseObject responseObject = new ResponseObject();
		try {

			if (categoryId > 0) {
				responseObject.setStatus(true);
				responseObject.setMessage(Constants.MESSAGE_DELETE_DATA_SUCCESS);
				catelogyDao.delete(categoryId);
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

	@RequestMapping(value = "/find-all-category", method = RequestMethod.GET)
	@ResponseBody
	public ResponseObject findAllCategory() {
		ResponseObject responseObject = new ResponseObject();
		try {
			responseObject.setMessage(Constants.MESSAGE_GET_DATA);
			responseObject.setStatus(true);
			responseObject.setObject(catelogyService.getAll(null));
		} catch (Exception e) {
			e.printStackTrace();
			responseObject.setMessage(Constants.MESSAGE_GET_DATA_FAILD);
			responseObject.setStatus(false);
		}
		return responseObject;
	}

}
