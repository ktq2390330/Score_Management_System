<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title" value="得点管理システム" />
	<c:param name="scripts"></c:param>
	<c:param name="content">
		<section class="mp-4">
			<h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">教員一覧</h2>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>氏名</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty teachers}">
							<c:forEach var="teacher" items="${teachers}">
								<tr>
									<td><c:if test="${not empty teacher.name}">${teacher.name}</c:if></td>

								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
				</tbody>
			</table>
		</section>
	</c:param>
</c:import>