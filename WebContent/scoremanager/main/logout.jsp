<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">
				<b>ログアウト</b>
			</h2>
			<p class="h2 fw-normal bg-opacity-10 py-2 px-4 mb-0"
				style="text-align: center; background-color: lightgreen; font-size: small;">ログアウトしました</p>
			<br> <a href="/Score_Management/scoremanager/Login.action">ログイン</a>
		</section>
	</c:param>
</c:import>