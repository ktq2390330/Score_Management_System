<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">学生情報更新</h2>
			<form action="StudentUpdateExecute.action" method="post"
				onsubmit="return validateForm()">
				<div class="form-group" style="border: none;">
					<!-- 枠線を非表示にする -->
					<label for="entyear">入学年度</label> <input type="text"
						class="form-control" id="entyear" name="entyear"
						value="${student.entYear}" readonly>
				</div>

				<div class="form-group" style="border: none;">
					<!-- 枠線を非表示にする -->
					<label for="no">学生番号</label> <input type="text"
						class="form-control" id="no" name="no" value="${student.no}"
						readonly>
				</div>

				<div class="form-group">
					<label for="name">氏名</label> <input type="text"
						class="form-control" id="name" name="name" value="${student.name}" required>
				</div>
				<div id="errorMessage" style="color: black;"></div>

				<div class="form-group">
					<label for="classnum">クラス</label> <select class="form-select"
						id="classnum" name="classnum">
						<c:forEach var="classNumber" items="${classNumbers}">
							<option value="${classNumber}"
								<c:if test="${classNumber eq student.classNum}">selected</c:if>>${classNumber}</option>
						</c:forEach>
					</select>
				</div>

				<div class="form-group form-check">
					<input type="checkbox" class="form-check-input" id="isattend"
						name="isattend" value="true"
						<c:if test="${student.attend}">checked</c:if>> <label
						class="form-check-label" for="isattend">在学中</label>
				</div>

				<button type="submit" class="btn btn-primary">更新</button>
			</form>


			<a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>

<script>
    function validateForm() {
        var name = document.getElementById("name").value.trim();
        var errorMessageElement = document.getElementById("errorMessage");

        if (name === "") {
            errorMessageElement.innerText = "氏名を入力してください。";
            errorMessageElement.style.color = "black"; // エラーメッセージの色を赤に設定
            return false;
        } else {
            errorMessageElement.innerText = ""; // エラーメッセージをクリア
            return true;
        }
    }
</script>