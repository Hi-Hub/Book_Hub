package com.book.search.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.search.dao.Bookmark;
import com.book.search.dao.Member;
import com.book.search.dao.SearchHistory;
import com.book.search.enums.EnumBookCategory;
import com.book.search.enums.EnumBookTarget;
import com.book.search.exceptions.IllegalLoginException;
import com.book.search.service.BookmarkService;
import com.book.search.service.MemberService;
import com.book.search.service.SearchHistoryService;
import com.book.search.utils.CookieBox;

@Controller
@EnableAutoConfiguration
public class mainPageController {
	private static final Logger logger = LoggerFactory.getLogger(mainPageController.class);

	@Autowired
	MemberService memberService;

	@Autowired
	BookmarkService bookmarkService;

	@Autowired
	SearchHistoryService searchHistoryService;

	
	/***************************
	 * 로그인 여부 확인(getMemberObj) 
	 * member(null) IllegalLoginException
	 * member(not null) return member
	 ***************************/		
	private Member getMemberObj(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String userID = CookieBox.getUserID(req);
		Member member = memberService.getMember(userID);
		if (member == null) {
			throw new IllegalLoginException("로그인 되지 않았습니다.");
		}
		return member;
	}

	/***************************
	 * 메인 페이지(mainPage) 
	 ***************************/	
	@RequestMapping("/")
	public String mainPage(HttpServletRequest req, HttpServletResponse res, Model model) {
		try {
			Member member = getMemberObj(req, res);
			model.addAttribute("memberInfo", member);
			model.addAttribute("EnumTarget", EnumBookTarget.values());
			model.addAttribute("EnumCategory", EnumBookCategory.values());
			return "/mainPage";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/login";
		}
	}

	/***************************
	 * 상세보기 페이지(detail) 
	 ***************************/	
	@RequestMapping("/detail")
	public String detail(HttpServletRequest req, HttpServletResponse res, Model model,
			@RequestParam("isbn") String isbn) {
		try {
			Member member = getMemberObj(req, res);

			Bookmark bookmark = bookmarkService.getBookmark(member, isbn);
			model.addAttribute("memberInfo", member);			
			model.addAttribute("bookmark", bookmark);
			return "/detail";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/login";
		}
	}

	/***************************
	 * 북마크 페이지(bookmarks) 
	 ***************************/	
	@RequestMapping("/bookmarks")
	public String bookmarks(HttpServletRequest req, HttpServletResponse res, Model model,
			@PageableDefault(size = 10, page = 0, sort = "regdate", direction = Direction.ASC) Pageable pageable) {
		try {
			Member member = getMemberObj(req, res);

			Page<Bookmark> bookmarkPage = bookmarkService.findByMember(member, pageable);
			model.addAttribute("memberInfo", member);			
			model.addAttribute("bookmarkPage", bookmarkPage);
			return "/bookmarks";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/login";
		}

	}

	/***************************
	 * 검색 히스토리 페이지(searchHistory) 
	 ***************************/		
	@RequestMapping("/searchHistory")
	public String searchHistory(HttpServletRequest req, HttpServletResponse res, Model model,
			@PageableDefault(size = 10, page = 0, sort = "regdate", direction = Direction.DESC) Pageable pageable) {
		try {
			Member member = getMemberObj(req, res);

			Page<SearchHistory> searchHistoryPage = searchHistoryService.findByMember(member, pageable);
			model.addAttribute("memberInfo", member);		
			model.addAttribute("searchHistoryPage", searchHistoryPage);
			return "/searchHistory";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/login";
		}

	}

}
