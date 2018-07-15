package com.book.search.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.search.dao.Member;
import com.book.search.dao.SearchHistory;
import com.book.search.enums.EnumBookCategory;
import com.book.search.enums.EnumBookTarget;
import com.book.search.service.ApiService;
import com.book.search.service.BookmarkService;
import com.book.search.service.MemberService;
import com.book.search.service.SearchHistoryService;
import com.book.search.utils.CookieBox;

@RestController
@RequestMapping("ajax/")
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@Autowired
	ApiService apiService;

	@Autowired
	MemberService memberService;

	@Autowired
	BookmarkService bookmarkService;

	@Autowired
	SearchHistoryService searchHistoryService;

	
	/***************************
	 * 책검색 (searchBooks)
	 * 검색 후 SearchHistory save
	 ***************************/	
	@RequestMapping(value = "/searchBooks")
	public Map<String, Object> searchBooks(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("searchWord") String searchWord,
			@RequestParam(name = "target", defaultValue = "all") String target,
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "page", defaultValue = "1") int page) {

		String userID = CookieBox.getUserID(req);
		Member member = memberService.getMember(userID);
		Map<String, Object> result = apiService.searchBooks(searchWord, target, category, page);
		searchHistoryService.save(new SearchHistory(searchWord, target, category, Timestamp.valueOf(LocalDateTime.now()), member));

		return result;
	}

	/***************************
	 * 책 상세보기 (getBookinfo)
	 ***************************/	
	@RequestMapping(value = "/getBookinfo/isbn/{isbn}", method = RequestMethod.GET)
	public Map<String, Object> getBookinfo(@PathVariable String isbn) {
			
		Map<String, Object> result = apiService.searchBooks(isbn, EnumBookTarget.ISBN.getCode(),
				EnumBookCategory.전체.getCode(), 1);

		return result;
	}

	/***************************
	 * 북마크 (bookmark) 
	 ***************************/	
	@RequestMapping(value = "/bookmark", method = RequestMethod.POST)
	public String bookmark(HttpServletRequest req, HttpServletResponse res, @RequestParam("isbn") String isbn) {
		
		String userID = CookieBox.getUserID(req);
		Member member = memberService.getMember(userID);
		if (member == null) {
			return "FAIL";
		}
		boolean result = bookmarkService.addBookmark(member, isbn);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	/***************************
	 * 북마크 삭제(unbookmark) 
	 ***************************/	
	@RequestMapping(value = "/unbookmark", method = RequestMethod.POST)
	public String unbookmark(HttpServletRequest req, HttpServletResponse res, @RequestParam("isbn") String isbn) {

		String userID = CookieBox.getUserID(req);
		Member member = memberService.getMember(userID);
		if (member == null) {
			return "FAIL";
		}
		boolean result = bookmarkService.deleteBookmark(member, isbn);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

}
