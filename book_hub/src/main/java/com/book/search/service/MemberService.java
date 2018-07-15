package com.book.search.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.search.dao.Member;
import com.book.search.dao.MemberRepository;

@Service
public class MemberService {
	@Autowired
	MemberRepository memberRepository;

	
	/***************************
	 * 회원정보 가져오기(getMember)
	 ***************************/		
	@Transactional
	public Member getMember(String account) {
		return memberRepository.findOne(account);
	}

	/***************************
	 * 회원정보 신규(저장)하기(save)
	 ***************************/		
	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}

}
