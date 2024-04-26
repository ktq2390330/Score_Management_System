<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-1">
				<b>成績参照</b>
			</h2>
			<form method="get">
				<div class="row border mx-5 mb-6 py-3 align-items-center rounded"
					id="filter">

					科目情報
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度</label> <select
							class="form-select" id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label> <select
							class="form-select" id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${class_num_set}">
								<option value="${num}" <c:if test="${num==f2}">selected</c:if>>${num}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-5">
						<label class="form-label" for="subject-f3-select">科目</label> <select
							class="form-select" id="subject-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="num2" items="${subject_num2_set}">
								<option value="${num2}" <c:if test="${num2==f2}">selected</c:if>>${num2}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>

					<!-- Content above the line -->
					<hr class="my-3">
					<!-- Horizontal line -->
					<!-- Content below the line -->

					学生情報
					<div class="col-5">
						<label class="form-label" for="student-f4-input">学生番号</label> <input
							class="form-control" type="text" id="student-f4-input" name="f4"
							placeholder="学生番号を入力してください" value="${f4}" required>

					</div>
					<div class="col-2">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>
				<p>
					<font color="#00FFFF">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</font>
				</p>
			</form>
		</section>
	</c:param>
</c:import>