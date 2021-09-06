$(function () {
    $.ajax({
        type: "get",
        url: "/api/resting",
        success: function (r) {
            console.log(r);
            for (let i = 0; i < r.data.length; i++) {
                let tag =
                ' <tr>' +
                '<td>' + r.data[i].batchMenu + '</td>' +
                '<td>' + r.data[i].brand + '</td>' +
                ' <td>' + r.data[i].convenience + '</td>' +
                ' <td>' + r.data[i].direction + '</td>' +
                '<td>' + r.data[i].routeName + '</td>' +
                '<td>' + r.data[i].serviceAreaName + '</td>' +
                ' <td>' + r.data[i].svarAddr + '</td>' +
                ' <td>' + r.data[i].telNo + '</td>' +
                '<td id="staticMap' + r.data[i].seq + '"style="width:300px;height:150px;"></td>' +
                ' </tr>'
                $(".live_rest_area").append(tag);
                // 주소-좌표 변환 객체를 생성합니다
                var geocoder = new kakao.maps.services.Geocoder();
                
                // 주소로 좌표를 검색합니다
                geocoder.addressSearch(r.data[i].svarAddr, function (result, status) {
                    
                    // 정상적으로 검색이 완료됐으면 
                    if (status === kakao.maps.services.Status.OK) {
                        
                        var coords = new kakao.maps.LatLng(result[0].x, result[0].y);
                        var markers = [{
                            position: new kakao.maps.LatLng(coords.La, coords.Ma),
                            text: r.data[i].itsBroNm // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다     
                        }];

                        var staticMapContainer = document.getElementById('staticMap' + r.data[i].seq) // 이미지 지도를 표시할 div  
                        var staticMapOption = {
                            center: new kakao.maps.LatLng(coords.La, coords.Ma), // 이미지 지도의 중심좌표
                            level: 6, // 이미지 지도의 확대 레벨
                            marker: markers // 이미지 지도에 표시할 마커 
                        };
                        // 이미지 지도를 생성합니다
                        var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
                    }
                });
            }
        }
    })
    $(".search").click(function () {
        let region = $("#value").val()
        $("live_rest_area").html("")
        console.log(region)
        $.ajax({
            type: "get",
            url: "/api/restingsearch?region="+region,
            success: function (r) {
                console.log(r);
                for (let i = 0; i < r.data.length; i++) {
                    let tag =
                    ' <tr>' +
                    '<td>' + r.data[i].batchMenu + '</td>' +
                    '<td>' + r.data[i].brand + '</td>' +
                    ' <td>' + r.data[i].convenience + '</td>' +
                    ' <td>' + r.data[i].direction + '</td>' +
                    '<td>' + r.data[i].routeName + '</td>' +
                    '<td>' + r.data[i].serviceAreaName + '</td>' +
                    ' <td>' + r.data[i].svarAddr + '</td>' +
                    ' <td>' + r.data[i].telNo + '</td>' +
                    '<td id="staticMap' + r.data[i].seq + '"style="width:300px;height:150px;"></td>' +
                    ' </tr>'
                    $(".live_rest_area").append(tag);
                    // 주소-좌표 변환 객체를 생성합니다
                    var geocoder = new kakao.maps.services.Geocoder();
                    
                    // 주소로 좌표를 검색합니다
                    geocoder.addressSearch(r.data[i].svarAddr, function (result, status) {
                        
                        // 정상적으로 검색이 완료됐으면 
                        if (status === kakao.maps.services.Status.OK) {
                            
                            var coords = new kakao.maps.LatLng(result[0].x, result[0].y);
                            var markers = [{
                                position: new kakao.maps.LatLng(coords.La, coords.Ma),
                                text: r.data[i].itsBroNm // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다     
                            }];

                            var staticMapContainer = document.getElementById('staticMap' + r.data[i].seq) // 이미지 지도를 표시할 div  
                            var staticMapOption = {
                                center: new kakao.maps.LatLng(coords.La, coords.Ma), // 이미지 지도의 중심좌표
                                level: 6, // 이미지 지도의 확대 레벨
                                marker: markers // 이미지 지도에 표시할 마커 
                            };
                            // 이미지 지도를 생성합니다
                            var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
                        }
                    });
                }
            }
        })
        
    })
})