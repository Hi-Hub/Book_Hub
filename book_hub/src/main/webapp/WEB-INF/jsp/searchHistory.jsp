<%--
/*******************************************************************
 * 1. 설명
 * 검색내역(History)페이지
 * /book_hub/src/main/webapp/WEB-INF/jsp/searchHistory.jsp
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
<title>Book Hub [검색내역]</title>

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
	</div>
	<div class="container mt-3">
		<div class="row">
			<div class="col list-bookmark">
				<hr>
				<form action="./searchHistory" id="searchForm" name="searchForm" method="get">
					<div class="form-row">
						<h4>검색 히스토리</h4>&nbsp&nbsp&nbsp 
						<div class="form-group">
							<select id="select-sort" name="sort" class="form-control">
								<option value="regdate,desc" ${param.sort=='regdate,desc'?'selected="selected"':'' }>시간역순 </option>
								<option value="regdate,asc" ${param.sort=='regdate,asc'?'selected="selected"':'' }>시간순 </option>
							</select>
						</div>
					</div>
					<input type="hidden" name="page" value="${searchHistoryPage.number}">
					<input type="hidden" name="size" value="${searchHistoryPage.size }">					
				</form>
				<hr>				

				<ul class="list-group">
					<c:if test="${empty searchHistoryPage.content }">
						<li>조회된 검색 내용이 없습니다.</li>
					</c:if>
					<c:forEach var="b" items="${searchHistoryPage.content }">
						<li class="list-group-item">
							<dl>
								<dt>
									<b>검색단어</b> <span style="color: green;">${b.search_word }</span><br>
									<b>검색날짜</b> <span style="color: green;"><fmt:formatDate value="${b.regdate }" pattern="yyyy. MM. dd HH:mm:ss"/></span>
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
			<c:if test="${not empty searchHistoryPage.content }">
				$('.paging-layout').bootpag({
				    total: ${searchHistoryPage.totalPages},
				    page: ${searchHistoryPage.number+1},
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
		});
		
		$('#searchForm select[name=sort]').on('change',function(){
			var frm = document.searchForm
			frm.page.value = 0;
			frm.submit();
		});
	</script>
</body>
</html>