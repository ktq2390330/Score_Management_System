<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">
				<b>メニュー</b>
			</h2>
			<br>
			<div class="d-flex justify-content-center">
				<div
					class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #dbb;">
					<a href="StudentList.action" style="font-size: 25px;"><b>学生管理</b></a>
				</div>

				<div
					class="col d-flex flex-column align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #CCFFCC;">
<<<<<<< HEAD
					<b style="font-size: 25px;">成績管理</b> <a href="TestRegist.action" style="font-size: 25px;"><b>成績登録</b></a> <a href="#" style="font-size: 25px;"><b>成績参照</b></a>
=======
					<b>成績管理</b> <a href="TestRegist.action"><b>成績登録</b></a> <a href="TestList.action"><b>成績参照</b></a>
>>>>>>> branch 'master' of https://github.com/ktq2390330/Score_Management_System
				</div>

				<div
					class="col d-flex align-items-center justify-content-center mx-2 rounded shadow"
					style="height: 10rem; background-color: #CCCCFF;">
					<a href="SubjectList.action" style="font-size: 25px;"><b>科目管理</b></a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>
