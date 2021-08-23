$(function(){
    $.ajax({
        type:"get",
        url:"/api/road/today",
        success:function(r) {
            console.log(r);
            let routeName = new Array();
            let defCnt = new Array();
            $(".region-tbody").html("")
            for(let i=0; i<r.data.length; i++) {
                let road = r.data[i].routeName;
                let cnt = r.data[i].cnt;
                routeName.push(road);
                defCnt.push(cnt);
                let page = Math.floor(i/3);
                let tag =
                '<tr>'+
                    '<td>'+r.data[i].routeName+'</td>'+
                    '<td>'+r.data[i].defCnt+'</td>'+
                '</tr>'
                $(".region-tbody").eq(page).append(tag);
            }
            $(".region-tbody").eq(0).addClass("active");
            $("#routeName").html(r.data.routeName);
            let ctx = $("#confirmed_chart");
            let roadChart = new Chart(ctx, {
                type:'bar',
                options:{
                    responsive:false,
                    indexAxis:"y",
                    plugins: {
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    var label = context.dataset.label || '';
                                    if (label) {
                                        label += ': '+context.parsed.x + "대";
                                    }
                                    return label;
                                }
                            }
                        }
                    }
                },
                data:{
                    labels:routeName,
                    datasets:[{
                        label:"차량 수",
                        data:defCnt,
                        backgroundColor:['rgba(255,30,30,0.7)',]
                    }]
                }
            });
        }
    })
    $.ajax({
        type:"get",
        url:"/api/congest",
        success:function(r) {
            console.log(r);
            let routeName = new Array();
            let trafficAmount = new Array();
            let timeAvg = new Array();
            let speed = new Array();
            for(let i=0; i<r.data.length; i++) {
                let road = r.data[i].routeName;
                let tra = r.data[i].amount;
                let time = r.data[i].time;
                let spe = r.data[i].speedAvg;
                routeName.push(road);
                trafficAmount.push(Math.floor(tra));
                timeAvg.push(Math.floor(time));
                speed.push(Math.floor(spe));
                let page = Math.floor(i/3);
                let tag =
                '<tr>'+
                    '<td>'+r.data[i].routeName+'</td>'+
                    '<td>'+r.data[i].amount+'</td>'+
                    '<td>'+r.data[i].time+'</td>'+
                    '<td>'+r.data[i].speedAvg+'</td>'+
                '</tr>'
                $(".region-tbody").eq(page).append(tag);
            }
            $(".region-tbody").eq(0).addClass("active");
            $("#routeName").html(r.data.routeName);
            let ctx2 = $("#regional_status");
            let roadChart = new Chart(ctx2, {
                type:'bar',
                options:{
                    responsive:false,
                    indexAxis:"y",
                    plugins: {
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    var label = context.dataset.label || '';
                                    if (label) {
                                        label += ': '+context.parsed.x + "Km/H";
                                    }
                                    return label;
                                }
                            }
                        }
                    }
                },
                data:{
                    labels:routeName,
                    datasets:[{
                        label:"정체구간 평균속도",
                        data:trafficAmount,
                        backgroundColor:['rgba(25,30,30,0.7)']
                    },
                    ]
                }
            });
            let roadChart2 = new Chart($("#regional_status2"), {
                type:'bar',
                options:{
                    responsive:false,
                    indexAxis:"y",
                    plugins: {
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    var label = context.dataset.label || '';
                                    if (label) {
                                        label += ': '+context.parsed.x + "분";
                                    }
                                    return label;
                                }
                            }
                        }
                    }
                },
                data:{
                    labels:routeName,
                    datasets:[
                    {
                        label:"통행 시간",
                        data:timeAvg,
                        backgroundColor:['rgba(25,130,30,0.4)']
                    },
                    ]
                }
            });
            let roadChart3 = new Chart($("#regional_status3"), {
                type:'bar',
                options:{
                    responsive:false,
                    indexAxis:"y",
                    plugins: {
                        tooltip: {
                            callbacks: {
                                label: function(context) {
                                    var label = context.dataset.label || '';
                                    if (label) {
                                        label += ': '+context.parsed.x + "Km/H";
                                    }
                                    return label;
                                }
                            }
                        }
                    }
                },
                data:{
                    labels:routeName,
                    datasets:[
                    {
                        label:"평균속도",
                        data:speed,
                        backgroundColor:['rgba(5,30,230,0.5)']
                    }]
                }
            });
        }
    })
});
