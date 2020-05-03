
document.addEventListener("DOMContentLoaded", function(event) {

var headerDetails=$("#mainheader").html();
$("#mainheader").html("");
$("#newheader").html(headerDetails);



$("#createuser").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="createuser";
	});

$("#createadvertise").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="createadvertise";
	});


$("#home").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="home";
	});


$("#editviewuser").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="editviewUser";
	});


$("#contactus").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="contactus";
	});


$("#logout").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="logout";
	});

$("#searchbutton").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	$("#searchpageform").submit();
	});


$("#user_change_password").click(function(){
	$("#userChangesPasswordModal").modal();
	});

$("#userchangesPassword").click(function(){
	if($("#old_password").val().trim()==$("#new_password").val().trim()){
		 $("#user_password_change_msg").text("Old Password and new password cannot be same");
		 return;
	}
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	var userDetails={};
	userDetails["oldpassword"]=$("#old_password").val().trim();
	userDetails["newpassword"]=$("#new_password").val().trim();
	
	$.ajax({
		url : "userpasswordchange",
		type: "POST",
		contentType : 'application/json; charset=utf-8',
	    data : JSON.stringify(userDetails),
		success : function(result) {
			
			$("#pageloader").css("display", "none");
			$("#bodydiv").css("opacity", "1");
			
			
            $("#user_password_change_msg").text(result.Message);
            if(result.isPasswordChange=="Y")
            setTimeout(() => { location.href="logout"; }, 5000);
		},
		error: function (jqXHR, textStatus, errorThrown)
	    {
			alert(textStatus);
	    }
	});
	
	});
	
});


function openNav() {
	  document.getElementById("sidenav").style.width = "250px";
	}

	function closeNav() {
	  document.getElementById("sidenav").style.width = "0";
	}

