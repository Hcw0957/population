$(function () {
    //실시간 문자서비스
    $("#timeSmsList_select").change(function(){
        let roadNM = $(this).find("option:selected").val()
        console.log(roadNM)
        getInternationalInfo(roadNM) 
    })

    $("#value1").keydown(function (e) {
        if (e.keyCode != 13) return;
        $(".search").trigger("click");
        $(".live_smslist_area tbody").html("")
        let start = $("#value1").val()
        console.log(start)
        getInternationalInfo(start);
        $.ajax({
            type: "get",
                url: "/api/sms?start=" + start,
                success: function (r) {
                    console.log(r);
                    $(".live_confirm_area").html("")
                    for (let i = 0; i < r.data.length; i++) {
                        let tag =
                            '<tr>' +
                            '<td>' + r.data[i].accDate + '</td>' +
                            '<td>' + r.data[i].accHour + '</td>' +
                            '<td>' + r.data[i].accType + '</td>' +
                            '<td>' + r.data[i].roadNM + '</td>' +
                            '<td>' + r.data[i].smsText + '</td>' +
                            '<td>' + r.data[i].startEndTypeCode + '</td>' +
                            '</tr>'
                        $(".live_confirm_area").append(tag);
                    }
                    
                }
            })
    })
    getInternationalInfo("all")
    function getInternationalInfo(roadNM) {
        $.ajax({
        type: "get",
            url: "/api/smslist?roadNM=" + roadNM,
            success: function (r) {
                console.log(r);
                $(".live_confirm_area").html("")
                for (let i = 0; i < r.data.length; i++) {
                    let tag =
                        '<tr>' +
                        '<td>' + r.data[i].accDate + '</td>' +
                        '<td>' + r.data[i].accHour + '</td>' +
                        '<td>' + r.data[i].accType + '</td>' +
                        '<td>' + r.data[i].roadNM + '</td>' +
                        '<td>' + r.data[i].smsText + '</td>' +
                        '<td>' + r.data[i].startEndTypeCode + '</td>' +
                        '</tr>'
                    $(".live_confirm_area").append(tag);
                }
            
               
            }
        })
    }
 

    //매시간별 도로 교통량
    $.ajax({
        type: "get",
        url: "/api/sector",
        success: function (r) {
            console.log(r);
            $.ajax({
                type: "get",
                url: "/api/gong",
                success: function (g) {
                    let sec = new Array();
                    let go = new Array();
                    for (let i = 0; i < r.data.length; i++) {
                        sec.push(r.data[i].amount);
                        go.push(g.data[i].amount);
                    }
                    console.log(go)
                    console.log(sec)
                    let ctx = $("#traffic_chart");
                    // let list = r.data.amount
                    let traffic_chart = new Chart(ctx, {
                        type: "pie",
                        options: {
                            responsive: false,
                        },
                        data: {
                            labels: ["도공", "민자"],
                            datasets: [{
                                label: "도공/민자",
                                data: [go, sec],
                                backgroundColor: ["rgba(225,0,0,0.7)", "rgba(20,250,0,0.7)"]
                            }]
                        }
                    })
                }
            })
        }
    })







    //노선별 정보 및 사용여부
    $("#value2").keydown(function (e) {
        if (e.keyCode != 13) return;

        $(".search").trigger("click");
        $(".emergency_table tbody").html("")
        let list = $("#value2").val()
        console.log(list)
        $.ajax({
            type: "get",
            url: "/api/route?list=" + list,
            success: function (r) {
                console.log(r);
                $(".live_routeList_area").html("");
                for (let i = 0; i < r.data.length; i++) {
                    let tag =
                        '<tr>' +
                        '<td class="edpntDstnc">' + r.data[i].edpntDstnc + '</td>' +
                        '<td class="routeNm">' + r.data[i].routeNm + '</td>' +
                        '<td class="routeNo">' + r.data[i].routeNo + '</td>' +
                        '<td class="stptDstnc">' + r.data[i].stptDstnc + '</td>' +
                        '<td class="totExtnsDstne">' + r.data[i].totExtnsDstne + '</td>' +
                        '<td class="useYn">' + r.data[i].useYn + '</td>' +
                        '</tr>'
                    $(".live_routeList_area").append(tag);
                }
            }
        })
    })
    getRouteList("");
    $("#routeList_select").change(function () {
        let list = $(this).find("option:selected").val();
        console.log(list)
        getRouteList(list);
    })

    function getRouteList(route) {
        $.ajax({
            type: "get",
            url: "/api/route?list=" + route,
            success: function (r) {
                console.log(r);
                $(".live_routeList_area").html("");
                for (let i = 0; i < r.data.length; i++) {
                    let tag =
                        '<tr>' +
                        '<td class="edpntDstnc">' + r.data[i].edpntDstnc + '</td>' +
                        '<td class="routeNm">' + r.data[i].routeNm + '</td>' +
                        '<td class="routeNo">' + r.data[i].routeNo + '</td>' +
                        '<td class="stptDstnc">' + r.data[i].stptDstnc + '</td>' +
                        '<td class="totExtnsDstne">' + r.data[i].totExtnsDstne + '</td>' +
                        '<td class="useYn">' + r.data[i].useYn + '</td>' +
                        '</tr>'
                    $(".live_routeList_area").append(tag);

                }
            }
        })
    }
})