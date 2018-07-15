package com.book.search.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.book.search.dao.Member;
import com.book.search.dao.SearchHistory;
import com.book.search.dao.SearchHistoryRepository;



@Service
public class SearchHistoryService {
	@Autowired
	SearchHistoryRepository seaerchHistoryRepository;

	/***************************
	 * 검색 히스토리 저장(save)
	 ***************************/		
	@Transactional
	public void save(SearchHistory entity) {
		seaerchHistoryRepository.save(entity);
	}

	/***************************
	 * 검색 히스토리 정보 가져오기
	 ***************************/		
	public Page<SearchHistory> findByMember(Member member, Pageable pageable) {
		return seaerchHistoryRepository.findByMember(member, pageable);
	}

}
