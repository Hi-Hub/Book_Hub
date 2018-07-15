<%--
/*******************************************************************
 * 1. 설명
 * 로그인 페이지
 * /book_hub/src/main/webapp/WEB-INF/jsp/login.jsp
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
	<title>Book Hub [로그인]</title>
  	<meta name="viewport" content="width=device-width, initial-scale=1"> 
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
  	
  	<script language="javascript">
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
<body>
	<form name="login" action="/login" method="POST">   
		<input type="hidden" name="isLogin" value="" />
		<div class="jumbotron text-center"> 
		  <h1>인터넷 책방</h1> 
		  <p>wellcome to the book search site</p>  
		</div> 
	   
		<div class="container"> 
			<div class="row">
		    	<div class="col-sm-6"> 
					<li class="list-group-item">		    	
			      		<h3>로그인</h3> 
						<br>				
					  	<p>기존 회원은  ID와 Password를 입력하여 주세요.</p>
					  	<label for="userID" class="sr-only">ID</label>
			          	<input type="text" id="userID" name="userID" class="form-control" placeholder="ID"  />
			          	<label for="userPwd" class="sr-only">Password</label>
			          	<input type="password" id="userPwd" name="userPwd" class="form-control" placeholder=" Password"  />
			          	<br>
			          	<button class="btn btn-lg btn-block" onclick="javascript:go_submit('1');">로그인 </button>
			          	<br>
			          	<div class="error-message alert text-danger" style="">${resultMessage}</div>
		      		</li>
				</div>	
				<div class="col-sm-6"> 	      	 	
				    <li class="list-group-item">
			      	 	<h3>회원가입</h3>
			      	 	<br> 					    
					          처음 방분하신 분은 회원가입 후 이용해주시기 바랍니다.</br>
					   	 다양한 서비스를 사용하실 수 있습니다.
		      			<br>
		      			<br>
		      			<br>
		      			<br>
		      			<br>			      			
	      				<button class="btn btn-lg btn-block" onclick="javascript:go_submit('2');">회원가입 </button>
		      			<br>
		      			<br>
		      			<br>			      				
	      			</li>						   	 
	    		</div> 
			</div>
		</div> 
	</form> 	
</body>
</html>