<%@page language = "java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />

    <c:param name="scripts"></c:param>
	<c:param name = "content">
		<section class = "mp-4">
			<h2 class="h2 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">学生除籍</h2>
			<p class="p mb-3 fw-normal text-center" style="background-color: rgba(50, 150, 100, 0.6); padding: 4px;">学生を除籍しました</p>
			<br><br><br><br><br>
			<a href="StudentCreate.action">戻る</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href = "StudentList.action">学生一覧</a>
		</section>
	</c:param>
</c:import>