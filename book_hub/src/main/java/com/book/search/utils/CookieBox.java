package com.book.search.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieBox {
	private static final Logger logger = LoggerFactory.getLogger(CookieBox.class);

	private static final String ENCRYT_KEY = "encryptkey";

	/***************************
	 * 로그인 여부 확인 (isLogin)
	 ***************************/		
	public static final boolean isLogin(HttpServletRequest req) {
		CryptEncoding encoding = new CryptEncoding();

		String userID = getCookie(req, "account_site");
		String timeStamp = getCookie(req, "timeStamp_site");
		String validChecker = getCookie(req, "validChecker_site");

		if (userID == null || timeStamp == null || validChecker == null) {
			return false;
		}
		return encoding.matches(ENCRYT_KEY + "|" + userID + "|" + timeStamp, validChecker);
	}

	/***************************
	 * 쿠키정보 입력 - (login)
	 ***************************/	
	public static final void login(HttpServletResponse res, String userID) {
		CryptEncoding encoding = new CryptEncoding();

		String timeStamp = String.valueOf(System.currentTimeMillis());
		String validstring = encoding.encode(ENCRYT_KEY + "|" + userID + "|" + timeStamp);

		addCookie(res, "account_site", userID, 365);
		addCookie(res, "timeStamp_site", timeStamp, 365);
		addCookie(res, "validChecker_site", validstring, 365);

	}

	/***************************
	 * 쿠키정보 삭제 - (logout)
	 ***************************/		
	public static final void logout(HttpServletResponse res) {
		//
		delCookie(res, "account_site");
		delCookie(res, "timeStamp_site");
		delCookie(res, "validChecker_site");
	}

	/***************************
	 * 로그인 ID 쿠키정보 가져오기 - (getUserID)
	 ***************************/	
	public static final String getUserID(HttpServletRequest req) {
		return getCookie(req, "account_site");
	}
	
	/***************************
	 * 쿠키 정보 추가  - (addCookie)
	 ***************************/		
	public static final void addCookie(HttpServletResponse res, String name, String value, int expiredDay) {

		try {

			value = URLEncoder.encode(value, "UTF-8");

			Cookie cookie = new Cookie(name, value);
			cookie.setPath("/");
			if (expiredDay != 0) {
				cookie.setMaxAge(60 * 60 * 24 * expiredDay);
			}

			res.addCookie(cookie);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***************************
	 * 쿠키 정보 삭제  - (delCookie)
	 ***************************/		
	public static final void delCookie(HttpServletResponse res, String name) {

		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		res.addCookie(cookie);
	}

	/***************************
	 * 쿠키 정보 가져오기 1 - (getCookie)
	 ***************************/		
	public static final String getCookie(HttpServletRequest req, String name) {

		Cookie[] cookies = req.getCookies();

		return getCookie(cookies, name);
	}

	/***************************
	 * 쿠키 정보 가져오기2  - (getCookie)
	 ***************************/		
	public static final String getCookie(Cookie[] cookies, String name) {

		if (cookies == null)
			return null;

		String returnValue = null;
		for (int i = 0; i < cookies.length; i++) {

			if (name.equals(cookies[i].getName())) {
				try {
					returnValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				} catch (Exception e) {
				}
				break;
			}
		}

		return returnValue;
	}

}
