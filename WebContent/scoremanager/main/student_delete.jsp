<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />

	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">学生除籍</h2>
			<form action="StudentDeleteExecute.action" method="post"
				onsubmit="return validateForm()">
<div class="form-group" style="border: none;">
    <!-- 枠線を非表示にする -->
    <label for="entyear">入学年度</label>
    <input type="text"
						class="form-control" id="entyear" name="entyear"
						value="${student.entYear}" readonly>
</div>

<div class="form-group" style="border: none;">
    <!-- 枠線を非表示にする -->
    <label for="no">学生番号</label>
     <input type="text"
						class="form-control" id="no" name="no" value="${student.no}"
						readonly>
</div>

<div class="form-group">
    <label for="name">氏名</label>
     <input type="text"
						class="form-control" id="name" name="name" value="${student.name}" readonly>
</div>

<div class="form-group">
    <label for="classnum">クラス</label>
     <input type="text"
						class="form-control" id="classnum" name="classnum" value="${student.classNum}" readonly>
</div>
<br>

				<input type="hidden" name="no" value="${param.no}" />
				<button type="submit" class="btn btn-danger">除籍</button>
			</form>


			<a href="StudentList.action">戻る</a>
		</section>
	</c:param>
</c:import>