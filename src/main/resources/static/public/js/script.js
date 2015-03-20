$(document).ready(function(){
    $.ajax({
        method: "GET",
        url: "/phone",
        success: function(data){
            var phones = data;
            var str;
            $.each(phones, function(i, item) {
                str = "<tr>";
                str += "<td>"+item.id+"</td>";
                str += "<td>"+item.serialNumber+"</td>";
                str += "<td>"+item.number+"</td>";
                str += "<td>"+item.firstName+"</td>";
                str += "<td>"+item.lastName+"</td>";
                str += "<td>"+((item.stolen)?"Stolen":"Retrieved")+"</td>";
                str += "<td>"+((item.stolen)?"<a class=\"btn btn-info btn-xs phoneretrieve\" href=\"#\" role=\"button\">Retreive</a>":"")+"</td>";
                str += "</tr>";
                $('#phonetable').append(str);
            });
        }
    });

    $("#addform").submit(function(){
        var phoneDTO = {};
        phoneDTO.id = 0;
        phoneDTO.serialNumber = $("#SerialNumber").val();
        phoneDTO.number = $("#PhoneNumber").val();
        phoneDTO.firstName = $("#FirstName").val();
        phoneDTO.lastName = $("#LastName").val();
        phoneDTO.stolen = true;

         $.ajax({
                method: "POST",
                url: "/phone",
                data: JSON.stringify(phoneDTO),
                accepts: "application/json; charset=UTF-8",
            contentType: "application/json; charset=UTF-8",
            success: function(data){
                //console.log(data);
                var phone = data;
                var str;
                str = "<tr>";
                str += "<td>"+phone.id+"</td>";
                str += "<td>"+phone.serialNumber+"</td>";
                str += "<td>"+phone.number+"</td>";
                str += "<td>"+phone.firstName+"</td>";
                str += "<td>"+phone.lastName+"</td>";
                str += "<td>"+((phone.stolen)?"Stolen":"Retrieved")+"</td>";
                str += "<td>"+((phone.stolen)?"<a class=\"btn btn-info btn-xs phoneretrieve\" href=\"#\" role=\"button\">Retreive</a>":"")+"</td>";
                str += "</tr>";
                $('#phonetable').append(str);
            }
        });
    });

    $('#addform').hide();


    $('#addbutton').click(function(){
        $('#addform').toggle();
    });

    $(document).on("DOMNodeInserted", function(){
        phoneRetriever();
    });
});

function phoneRetriever(){
    $(".phoneretrieve").unbind("click");
    $('.phoneretrieve').click(function(){
        var $self = $(this);
        var id = ($self.parent().parent().children().first().html());
        var phoneDTO = {};
        phoneDTO.stolen = false;

        $.ajax({
            method: "PUT",
            url: "/phone/"+id,
            data: JSON.stringify(phoneDTO),
            accepts: "application/json; charset=UTF-8",
            contentType: "application/json; charset=UTF-8",
            success: function(data){
                $self.parent().parent().remove();
            }
        });
        alert("The phone {id : "+id+"} has been retrieved");
    });
}



