<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.id }">
	<li><a href="login.yong">로그인(mvc버전)</a></li>
	<li><a href="join.yong">회원가입(mvc버전)</a></li>
</c:if>
<%
String sname=(String)session.getAttribute("sname");

%>
<header> <!-- 시멘틱 태그: header -->
	<%
	if(sname==null){
		%>
			<div><a href="javascript:openLogin();">로그인</a> 
			| <a href="/myweb/member/memberJoin.jsp">회원가입</a></div>		
		<%
	}else{
		%>
		<div><%=sname %>님 로그인중...| <a href="/myweb/member/logout.jsp">로그아웃</a></div>
		<%
	}
	%>
<h1>JSP Study Site</h1>
<nav>
	<ul>
		<li><a href="/myweb">Home</a></li>
		<li><a href="#">Profile</a></li>
		<li><a href="/myweb/bbs/bbsList.jsp">자유게시판</a></li>
		<li><a href="/myweb/webfolder/webfolder.jsp">Yong드라이브</a></li>
		<li><a href="#">방명록</a></li>
	</ul>
</nav>

<hr>
</header>
