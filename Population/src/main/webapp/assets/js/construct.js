$(function () {
    $("#continent_select").change(function(){
        let continent = $("#continent_select").find("option:selected").val()
        console.log(continent)
        getInternationalInfo(continent)
    })
    getInternationalInfo("시공")
    function getInternationalInfo(continent){
        $.ajax({
            type:"get",
            url: "/api/construt?sigong="+continent,
            success: function (r) {
                console.log(r);
                $(".country_table tbody").html("")
                if (r.data != null) {
                    for (let i = 0; i < r.data.length; i++) {
                        
                        let tag =
                            '<tr>' +
                            '<td id = "cmcnCstrClss">' + r.data[i].cmcnCstrClss + '</td>' +
                            '<td id = "cnstnStpntAddr">' + r.data[i].cnstnStpntAddr + '</td>' +
                            '<td id = "cnstnEnpntAddr">' + r.data[i].cnstnEnpntAddr + '</td>' +
                            '<td id = "cnstnTerm">' + r.data[i].cnstnTerm + '</td>' +
                            '<td id = "cmcnDate">' + r.data[i].cmcnDate + '</td>' +
                            '<td id = "routeName">' + r.data[i].routeName + '</td>' +
                            '<td id = "sectionName">' + r.data[i].sectionName + '</td>' +
                            '</tr>'
                        $(".country_table tbody").append(tag);
                    }
                }
            }
        })
    }
})

function comma(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}