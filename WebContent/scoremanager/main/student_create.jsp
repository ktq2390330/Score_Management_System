<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">
				<b>学生情報登録</b>
			</h2>
			<form action="StudentCreateExecute.action" method="post"
				onsubmit="return validateForm()">
				<label for="entyear">入学年度</label> <select class="form-select"
					id="entyear" name="entyear">
					<option value="" selected disabled>--------</option>
					<c:forEach var="year" items="${yearList}">
						<option value="${year}">${year}</option>
					</c:forEach>
				</select>
				<div id="entError"></div>
				<br> <label for="no">学生番号</label> <input type="text"
					class="form-control" id="no" name="no" placeholder="学生番号を入力してください" maxlength="10">
				<div id="noError"></div>
				<br>
				<% if (request.getAttribute("errorMessage") != null) { %>
				<ul style="list-style: none; padding-left: 0; color: orange;">
					<li><%= request.getAttribute("errorMessage") %></li>
				</ul>
				<% } %>

				<label for="name">氏名</label> <input type="text" class="form-control"
					id="name" name="name" placeholder="氏名を入力してください" maxlength="10">
				<div id="nameError"></div>
				<br> <label for="classnum">クラス</label> <select
					class="form-select" id="classnum" name="classnum">
					<c:forEach var="classNumber" items="${classNumList}">
						<option value="${classNumber}">${classNumber}</option>
					</c:forEach>
				</select> <br> <input type="submit" value="登録して終了">
			</form>
			<br> <a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>

<script>
    function validateForm() {
        var entyear = document.getElementById("entyear").value;
        var no = document.getElementById("no").value.trim();
        var name = document.getElementById("name").value.trim();
        var noError = document.getElementById("entError");
        var noError = document.getElementById("noError");
        var nameError = document.getElementById("nameError");

        if (entyear === "") {
            entError.innerText = "入学年度を選択してください。";
            entError.style.color = "orange";
            nameError.innerText = ""; // 氏名エラーメッセージをクリア
            noError.innerText = ""; // 学生番号エラーメッセージをクリア
            return false;
        } else {
            noError.innerText = "";
        }
        if (no === "") {
            noError.innerText = "学生番号を入力してください。";
            noError.style.color = "black";
            nameError.innerText = ""; // 氏名エラーメッセージをクリア
            return false;
        } else {
            noError.innerText = "";
        }
        if (name === "") {
            nameError.innerText = "氏名を入力してください。";
            nameError.style.color = "black";
            noError.innerText = ""; // 学生番号エラーメッセージをクリア
            return false;
        } else {
            nameError.innerText = "";
        }
        return true;
    }
</script>