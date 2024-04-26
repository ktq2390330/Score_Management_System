<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">学生情報削除</h2>
			<form action="StudentDeleteExecute.action" method="post"
				onsubmit="return validateForm()">
<div class="form-group" style="border: none;">
    <!-- 枠線を非表示にする -->
    <label for="entyear">入学年度</label>
    <p class="form-control-static" id="entyear">&nbsp;&nbsp;&nbsp;&nbsp;${student.entYear}</p>
</div>

<div class="form-group" style="border: none;">
    <!-- 枠線を非表示にする -->
    <label for="no">学生番号</label>
    <p class="form-control-static" id="no">&nbsp;&nbsp;&nbsp;&nbsp;${student.no}</p>
</div>

<div class="form-group">
    <label for="name">氏名</label>
    <p class="form-control-static" id="name">&nbsp;&nbsp;&nbsp;&nbsp;${student.name}</p>
</div>

<div class="form-group">
    <label for="classnum">クラス</label>
    <p class="form-control-static" id="classnum">&nbsp;&nbsp;&nbsp;&nbsp;${student.classNum}</p>
</div>

				<input type="hidden" name="no" value="${param.no}" />
				<button type="submit" class="btn btn-danger">削除</button>
			</form>


			<a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>