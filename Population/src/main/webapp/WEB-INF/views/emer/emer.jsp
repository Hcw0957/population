<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>비상 연결로</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="/assets/js/emer.js"></script>
</head>

<body>
    <div class="emergency_data">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <h1>비상 우회도로 안내</h1>
        <div class="live_rest_area">
            <input type="text" id="value" placeholder="검색(예:경주)">
            <button class="search">검색</button>
            
            <div class="right_area">
                <div class="content_head">
                    <select id="emergency_select">
                        <option value="all">전체</option>
                        <option value="경부선">경부선</option>
                        <option value="남해선(영암순천)">남해선(영암순천)</option>
                        <option value="남해선(순천부산)">남해선(순천부산)</option>
                        <option value="광주대구선">광주대구선</option>
                    </select>
                </div>
            </div>
            <table class="emergency_table">
                <thead>
                    <tr>
                        <td>전방IC </td>
                        <td>후방IC </td>
                        <td>방향 </td>
                        <td>우회도로 </td>
                        <td>노선 </td>
                        <td>이정(km) </td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
</body>

</html>