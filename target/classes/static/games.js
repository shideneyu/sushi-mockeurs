$(document).ready(function(){
   console.log("doc loaded");

    $.ajax({
       type: "GET",
        url: "/game",
        success: function (data){
            var result = "";

            result += data.forEach(function(){
                return this.name + "<br />";
            });

            $("#anyGames").html(result);
        }
    });
});