$(function () {
    $("#emergency_select").change(function(){
        let routeNm = $("#emergency_select").find("option:selected").val()
        console.log(routeNm)
        getInternationalInfo(routeNm) 
    })
    getInternationalInfo("all")
    function getInternationalInfo(routeNm){
        $.ajax({
            type:"get",
            url: "/api/emer?routeNm="+routeNm,
            success: function (r) {
                console.log(r);
                $(".emergency_table tbody").html("")
                if (r.data != null) {
                    for (let i = 0; i < r.data.length; i++) {
                        if(r.data[i].frwdIc == null || r.data[i].bgwdIc == null){
                            r.data[i].frwdIc = "국도"
                            r.data[i].bgwdIc = "국도"
                        let tag =
                            '<tr>' +
                            '<td id = "bgwdIc">' + r.data[i].bgwdIc + '</td>' +
                            '<td id = "frwdIc">' + r.data[i].frwdIc + '</td>' +
                            '<td id = "drveDrctNm">' + r.data[i].drveDrctNm + '</td>' +
                            '<td id = "bypsRoad">' + r.data[i].bypsRoad + '</td>' +
                            '<td id = "routeNm">' + r.data[i].routeNm + '</td>' +
                            '<td id = "shift">' + r.data[i].shift + '</td>' +
                            '</tr>'
                        $(".emergency_table tbody").append(tag);
                        }
                        else{
                        let tag =
                            '<tr>' +
                            '<td id = "bgwdIc">' + r.data[i].bgwdIc + '</td>' +
                            '<td id = "frwdIc">' + r.data[i].frwdIc + '</td>' +
                            '<td id = "drveDrctNm">' + r.data[i].drveDrctNm + '</td>' +
                            '<td id = "bypsRoad">' + r.data[i].bypsRoad + '</td>' +
                            '<td id = "routeNm">' + r.data[i].routeNm + '</td>' +
                            '<td id = "shift">' + r.data[i].shift + '</td>' +
                            '</tr>'
                        $(".emergency_table tbody").append(tag);
                        }
                    }
                }
            }
        })
    }
    $("#value").keydown(function(e){
        if(e.keyCode != 13) return;
        
        $(".search").trigger("click");
        $(".emergency_table tbody").html("")
            let frwdIc = $("#value").val()
        console.log(frwdIc)
        $.ajax({
            type:"get",
            url:"/api/selectEmer?frwdIc="+frwdIc,
            success:function(r){
                console.log(r);
                if (r.list != null) {
                    for (let i = 0; i < r.list.length; i++) {
                        if(r.list[i].bgwdIc == null){
                            r.list[i].bgwdIc = "국도"
                        let tag =
                            '<tr>' +
                            '<td id = "bgwdIc">' + r.list[i].bgwdIc + '</td>' +
                            '<td id = "frwdIc">' + r.list[i].frwdIc + '</td>' +
                            '<td id = "drveDrctNm">' + r.list[i].drveDrctNm + '</td>' +
                            '<td id = "bypsRoad">' + r.list[i].bypsRoad + '</td>' +
                            '<td id = "routeNm">' + r.list[i].routeNm + '</td>' +
                            '<td id = "shift">' + r.list[i].shift + '</td>' +
                            '</tr>'
                        $(".emergency_table tbody").append(tag);
                        }
                        else{
                        let tag =
                            '<tr>' +
                            '<td id = "bgwdIc">' + r.list[i].bgwdIc + '</td>' +
                            '<td id = "frwdIc">' + r.list[i].frwdIc + '</td>' +
                            '<td id = "drveDrctNm">' + r.list[i].drveDrctNm + '</td>' +
                            '<td id = "bypsRoad">' + r.list[i].bypsRoad + '</td>' +
                            '<td id = "routeNm">' + r.list[i].routeNm + '</td>' +
                            '<td id = "shift">' + r.list[i].shift + '</td>' +
                            '</tr>'
                        $(".emergency_table tbody").append(tag);
                        }
                    }
                }
            }
        })
    })
    
})
