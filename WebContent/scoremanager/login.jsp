<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts">
		<script>
            // パスワード表示のトグル機能
            document.addEventListener('DOMContentLoaded', function() {
                const showPasswordCheckbox = document.getElementById('show-password');
                const passwordInput = document.getElementById('password');

                showPasswordCheckbox.addEventListener('change', function() {
                    if (showPasswordCheckbox.checked) {
                        passwordInput.type = 'text';
                    } else {
                        passwordInput.type = 'password';
                    }
                });
            });
        </script>
	</c:param>
	<c:param name="content">
		<div class="row border mx-3 py-2 align-items-center rounded"
			id="fillter">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-0 px-4 mb-3"
				style="text-align: center;">
				<b>ログイン</b>
			</h2>
			<% if (request.getAttribute("errors") != null) { %>
			<ul style="text-align: center; list-style: none; padding-left: 0;">
				<% for (String error : (List<String>) request.getAttribute("errors")) { %>
				<li><%= error.substring(0) %></li>
				<% } %>
			</ul>
			<% } %>


			<form method="post" action="LoginExecute.action">
				<div class="mb-3">
					<label class="form-label" for="username">ID</label>
						<input type="text" class="form-control" id="id" name="id" required placeholder="半角の初期値を入力してください" />
										</div>
				<div class="mb-3">
					<label class="form-label" for="password">パスワード</label> <input
						type="password" class="form-control" id="password" name="password"
						required placeholder="20文字以内の半角英数字でご入力ください。"/>
				</div>
				<div class="mb-3 form-check text-center">
					<label class="form-check-label" for="show-password">パスワードを表示
						<input class="form-check-input" type="checkbox" id="show-password" />
					</label>
				</div>
				<div class="text-center">
					<!-- ログインボタンとエラーメッセージを中央寄せ -->
					<input type="submit" name="login" class="btn btn-primary"
						value="ログイン">
					<div class="mt-2 text-danger">
						<c:if test="${not empty errorMessage}">
            ${errorMessage}
        </c:if>
					</div>
				</div>

			</form>
		</div>
	</c:param>
</c:import>