package com.book.search;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.book.search.dao.Member;
import com.book.search.dao.MemberRepository;

//@EnableAutoConfiguration
//@ComponentScan
public class App implements CommandLineRunner {

	@Autowired
	MemberRepository memberRepository;

	@Override
	public void run(String... arg0) throws Exception {
	
	}
}
