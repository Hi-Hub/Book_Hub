package com.book.search.enums;


/***************************
 * 책 category 분류(EnumBookCategory) 
 ***************************/	
public enum EnumBookCategory {
	전체("전체", ""), 국내도서_소설("국내도서(소설)", "1"), 국내도서_가정생활("국내도서(가정/생활)", "7"), 국내도서_요리("국내도서(요리)", "8"), 국내도서_취미스포츠("국내도서(취미/스포츠)",
			"11"), 국내도서_컴퓨터IT("국내도서(컴퓨터/IT)", "33"), 국내도서_외국어("국내도서(외국어)", "27"), 국내도서_과학("국내도서(과학)", "29"), 국내도서_여행("국내도서(여행)", "32");

	private String desc;
	private String code;
	
	private EnumBookCategory(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}	

	public static EnumBookCategory getByCode(String code) {
		EnumBookCategory returnValue = null;

		for (EnumBookCategory temp : EnumBookCategory.values()) {
			if (temp.getCode().equals(code)) {
				returnValue = temp;
				break;
			}
		}

		return returnValue;
	}	
	
	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}
}
