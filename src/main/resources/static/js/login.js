	var emailId="";
	var isEmailSend=false;
document.addEventListener("DOMContentLoaded", function(event) {
	
	$("#signinbtn").click(function(){
		submitLogin();
	});
	
		function submitLogin() {
		for (var i = 0; $("#loginform")[0].length > i; i++) {
			var id = $("#loginform")[0][i].id;
			if ($("#" + id).val().trim() == "") {
				alert(id + " cannot be blank");
				return false;
			}
		}
		$("#loginform").submit();
	}
		
		$("#forgotPass").click(function(){
		var check=isEmailSend?$("#changesPasswordModal").modal():$("#sendemailModal").modal();
		});
		
		$("#sendPassword").click(function(){
			emailId = $("#email_forgot").val()
			if(emailId.trim()!=""){
					var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i 
					if(pattern.test(emailId))
					{
						$("#email_forgot_err").text("Please Wait it will take some time.....");
						$.ajax({
							url : "forgotpassword/"+emailId,
							type: "POST",
							success : function(result) {
								
								if(result.isEmailIdAvailable=="N")
								{
									$("#email_forgot_err").text(result.Message);
								}else if(result.isEmailIdAvailable=="Y")
								{
									if(result.isMailSend=="N")
									{
										$("#email_forgot_err").text("Not able to send email ");
									}else{
										$("#sendemailModal").modal('hide');
										isEmailSend=true;
										$("#changesPasswordModal").modal();
									}
								}
							},
							error: function (jqXHR, textStatus, errorThrown)
						    {
						 
						    }
						});
					}else
					{
						$("#email_forgot_err").text("Email format is wrong");
					}
			}
		});
		
		
		
		$("#changesPassword").click(function(){
			if(emailId.trim()!="" && $("#generated_password").val().trim()!="" && $("#new_password").val().trim()!=""){
				var userDetails={};
				userDetails['emailId']=emailId.trim();
				userDetails['generatedpassword']=$("#generated_password").val().trim();
				userDetails['newpassword']=$("#new_password").val().trim();
				
				if($("#generated_password").val().trim()==$("#new_password").val().trim()){
					 $("#password_change_msg").text("Generated Password and new password cannot be same");
					 return;
				}
				
				$.ajax({
					url : "userpasswordchange",
					type: "POST",
					contentType : 'application/json; charset=utf-8',
				    data : JSON.stringify(userDetails),
					success : function(result) {
                        $("#password_change_msg").text(result.Message);
                        setTimeout(() => { window.location.href="/"; }, 5000);
					},
					error: function (jqXHR, textStatus, errorThrown)
				    {
						alert(textStatus);
				    }
				});
			}
		});
		
});

