<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">科目情報登録</h2>
			<form action="SubjectCreateExecute.action" method="post"
				onsubmit="return validateForm()">

				<label for="cd">科目コード</label> <input type="text"
					class="form-control" id="cd" name="cd" placeholder="科目コードを入力してください"
					required>
				<div class="mt-2" style="color: orange;">
					<c:if test="${not empty errorMessage}">
                        ${errorMessage}
                    </c:if>
				</div>
				<br> <label for="name">科目名</label> <input type="text"
					class="form-control" id="name" name="name"
					placeholder="科目名を入力してください" required> <br>
				<button type="submit" class="btn btn-primary">登録</button>
			</form>
			<br> <a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>

<script>
	function validateForm() {
		var cd = document.getElementById("cd").value.trim();
		var cdError = document.getElementById("cdError");

		if (cd.length !== 3) {
			cdError.innerText = "科目コードは3文字で入力してください";
			cdError.style.color = "orange";
			return false;
		} else {
			cdError.innerText = "";
		}
		return true;
	}
</script>