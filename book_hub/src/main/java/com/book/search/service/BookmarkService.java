package com.book.search.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.book.search.controller.AjaxController;
import com.book.search.dao.Bookmark;
import com.book.search.dao.BookmarkRepository;
import com.book.search.dao.Member;
import com.book.search.dao.MemberRepository;


@Service
public class BookmarkService {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	BookmarkRepository bookmarkRepository;

	@Autowired
	ApiService apiService;

	
	/***************************
	 * 등록된 Bookmark 가져오기(getBookmark) 
	 ***************************/	
	@Transactional
	public Bookmark getBookmark(Member member, String isbn) {
		
		logger.info("getBookmark_member___________________"+member);
		logger.info("getBookmark_isbn___________________"+isbn);	
		
		return bookmarkRepository.findOneByMemberAccountAndIsbn(member, isbn);
	}

	
	/***************************
	 * Bookmark 추가하기(addBookmark)
	 ***************************/		
	@Transactional
	public boolean addBookmark(Member member, String isbn) {
		Map<String, Object> map_book = apiService.getBookByISBN(isbn);
		Bookmark check = this.getBookmark(member, isbn);

		if (check != null) {
			return false;
		}

		Bookmark bookmark = new Bookmark();
		bookmark.setTitle(map_book.get("title").toString());
		bookmark.setMember(member);
		bookmark.setIsbn(isbn);
		bookmark.setRegdate(Timestamp.valueOf(LocalDateTime.now()));

		bookmarkRepository.save(bookmark);

		return true;
	}

	/***************************
	 * Bookmark 삭제하기(deleteBookmark)
	 ***************************/	
	@Transactional
	public boolean deleteBookmark(Member member, String isbn) {
		Bookmark bookmark = this.getBookmark(member, isbn);
		if (bookmark != null) {
			bookmarkRepository.delete(bookmark);
			return true;
		}
		return false;
	}

	/***************************
	 * 북마크 등록 정보 가져오기
	 ***************************/	
	@Transactional
	public Page<Bookmark> findByMember(Member member, Pageable pageable) {
		return bookmarkRepository.findByMember(member, pageable);
	}

}
