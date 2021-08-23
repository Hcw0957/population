<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>전국 교통정보 대시보드</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="/assets/js/index.js"></script>
</head>
<body>
    <div class="container">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <div class="dashboard_area">
            <div class="dashboard_content">
                <div class="content_left">
                    <h1>실시간 고속도로 현황</h1>
                    <canvas id="confirmed_chart" style="width:100%; height:100%"></canvas>
                </div>
                <div class="content_right">
                    <h1>실시간 고속도로 정체 현황</h1>
                    <canvas id="regional_status" style="width:100%; height:100%"></canvas>
                    <p class="RoadFirst"></p>
                </div>
                <div class="content_right">
                    <h1>실시간 고속도로 정체 현황</h1>
                    <canvas id="regional_status2" style="width:100%; height:100%"></canvas>
                    <p class="RoadFirst"></p>
                </div>
                <div class="content_right">
                    <h1>실시간 고속도로 정체 현황</h1>
                    <canvas id="regional_status3" style="width:100%; height:100%"></canvas>
                    <p class="RoadFirst"></p>
                </div>
            </div>
</body>
</html>