<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-3">
				<b>成績管理</b>
			</h2>
			<p class="h2 fw-normal bg-opacity-10 py-2 px-5 mb-3"
				style="text-align: center; background-color: #66CC99; font-size: small;">登録が完了しました</p>
			<a href="#">戻る</a> &nbsp;<a href="#">成績参照</a>
		</section>
	</c:param>
</c:import>