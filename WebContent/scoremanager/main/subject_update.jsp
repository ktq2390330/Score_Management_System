<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">科目情報変更</h2>
			<form action="SubjectUpdateExecute.action" method="post"
				onsubmit="return validateForm()">
				<div class="form-group" style="border: none;">
					<!-- 枠線を非表示にする -->
					<label for="cd">科目コード</label> <input type="text"
						class="form-control" id="cd" name="cd" value="${subject.cd}"
						readonly style="border: none;">
				</div>
				<%-- エラーメッセージの表示 --%>
				<div class="mt-2" style="color: orange;">
					<%
						String errorMessage = (String) request.getAttribute("errorMessage");
								if (errorMessage != null) {
					%>
					<%=errorMessage%>
					<%
						}
					%>
				</div>

				<div class="form-group">
					<label for="name">科目名</label> <input type="text"
						class="form-control" id="name" name="name" value="${subject.name}" required>
				</div>
				<br>
				<button type="submit" class="btn btn-primary">変更</button>
			</form>


			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>