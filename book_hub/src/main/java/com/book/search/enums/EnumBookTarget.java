package com.book.search.enums;

/***************************
 * 책 검색 종류 분류(EnumBookTarget) 
 ***************************/	
public enum EnumBookTarget {
	전체("전체", "all"), 제목("제목", "title"), 책소개("책소개", "overview"), 출판사("출판사", "publisher"), ISBN("ISBN", "isbn"), 주제어("주제어", "keyword"), 목차("목차", "contents");

	private String desc;
	private String code;

	public static EnumBookTarget getByCode(String code) {
		EnumBookTarget returnValue = null;

		for (EnumBookTarget temp : EnumBookTarget.values()) {
			if (temp.getCode().equals(code)) {
				returnValue = temp;
				break;
			}
		}

		return returnValue;
	}
	
	private EnumBookTarget(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}
	
}
