<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>톨게이트 IC</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/tollgate.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52817cb7b77a92b2ad12c59bdb661586"></script>
    
</head>
<body>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>
    <div class="tollgate_area">
        <h1>원하는 IC 톨게이트 </h1>
        <div class="tollgate_head1s">
            <select id= "tollgate_select">
                <option id ="route" value = "all">전체</option>
                <c:forEach items = "${route}" var="list">
                    <option class ="route">${list.routeName}</option>
                </c:forEach>
            </select>
            <select id= "ic_select">
                <option id ="ic" value = "all">전체</option>
            </select>
        </div>
    </div>
    <div class="map_area">
        <div id="map" style="width:100%;height:350px;"></div>
    </div>
</body>
</html>