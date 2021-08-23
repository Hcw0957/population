<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/exit.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52817cb7b77a92b2ad12c59bdb661586"></script>
</head>
<body>

    <div id="map" style="width:100%;height:400px;">
        <%@include file="/WEB-INF/views/includes/menu.jsp"%>
        <h1>고속도로 출입시설 위치정보</h1>
        
    </div>
</body>
</html>