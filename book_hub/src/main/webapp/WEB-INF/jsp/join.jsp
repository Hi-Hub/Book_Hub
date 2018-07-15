<%--
/*******************************************************************
 * 1. 설명
 * 회원가입 페이지
 * /book_hub/src/main/webapp/WEB-INF/jsp/join.jsp
 *  
 * @author : ehkim
 * @version v1.0
 * @작성 일자 : 2018-07-13
 * 
 ******************************************************************/
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<title>Book Hub [회원가입]</title>
 	<meta charset="utf-8"> 
  	<meta name="viewport" content="width=device-width, initial-scale=1"> 
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 

	<script type="text/javascript">

	function go_submit(login){
		var frm = document.login;
		
		if(login == "1"){
			frm.isLogin.value = "Y";
			frm.submit();
		}else{
			frm.isLogin.value = "N";
			frm.submit();
		}
	}
		
	</script>
</head>
<body class="login">
<form name="login" action="/login" method="POST">     
	<input type="hidden" name="isLogin" value="" />
	<div class="jumbotron text-center"> 
	  <h1>인터넷 책방</h1> 
	  <p>회원가입 화면 입니다.</p>
	</div> 
	   
	<div class="container">
    	<div class="row">
      		<div class="col">
	      		<h3>회원가입</h3> 
				<br>				
			  	<p>회원가입에 필요한 ID와 password를 입력해주세요.</p>
			  	<label for="nickName" class="sr-only">닉네임</label>
	          	<input type="text" id="nickName" name="nickName" class="form-control" placeholder="닉네임" autofocus />
			  	<label for="userID" class="sr-only">ID</label>
	          	<input type="text" id="userID" name="userID" class="form-control" placeholder="ID" autofocus />
	          	<label for="userPwd" class="sr-only">Password</label>
	          	<input type="password" id="userPwd" name="userPwd" class="form-control" placeholder=" Password" />
	          	<br>
	          	<button class="btn btn-lg btn-block" onclick="javascript:go_submit('2');">가입하기 </button>
	          	<div class="alert text-danger" style="">${resultMessage}</div>	          	
	          	<br>
	          	<button class="btn btn-lg btn-block" onclick="javascript:go_submit('1');">로그인화면으로 가기 </button>	          	
			</div>		
		</div>
	</div>
	 
</form>
</body>
</html>