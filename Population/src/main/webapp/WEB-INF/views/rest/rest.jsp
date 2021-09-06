<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="/assets/js/rest.js"></script>
    <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52817cb7b77a92b2ad12c59bdb661586&libraries=services"></script>
</head>

<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="resting_content">
            <h1>휴게소</h1>
            <div class="live_rest_area">
                <input type="text" id="value" placeholder="검색(예:부산)">
                <button class="search">검색</button>
                <table>
                    <thead>
                        <tr>
                            <td>대표음식</td>
                            <td>브랜드</td>
                            <td>편의시설</td>
                            <td>방행</td>
                            <td>노선명</td>
                            <td>휴게소.주유소 명</td>
                            <td>주소</td>
                            <td>전화번호</td>
                        </tr>
                    </thead>
                </table>
                <tbody>
                </tbody>
            </div>
        </div>
</body>

</html>