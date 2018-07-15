package com.book.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.book.search.enums.EnumBookCategory;
import com.book.search.enums.EnumBookTarget;
import com.book.search.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/***************************
 * developers.kakao.com 책검색 API
 ***************************/	
@Service
public class ApiService {

	private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

	private static final String API_KEY = "7d599761d90266f796583bc192f906fd";
	private static final String API_URL = "https://dapi.kakao.com/v2/search/book";

	public Map<String, Object> searchBooks(String searchWord, String target, String category, int page) {
		//
		final String URL = API_URL + "?target=" + target + "&target=" + target + "&category=" + category + "&page="
				+ page;
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "KakaoAK " + API_KEY);
		Map<String, String> params = new HashMap<>();
		params.put("query", searchWord);
		String jsonString = null;
		Map<String, Object> resultData = null;
		try {
			jsonString = Utils.getHttpPOST2String(URL, headers, params, false);
			ObjectMapper mapper = new ObjectMapper();
			resultData = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
			});

			logger.info(URL + "API Info : " + jsonString);

		} catch (Exception e) {
			logger.info(URL + "API Exception : " + jsonString);
			e.printStackTrace();
		}
		return resultData;
	}

	/***************************
	 * 책정보 가져오기 (getBookByISBN)
	 ***************************/	
	public Map<String, Object> getBookByISBN(String ISBN) {
		Map<String, Object> book = null;
		Map<String, Object> json = this.searchBooks(ISBN, EnumBookTarget.전체.getCode(), EnumBookCategory.전체.getCode(),
				1);
		int cnt = (Integer) ((Map) json.get("meta")).get("total_count");
		if (cnt > 0) {
			book = (Map) ((List) json.get("documents")).get(0);
		}
		return book;
	}

}
