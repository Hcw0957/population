<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="/assets/js/construct.js"></script>
</head>

<body>
    <div class="construction_list">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <h1>고속도로 공사 현황</h1>
        <div class="right_area">
            <div class="content_head">
                <select id="continent_select">
                    <option value="시공">시공</option>
                    <option value="준공">준공</option>
                </select>
            </div>
            <table class="country_table">
                <thead>
                    <tr>
                        <td>공사 현황</td>
                        <td>공사시점 주소</td>
                        <td>공사종점 주소</td>
                        <td>공사기간</td>
                        <td>준공날짜</td>
                        <td>노선명</td>
                        <td>준공날짜</td>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

    </div>
</body>

</html>