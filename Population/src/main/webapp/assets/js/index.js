// $(function () {
//     $.ajax({
//         type: "get",
//         url: "/api/sms",
//         success: function (r) {
//             console.log(r);
//             $(".live_confirm_area").html("")
//             for (let i = 0; i < r.data.length; i++) {
//                 let tag =
//                     '<tr>' +
//                     '<td>' + r.data[i].accDate + '</td>' +
//                     '<td>' + r.data[i].accHour + '</td>' +
//                     '<td>' + r.data[i].accType + '</td>' +
//                     '<td>' + r.data[i].roadNM + '</td>' +
//                     '<td>' + r.data[i].smsText + '</td>' +
//                     '<td>' + r.data[i].startEndTypeCode + '</td>' +
//                     '</tr>'
//                 $(".live_confirm_area").append(tag);
//             }
//         }
//     })
//     $.ajax({
//         type:"get",
//         url: "/api/trafficall",
//         success:function(r){
//             console.log(r);
//             $(".dashboard_traffic").html("")
//             for(let i=0; i < r.data.length; i++){
//                 let tag = 
//                 '<tr>'+
//                     '<td>' +r.data[i].exDivName+'</td>'+
//                     '<td>' +r.data[i].total+'</td>'+
//                 '</tr>'
//             $(".traffic_left").append(tag);
//             }
//         }
//     })



//     $("#value").keydown(function(e){
//         if(e.keyCode != 13) return;
//         $(".search").trigger("click");
//         $(".emergency_table tbody").html("")
//             let list = $("#value").val()
//         console.log(list)
//         $.ajax({
//             type:"get",
//             url:"/api/route?list="+list,
//             success:function(r){
//                 console.log(r);
//                 $(".live_routeList_area").html("");
//                 for(let i=0; i<r.data.length; i++){
//                     let tag = 
//                     '<tr>'+
//                     '<td class="edpntDstnc">'+r.data[i].edpntDstnc+'</td>'+
//                     '<td class="routeNm">'+r.data[i].routeNm+'</td>'+
//                     '<td class="routeNo">'+r.data[i].routeNo+'</td>'+
//                     '<td class="stptDstnc">'+r.data[i].stptDstnc+'</td>'+
//                     '<td class="totExtnsDstne">'+r.data[i].totExtnsDstne+'</td>'+
//                     '<td class="useYn">'+r.data[i].useYn+'</td>'+
//                     '</tr>'
//                     $(".live_routeList_area").append(tag);
                    
//                 }
//             }
//         })
//     })
//     $("#routeList_select").change(function(){
//         let list = $(this).find("option:selected").val();
//         console.log(list)
//         $.ajax({
//             type:"get",
//             url:"/api/route?list="+list,
//             success:function(r){
//                 console.log(r);
//                 $(".live_routeList_area").html("");
//                 for(let i=0; i<r.data.length; i++){
//                     let tag = 
//                     '<tr>'+
//                     '<td class="edpntDstnc">'+r.data[i].edpntDstnc+'</td>'+
//                     '<td class="routeNm">'+r.data[i].routeNm+'</td>'+
//                     '<td class="routeNo">'+r.data[i].routeNo+'</td>'+
//                     '<td class="stptDstnc">'+r.data[i].stptDstnc+'</td>'+
//                     '<td class="totExtnsDstne">'+r.data[i].totExtnsDstne+'</td>'+
//                     '<td class="useYn">'+r.data[i].useYn+'</td>'+
//                     '</tr>'
//                     $(".live_routeList_area").append(tag);
                    
//                 }
//             }
//         })
//     })
// })