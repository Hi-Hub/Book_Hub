package com.book.search.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.search.service.LoginService;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	
	/***************************
	 * 로그인(login)	isLogin == Y
	 * 회원가입(join)	isLogin == N 
	 ***************************/	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse res, Model model,
			@RequestParam(name = "nickName", defaultValue = "") String nickName,
			@RequestParam(name = "userID", defaultValue = "") String userID,
			@RequestParam(name = "userPwd", defaultValue = "") String userPwd,
			@RequestParam(name = "isLogin", defaultValue = "") String isLogin) {
		
		if("Y".equals(isLogin)) {		
			logger.info("loginController 로그인 페이지로 이동 되었습니다.");			
			
			if (userID.isEmpty()) {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "ID를 입력해주세요.");
				return "login";
			} else if (userPwd.isEmpty()) {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "Password를 입력해주세요.");	
				return "login";
			}
			
			if (loginService.login(res, userID, userPwd)) {
				return "redirect:/";
			} else {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "등록된 회원정보가 없습니다.<br>회원가입 후 이용해주시기 바랍니다.");
				return "login";
			}

		} else if("N".equals(isLogin)) {			
			logger.info("loginController 회원가입 페이지로 이동 되었습니다.");
			
			if (nickName.isEmpty()) {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "닉네임을 입력해주세요.");
				return "join";
			} else if (userID.isEmpty()) {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "ID를 입력해주세요.");
				return "join";
			} else if (userPwd.isEmpty()) {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "Password를 입력해주세요.");	
				return "join";
			}			
			
			if (loginService.join(res, userID, userPwd, nickName)) {
				model.addAttribute("resultCode", "succes");
				model.addAttribute("resultMessage", "회원가입에 성공하였습니다.<br>로그인 화면으로 이동 후 로그인해 주시기바랍니다.");
				return "join";
			} else {
				model.addAttribute("resultCode", "fail");
				model.addAttribute("resultMessage", "회원가입에 실패하였습니다.<br>다시한번 확인 후 시도하여 주시기바랍니다.");	
				return "join";
			}	
		}
		
		return "login";
	}
	
	
	/***************************
	 * 로그아웃(logout)
	 ***************************/	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		logger.info("loginController 로그아웃처리 되었습니다. ");
		return loginService.logout(res);
	}

}
