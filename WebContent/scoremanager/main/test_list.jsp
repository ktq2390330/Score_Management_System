<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashSet" %>
<%
HashSet<String> displayedStudents = new HashSet<String>();
pageContext.setAttribute("displayedStudents", displayedStudents);
%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="mp-4">
            <c:if test="${empty take}">
                <h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-1">
                    <b>成績参照</b>
                </h2>
            </c:if>
            <c:if test="${not empty take}">
                <h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-1">
                    <b>成績一覧(科目)</b>
                </h2>
            </c:if>
            <form method="get">
                <div class="row border mx-5 mb-6 py-3 align-items-center rounded"id="filter">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;科目情報&nbsp;&nbsp;&nbsp;
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
					<div class="col-4">
    					<label class="form-label" for="student-f3-select">科目</label>
    						<select class="form-select" id="student-f3-select" name="f3">
        					<option value="0">--------</option>
        					<c:forEach var="subject" items="${subject_cdset}" varStatus="loop">
            					<option value="${subject}" <c:if test="${subject==f3}">selected</c:if>>${subject_nameset[loop.index]}</option>
        					</c:forEach>
    					</select>
					</div>
					<div class="col-2">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div id="error-message-container"></div>
				</div>
            </form>
            <form action="TestList.action" method="post">
                <div class="row border mx-5 mb-6 py-3 align-items-center rounded"id="filter">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生情報&nbsp;&nbsp;&nbsp;
						<div class="col-5">
							<label class="form-label" for="student-f4-input">学生番号</label> <input
								class="form-control" type="text" id="student-f4-input" name="f4"
								placeholder="学生番号を入力してください" value="${f4}" required>
						</div>
						<div class="col-2">
							<button class="btn btn-secondary" id="filter-button">検索</button>
						</div>
				</div>
            </form>
            <c:choose>
                <c:when test="${tests.size() > 0}">
                    <div>科目: ${subject_name2}</div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th style="width: 20%;">入学年度</th>
                                <th style="width: 20%;">クラス</th>
                                <th style="width: 20%;">学生番号</th>
                                <th style="width: 20%;">氏名</th>
                                <th style="width: 10%;">1回</th>
                                <th style="width: 10%;">2回</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="student" items="${students}">
                            	<c:if test="${not displayedStudents.contains(student.no)}">
                                    <tr>
                                        <td>${student.entYear}</td>
                                        <td>${student.classNum}</td>
                                        <td>${student.no}</td>
                                        <td>${student.name}</td>
                                       	<c:set var="testPoint1" value="&nbsp;&nbsp;-" />
                                        <c:set var="testPoint2" value="&nbsp;&nbsp;-" />
                                        <c:forEach var="test" items="${tests}">
                                            <c:if test="${test.student.no eq student.no}">
                                                <c:choose>
                                                    <c:when test="${test.no eq 1}">
                                                        <c:set var="testPoint1" value="&nbsp;${test.point}" />
                                                    </c:when>
                                                    <c:when test="${test.no eq 2}">
                                                        <c:set var="testPoint2" value="&nbsp;${test.point}" />
                                                    </c:when>
                                                </c:choose>
                                            </c:if>
                                        </c:forEach>
                                        <td>${testPoint1}</td>
                                        <td>${testPoint2}</td>
                                    </tr>
                                    <c:set var="dummy" value="${displayedStudents.add(test.student.no)}" />
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <c:if test="${not empty take}">
                        <div>学生情報が存在しませんでした</div>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:if test="${empty take}">
                <p>
                    <font color="#00FFFF">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</font>
                </p>
            </c:if>
        </section>
    </c:param>
</c:import>

<script>
    document.getElementById("filter-button").addEventListener("click", function(event) {
        if (!validateForm()) {
            event.preventDefault();
            displayErrorMessage("入学年度とクラスと科目を選択してください");
        } else {
            clearErrorMessage();
        }
    });

    function validateForm() {
        var f1 = document.getElementById('student-f1-select').value;
        var f2 = document.getElementById('student-f2-select').value;
        var f3 = document.getElementById('student-f3-select').value;

        if (f1 == 0 || f2 == 0 || f3 == 0) {
            return false;
        }
        return true;
    }

    function displayErrorMessage(message) {
        var errorMessageContainer = document.getElementById('error-message-container');
        errorMessageContainer.innerHTML = '<p style="color: orange; margin-left: 20px;">' + message + '</p>';
    }

    function clearErrorMessage() {
        var errorMessageContainer = document.getElementById('error-message-container');
        errorMessageContainer.innerHTML = '';
    }
</script>
