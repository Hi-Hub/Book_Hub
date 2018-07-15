<%--
/*******************************************************************
 * 1. 설명
 * 책 상세보기 페이지
 * /book_hub/src/main/webapp/WEB-INF/jsp/detail.jsp
 *  
 * @author : ehkim
 * @version v1.0
 * @작성 일자 : 2018-07-13
 * 
 ******************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/jstl.jsp"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Hub [상세보기]</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<style type="text/css">
body.loading .container .media {
	visibility: hidden;
}
</style>
</head>
<body class="loading">
	<div>
		<div class="container">
			<div class="jumbotron text-center"> 
			  <h4>인터넷 책방</h4> ${memberInfo.nickName } 환영합니다.
			  <%@ include file="/include/gnb.jsp"%>
			</div> 		
		</div>
	</div>
	<div class="container mt-3">
		<div class="media">
			<div>
				<div class="book-img mb-1"></div>
				<div class="mr-3" style="text-align: center;">
					<c:if test="${empty bookmark }">
						<button type="button" class="btn btn-light btn-bookmark add">북마크</button>
					</c:if>
					<c:if test="${not empty bookmark }">
						<button type="button" class="btn btn-light btn-bookmark delete">북마크 취소</button>
					</c:if>
				</div>
			</div>
			<div class="media-body">
				<hr>
				<p>
					<h4><b>제목 </b><span class="book-title"></span><h4>
				</p>
				<hr>				
					<h5 class="mt-0"><b>책소개</b></h5>
				<p class="book-contents"></p>	
				<hr>				
				<p>
					<b>저자</b>&nbsp&nbsp&nbsp<span class="book-authors"></span>
				</p>							
				<hr>				
				<p>
					<b>출판사</b>&nbsp&nbsp&nbsp<span class="book-publisher"></span>
				</p>
				<hr>								
				<p>
					<b>카테고리</b>&nbsp&nbsp&nbsp<span class="book-category"></span>
				</p>
				<hr>				
				<p>
					<b>판매가 (정상가)</b>&nbsp&nbsp&nbsp<span class="book-price"></span>
				</p>
				<hr>				
				<p>
					<b>판매여부</b>&nbsp&nbsp&nbsp<span class="book-status"></span>
				</p>
				<hr>
				<p>
					<b>ISBN</b>&nbsp&nbsp&nbsp<span class="book-isbn"></span>
				</p>				
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		function btnUnbookmark() {
			const $this = $(this);
			const ISBN = $this.data("isbn");
			$.ajax({
				url : "/ajax/unbookmark",
				method: "POST",
				data : {
					"isbn" : ISBN
				},
				success : function(res) {
					console.log(res);
					alert("취소되었습니다.");
				},
				error : function(res) {
					console.log(res);
					alert(res);
				},
				complete : function() {
					$this.prop( "disabled", true );
				}
			});
		}
		function btnBookmark() {
			const $this = $(this);
			const ISBN = $this.data("isbn");
			$.ajax({
				url : "/ajax/bookmark",
				method: "POST",
				data : {
					"isbn" : ISBN
				},
				success : function(res) {
					console.log(res);
					alert("북마크 되었습니다.");
				},
				error : function(res) {
					console.log(res);
					alert(res); 
				},
				complete : function() {
					$this.prop( "disabled", true );
				}
			});
		}

		function init() {
			$
					.ajax({
						url : "/ajax/getBookinfo/isbn/${param.isbn}",
						success : function(res) {
							if (res.meta.total_count > 0) {
								var doc = res.documents[0];
								var authors = "", trans = "", thumbnail = "", isbn = "";
								$(doc.authors).each(function() {
									authors += (this + " ")
								});
								$(doc.translators).each(function() {
									trans += (this + " ")
								});
								if (doc.thumbnail) {
									thumbnail = '<img class="mr-3 img-thumbnail" width="170" src="'+doc.thumbnail+'" alt="책 이미지 ">'
								}
								$(".book-img").html(thumbnail);
								$(".book-title").text(doc.title);
								$(".book-category").text(doc.category);
								$(".book-authors").text(doc.authors);
								$(".book-translators").text(doc.trans);
								$(".book-publisher").text(doc.publisher);
								$(".book-isbn").text(doc.isbn);
								var arr_isbn = doc.isbn.split(" ");
								if(arr_isbn.length>1){
									isbn = arr_isbn[1];
								}else{
									isbn = arr_isbn[0];
								}
								$(".btn-bookmark").data("isbn", isbn);
								$(".book-barcode").text(doc.barcoed);
								$(".book-price").text(
										doc.sale_price + "원 ( " + doc.price
												+ "원 )");
								$(".book-status").text(doc.status);
								$(".book-contents").text(doc.contents);

								$("body").removeClass("loading");
							} else {
								alert("정상적인 접근이 아닙니다.");
								location.replace("/");
							}
							console.log(res);
						},
						error : function(res) {
							console.log(res);
							alert("잠시후 다시 시도해주세요.");
							location.replace("/");
						},
						conplete : function() {

						}
					});
		}

		$(document).ready(function() {
			init();
			$(".btn-bookmark.add").on("click", btnBookmark);
			$(".btn-bookmark.delete").on("click", btnUnbookmark);
		});
	</script>
</body>
</html>