<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">
    <title>学生管理システム</title>
</head>
<body>
    <!-- ログイン情報を確認し、遷移先を決定 -->
    <c:choose>
        <c:when test="${not empty sessionScope.user}">
            <!-- ログインしている場合はメニューに遷移 -->
            <meta http-equiv="refresh" content="1;url=main/Menu.action">
        </c:when>
        <c:otherwise>
            <!-- ログインしていない場合はログインページに遷移 -->
            <meta http-equiv="refresh" content="1;url=Login.action">
        </c:otherwise>
    </c:choose>

    <h3 style="text-align: center;">ログイン情報を取得しています</h3>
</body>
</html>
