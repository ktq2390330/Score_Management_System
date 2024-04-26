<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                </div>
            </form>

            <c:if test="${not empty tests}">
                <div>科目:${f3}(${f4}回)</div>
                <form action="TestRegist.action" method="post">
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
                                    <td>${test.student.entYear}<input type="hidden" name="student.entYear" value="${test.student.entYear}"></td>
                                    <td>${test.classNum}<input type="hidden" name="classNum" value="${test.classNum}"></td>
                                    <td>${test.student.no}<input type="hidden" name="student.no" value="${test.student.no}"></td>
                                    <td>${test.student.name}<input type="hidden" name="student.name" value="${test.student.name}"></td>
                                    <td><input type="text" name="point" value="${test.point}"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <input type="submit" value="登録して終了">
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>