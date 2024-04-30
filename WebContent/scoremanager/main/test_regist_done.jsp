<%@page language = "java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />

    <c:param name="scripts"></c:param>
	<c:param name = "content">
		<section class = "mp-4">
			<h2 class="h2 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<p class="p mb-3 fw-normal text-center" style="background-color: rgba(50, 150, 100, 0.6); padding: 4px;">登録が完了しました</p>
			<br><br><br><br><br>
			<a href="TestRegist.action">戻る</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href = "TestList.action">成績参照</a>
		</section>
	</c:param>
</c:import>
