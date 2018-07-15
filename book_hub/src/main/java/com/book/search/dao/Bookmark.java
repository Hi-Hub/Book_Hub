package com.book.search.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.book.search.controller.AjaxController;

@Entity
@Table(name = "bookmark")
public class Bookmark {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String isbn;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private Timestamp regdate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_account")
	private Member member;

	public Bookmark() {
		super();
	}

	public Bookmark(Long id, String title, String isbn, Timestamp regdate, Member member) {
		super();
		
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.regdate = regdate;
		this.member = member;
	}

	public Bookmark(String title, String isbn, Timestamp regdate, Member member) {
		super();

		this.title = title;
		this.isbn = isbn;
		this.regdate = regdate;
		this.member = member;
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
