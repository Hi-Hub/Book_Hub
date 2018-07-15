package com.book.search.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CryptEncoding implements PasswordEncoder{
	private PasswordEncoder passwordEncoder;
	
	public CryptEncoding() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	public CryptEncoding(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	/***************************
	 * password 인코딩 (encode)
	 ***************************/		
	@Override
	public String encode(CharSequence rawPassword) {
		//
		return passwordEncoder.encode(rawPassword);
	}

	/***************************
	 * password 매칭 (matches)
	 ***************************/		
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
}
