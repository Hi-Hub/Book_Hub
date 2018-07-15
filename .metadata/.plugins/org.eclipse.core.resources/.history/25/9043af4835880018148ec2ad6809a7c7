<%--
/*******************************************************************
 * 1. 설명
 * 북마크 페이지
 * /book_hub/src/main/webapp/WEB-INF/jsp/bookmarks.jsp
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
<title>Book Hub [북마크]</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div>
	<div class="container">
		<div class="jumbotron text-center"> 
		  <h4>인터넷 책방</h4> ${memberInfo.nickName } 환영합니다.
		  <%@ include file="/include/gnb.jsp"%>
		</div> 		
	</div>

	<div class="container mt-3">
		<div class="row">
			<div class="col list-bookmark">
				<hr>
				<form action="./bookmarks" id="searchForm" name="searchForm" method="get">
					<input type="hidden" name="page" value="${bookmarkPage.number}">
					<input type="hidden" name="size" value="${bookmarkPage.size }">
					<div class="form-row">
						<h4>북마크 리스트</h4>&nbsp&nbsp&nbsp 
						<div class="form-group">
							<select id="select-sort" name="sort" class="form-control">
								<option value="regdate,asc" ${param.sort=='regdate,asc'?'selected="selected"':'' }>시간순 </option>
								<option value="title,asc" ${param.sort=='title,asc'?'selected="selected"':'' }>이름순 </option>
							</select>
						</div>
					</div>
				</form>
				<hr>
				<ul class="list-group">
					<c:if test="${empty bookmarkPage.content }">
					<li>등록된 북마크가 없습니다.</li>
					</c:if>
					<c:forEach var="b" items="${bookmarkPage.content }">
						<li class="list-group-item">
							<dl>
								<dt>
									<a href="./detail?isbn=${b.isbn }">${b.title }</a>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
									<span><fmt:formatDate value="${b.regdate }" pattern="yyyy. MM. dd HH:mm:ss"/></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
									<button type="button" class="btn btn-light btn-bookmark delete" data-isbn="${b.isbn }">북마크 취소</button>
								</dt>
							</dl>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="paging-layout center-block"></div>
	</div>

	<script type="text/javascript" src="/js/jquery.bootpag.min.js"></script>
	<script type="text/javascript">

		function btnUnbookmark() {
			const $this = $(this);
			const ISBN = $this.data("isbn");
			console.log(ISBN);
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
	
		$(document).ready(function() {
			<c:if test="${not empty bookmarkPage.content }">
			$('.paging-layout').bootpag({
			    total: ${bookmarkPage.totalPages},
			    page: ${bookmarkPage.number+1},
			    maxVisible: 10,
			    leaps: true,
			    firstLastUse: true,
			    first: '처음으로',
			    last: '마지막으로',
			    wrapClass: 'pagination',
			    activeClass: 'active',
			    disabledClass: 'disabled',
			    nextClass: 'next',
			    prevClass: 'prev',
			    lastClass: 'last',
			    firstClass: 'first'
			}).on("page", function(event, num){
				console.log(num);
				var frm = document.searchForm
				frm.page.value = num-1;
				frm.submit();
			});
			</c:if>
			
			$(".list-bookmark .list-group .list-group-item").on("click", ".btn-bookmark.delete",btnUnbookmark);
		});
		
		$('#searchForm select[name=sort]').on('change',function(){
			var frm = document.searchForm
			frm.page.value = 0;
			frm.submit();
		});
	</script>
</body>
</html>