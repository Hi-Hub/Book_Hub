<%--
/*******************************************************************
 * 1. 설명
 * 상단 메뉴(Gnb)
 * /book_hub/src/main/webapp/WEB-INF/jsp/include/gnb.jsp
 *  
 * @author : ehkim
 * @version v1.0
 * @작성 일자 : 2018-07-13
 * 
 ******************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar  navbar-expand-sm  bg-secondary  navbar-dark"> 
  <ul class="navbar-nav"> 
    <li class="nav-item active"> 
      <a class="nav-link" href="/">[책검색 화면]</a>
    </li> 
    <li class="nav-item active" > 
      <a class="nav-link" href="/searchHistory">[검색 히스토리]</a> 
    </li> 
    <li class="nav-item active"> 
      <a class="nav-link" href="/bookmarks">[북마크]</a> 
    </li> 
    <li class="nav-item active"> 
      <a class="nav-link disabled" href="/logout">[로그아웃]</a> 
    </li> 
  </ul> 
</nav>