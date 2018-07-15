package com.book.search.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.search.dao.Member;
import com.book.search.utils.CookieBox;
import com.book.search.utils.CryptEncoding;


/***************************
 * 로그인 처리(LoginService)
 ***************************/
@Service
public class LoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	MemberService memberService;

	@Transactional
	public boolean login(HttpServletResponse res, String userID, String userPwd) {
		//
		Member member = memberService.getMember(userID);
		if (!userID.isEmpty() && !userPwd.isEmpty()) {
			PasswordEncoder passwordEncoder = new CryptEncoding();
			if (member != null) {
				if (passwordEncoder.matches(userPwd, member.getUserPwd())) {
					CookieBox.login(res, userID);
					return true;
				}
			}

		}
		return false;
	}

	
	/***************************
	 * 회원가입 처리(join)
	 ***************************/	
	@Transactional
	public boolean join(HttpServletResponse res, String userID, String userPwd, String nickName) {
		//
		Member member = memberService.getMember(userID);
		if (!userID.isEmpty() && !userPwd.isEmpty()) {

			PasswordEncoder passwordEncoder = new CryptEncoding();
			if (member == null) { // 계정없는 경우 신규생성
				
				member = new Member(userID, passwordEncoder.encode(userPwd), Timestamp.valueOf(LocalDateTime.now()), nickName);
				
				memberService.save(member);
				CookieBox.login(res, userID);
				return true;
			}
		}
		return false;
	}
	
	/***************************
	 * 로그아웃 처리(logout)
	 ***************************/		
	public String logout(HttpServletResponse res) {
		//
		CookieBox.logout(res);
		return "redirect:/login";
	}

}
