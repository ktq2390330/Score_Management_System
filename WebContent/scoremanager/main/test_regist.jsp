<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.ArrayList" %>
<%
HashSet<String> displayedStudents = new HashSet<String>();
pageContext.setAttribute("displayedStudents", displayedStudents);
%>
<c:import url="/common/base.jsp">
    <c:param name="title" value="得点管理システム"/>
    <c:param name="scripts"/>
    <c:param name="content">
        <section class="mp-4">
            <h2 class="h2 fw-normal bg-secondary bg-opacity-10 py-2 px-4 mb-0">成績管理</h2><br>
            <form method="get" id="search-form">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-2">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year==f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
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
                        <label class="form-label" for="student-f4-select">回数</label>
                        <select class="form-select" id="student-f4-select" name="f4">
                            <option value="0">--------</option>
                            <c:forEach var="num" begin="1" end="10">
                                <option value="${num}" <c:if test="${num==f4}">selected</c:if>>${num}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" type="submit">検索</button>
                    </div>
                    <div id="error-message-container"></div>
                </div>
            </form>

            <c:if test="${not empty tests}">
                <div>科目:${subject_name2}(${f4}回)</div>
                <form action="TestRegistExecute.action" method="post" onsubmit="return validateForm()">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${tests}">
                                <tr>
                                    <td>${test.student.entYear}<input type="hidden" name="no" value="${test.no}"></td>
                                    <td>${test.classNum}<input type="hidden" name="classNum" value="${test.classNum}"></td>
                                    <td>${test.student.no}<input type="hidden" name="student.no" value="${test.student.no}"></td>
                                    <td>${test.student.name}<input type="hidden" name="subject.cd" value="${test.subject.cd}"></td>
                                    <td><input type="text" class="point" name="point" value="${test.point}">
                                    <div class="errorMessage2" style="color: orange;"></div></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" value="登録して終了" id="submit-button">
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>

<script>
    document.getElementById("search-form").addEventListener("submit", function(event) {
        if (!validateSearchForm()) {
            event.preventDefault();
            displaySearchErrorMessage("入学年度、クラス、科目、回数を選択してください");
        } else {
            clearSearchErrorMessage();
        }
    });

    function validateSearchForm() {
        var f1 = document.getElementById('student-f1-select').value;
        var f2 = document.getElementById('student-f2-select').value;
        var f3 = document.getElementById('student-f3-select').value;
        var f4 = document.getElementById('student-f4-select').value;

        if (f1 == 0 || f2 == 0 || f3 == 0 || f4 == 0) {
            return false;
        }
        return true;
    }

    function displaySearchErrorMessage(message) {
        var errorMessageContainer = document.getElementById('error-message-container');
        errorMessageContainer.innerHTML = '<p style="color: orange; margin-left: 20px;">' + message + '</p>';
    }

    function clearSearchErrorMessage() {
        var errorMessageContainer = document.getElementById('error-message-container');
        errorMessageContainer.innerHTML = '';
    }
</script>

<script>
    document.getElementById("submit-button").addEventListener("click", function(event) {
        if (!validatePoints()) {
            event.preventDefault();
            displayPointErrorMessage("点数は0~100の範囲で入力してください");
        } else {
            clearPointErrorMessage();
        }
    });

    function validatePoints() {
        var isValid = true;
        var points = document.querySelectorAll('input[name="point"]');

        for (var i = 0; i < points.length; i++) {
            var point = points[i].value.trim();
            var errorMessageElement = points[i].nextElementSibling;

            if (point === "" || isNaN(point) || point < 0 || point > 100) {
                errorMessageElement.innerText = "0~100の範囲で入力してください";
                errorMessageElement.style.color = "orange";
                isValid = false;
            } else {
                errorMessageElement.innerText = "";
            }
        }

        return isValid;
    }

    function displayPointErrorMessage(message) {
        var errorMessageContainer = document.getElementById('point-error-message-container');
        errorMessageContainer.innerHTML = '<p style="color: orange; margin-left: 20px;">' + message + '</p>';
    }

    function clearPointErrorMessage() {
        var errorMessageContainer = document.getElementById('point-error-message-container');
        errorMessageContainer.innerHTML = '';
    }
</script>