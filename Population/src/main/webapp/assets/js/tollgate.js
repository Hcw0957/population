$(function () {

    let icList = null;
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = { 
        center: new kakao.maps.LatLng(37.56687831123455, 126.97892390093868 ), // 지도의 중심좌표
        level: 10 // 지도의 확대 레벨
    };
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    var markers = [];

    $("#tollgate_select").change(function () {
        let route = $(this).find("option:selected").val()
        getAllroute(route)
    })
    $("#ic_select").change(function () {
        let icname = $(this).find("option:selected").val();
        icMarker(icname);
    })

    function icMarker(icname) {
        resetMarkers();
        // console.log(icname, icList);
        for(let i = 0; i< icList.length; i++) {
            if(icList[i].icName == icname) {
                addMarker(new kakao.maps.LatLng(icList[i].yvalue, icList[i].xvalue), icname);
                panTo(icList[i].yvalue, icList[i].xvalue)
            }
        }
    }
    getAllroute("all")
    function getAllroute(route) {
        if (route == "all") {
            $("#ic_select").html("<option>고속도로를 먼저 선택하세요</option>").prop("disabled", true);
            return;
        }
        $("#ic_select").prop("disabled", false)
        $("#ic_select").html("");
        $.ajax({
            type: "get",
            url: "/api/icnames?route=" + route,
            success: function (r) {
                resetMarkers();
                icList = r.list;
                var len = r.list.length;
                for(let i = 0; i < r.list.length; i++){
                    addMarker(new kakao.maps.LatLng(r.list[i].yvalue, r.list[i].xvalue), r.list[i].icName);
                }
                panTo(r.list[len-1].yvalue, r.list[len-1].xvalue);
                
                $("#ic_select").html("");
                for (let i = 0; i < r.list.length; i++) {
                    let tag =
                        '<option class ="ic" value = "' + r.list[i].icName + '">' + r.list[i].icName + '</option>'
                    $("#ic_select").append(tag);
                }
            }
        })
        
    }
    function resetMarkers() {
        for(let i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers = new Array();
    }
    function addMarker(position, title) {
        var marker = new kakao.maps.Marker({
            position: position
        });
        marker.setMap(map);
        markers.push(marker);

        var iwContent = '<div style="padding:5px;">'+title+'</div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent
        });
        kakao.maps.event.addListener(marker, 'mouseover', function() {
            infowindow.open(map, marker);
        });
        kakao.maps.event.addListener(marker, 'mouseout', function() {
            infowindow.close();
        });
    }
    function panTo(lat, lng) {
        var moveLatLon = new kakao.maps.LatLng(lat, lng);
        map.panTo(moveLatLon);            
    }
})





