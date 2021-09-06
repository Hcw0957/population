<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>실시간 문자 서비스</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="/assets/js/timesms.js"></script>
</head>

<body>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>
    <div class="timeSmsList_area">
        <h1>실시간 문자 서비스 내용</h1>
        <div class="live_smslist_area">
            <input type="text" id="value1" placeholder="방향(예:부산)">
            <button class="search">검색</button>

            <div class="timeSmsList_head1s">
                <select id="timeSmsList_select">
                    <option id="roadNM" value="all">전체</option>
                    <c:forEach items="${list}" var="data">
                        <option value="${data}">${data}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="content_left">
            <table>
                <thead>
                    <tr>
                        <td>전송 날짜</td>
                        <td>전송 시간</td>
                        <td>돌발 내용</td>
                        <td>노선</td>
                        <td>문자내용</td>
                        <td>노선방향</td>
                    </tr>
                </thead>
                <tbody class="live_confirm_area">
                </tbody>
            </table>
        </div>
    </div>

    <div class="dashboard_route">
        <h1>노선정보 및 사용가능 여부</h1>
        <div class="live_route_area">
            <input type="text" id="value2" placeholder="검색(예:경부선)">
            <button class="search">검색</button>
            <div class="routeList_head1s">
                <select id="routeList_select">
                    <option id="name" value2="">전체</option>
                    <c:forEach items="${list}" var="item">
                        <option value="${item}">${item}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="route_left">
                <table>
                    <thead>
                        <tr>
                            <td>종점이정</td>
                            <td>노선 이름</td>
                            <td>노선 번호</td>
                            <td>기점이정</td>
                            <td>총 연장거리</td>
                            <td>사용여부(Y/N)</td>
                        </tr>
                    </thead>
                    <tbody class="live_routeList_area">
                    </tbody>
                </table>
            </div>
            <div class="traffic_left">
                <h1>매시간 전국 교통량</h1>
                <table>
                    <thead>
                        <tr>
                            <canvas id="traffic_chart" style="width: 100%; height:100%;"></canvas>
                        </tr>
                    </thead>
                </table>
            </div>

        </div>
    </div>

    </div>
</body>

</html>