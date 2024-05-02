<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">科目管理</h2>
			<div class="my-2 text-end px-4">
				<a href="SubjectCreate.action">新規登録</a>
			</div>
			<table class="table table-hover">
				<thead>
					<tr>
						<th style="width: 20%;">科目コード</th>
						<th style="width: 40%;">科目名</th>
						<th style="width: 20%; text-align: right;"></th>
						<!-- 変更と削除のための空の列を追加 -->
						<th style="width: 20%; text-align: right;"></th>
						<!-- 変更と削除のための空の列を追加 -->

					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty subjects}">
							<c:forEach var="subject" items="${subjects}">
								<tr>
									<td><c:if test="${not empty subject.cd}">${subject.cd}</c:if></td>
									<td style="white-space: nowrap;"><c:if test="${not empty subject.name}">${subject.name}</c:if></td>
									<td style="text-align: right;"><a
										href="SubjectUpdate.action?cd=${subject.cd}">変更</a></td>
									<td style="text-align: right; padding-right: 70px;"><a
										href="SubjectDelete.action?cd=${subject.cd}">削除</a></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</section>
	</c:param>
</c:import>