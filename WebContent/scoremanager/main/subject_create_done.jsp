<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">科目情報登録</h2>
			<br>
			<p class="h2 fw-normal bg-opacity-10 py-2 px-4 mb-0"
				style="text-align: center; background-color: lightgreen; font-size: small;">登録が完了しました</p>
			<br><br><br><br>
			<a href="SubjectCreate.action" class="link-margin">戻る</a>
			<a href="SubjectList.action">科目一覧</a>
		</section>
	</c:param>
</c:import>
<style>
	.link-margin {
	margin-right: 80px; /* 右側に80pxの余白を追加 */
	}
</style>