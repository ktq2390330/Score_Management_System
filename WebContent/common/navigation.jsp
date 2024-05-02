<%-- サイドバー --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<ul class="nav nav-pills flex-column mb-auto px-4" classification="div">
    <li class="nav-item my-3"><a href="Menu.action" event="5">メニュー</a></li>
    <li class="nav-item mb-3"><a href="StudentList.action" event="10"><link>学生管理</link></a></li>
    <li class="nav-item"> <label>成績管理</label></li>
    <li class="nav-item mx-3 mb-3"><a href="TestRegist.action" event="20"><link>成績登録</link></a></li>
    <li class="nav-item mx-3 mb-3"><a href="TestList.action" event="30"><link>成績参照</link></a></li>
    <li class="nav-item mb-3"><a href="SubjectList.action" event="40"><link>科目管理</link></a></li>
    <li class="nav-item mb-3"><a href="TeacherList.action">教員一覧</a></li>
</ul>
