<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">科目情報削除</h2>
			<c:if test="${not empty errorMessage}">
                <p class="text-orange">${errorMessage}</p>
            </c:if>
			<p class="h2 fw-normal bg-opacity-10 py-2 px-4 mb-0"
				style="text-align: left; font-size: small;">「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>
			<!-- 削除ボタン -->
			<a href="SubjectDeleteExecute.action?cd=${subject.cd}" style="color: white; background-color: red; padding: 8px 16px; border-radius: 4px; text-decoration: none;">削除</a>
			<!-- 戻るボタン -->
			<a href="SubjectList.action">戻る</a>

		</section>
	</c:param>
</c:import>