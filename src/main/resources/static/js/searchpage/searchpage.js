function openNav() {
	document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
}

document.addEventListener("DOMContentLoaded", function(event) {

	$("#agenumber").text($("#age").val());
	$("#age").on('change', function() {
		$("#agenumber").text($("#age").val());
	});
	
	w3.includeHTML();

});



function loadFullProfile(id)
{

	 $.ajax({
		url : "loadfullprofileofuser/"+id,
		type: "POST",
		success : function(result) {
			$("#fullprofilemodaltitle").text(result['firstName']+" "+result['lastName']+" Bio Data")
			for(var key in result){ 
				if(key!="fileNameList")
				$("#"+key+"_mdl").text(result[key]);
			}
			
			
			
			if(result.fileNameList.length!=0){
				$('#userimages').text("");
				result.fileNameList.forEach(function(imagePath){
					$('#userimages').prepend($('<img>',{class:'userimages',src:imagePath}));
				});
			}else
			{
				$('#userimages').text("Photos not Available");
			}
			
            $("#inputhiddenUserId").val(result['id']);
            $('#download').text("");
            $("#download").prepend("<a href=\"download?id="+result['id']+"\" id=\"downloaduserdata\" >Download</a>");
            $("#fullprofilemodal").modal();
		},
		error: function (jqXHR, textStatus, errorThrown)
	    {
	 
	    }
	});
	
}

function email()
{
	var id=$("#inputhiddenUserId").val();
	
	 $.ajax({
			url : "email/"+id,
			type: "POST",
			success : function(result) {
				$('#fullprofilemodal').modal('hide');
				$("#messageModalBody").text("");
				$("#messageModalBody").text(result);
				$("#messageModal").modal();
			},
			error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		});




}



