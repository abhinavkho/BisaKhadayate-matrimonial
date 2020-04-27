
document.addEventListener("DOMContentLoaded", function(event) {


 $("#submit-photo-home-slider-advertise").click(function(){
		  
		  if($("#filehomeslideradvertise").val().trim()!="")
		  {
			  $("#filehomeslideradvertise_err").text("");
			  if($("#startDateslider").val().trim()=="")
			  {
				  $("#startDateslider_err").text("Start date cannot be set blank");
				  return;
			  }else
			  {
				  $("#startDateslider_err").text("");
			  }	  
			  if($("#endDateslider").val().trim()=="")
			  {
				  $("#endDateslider_err").text("End date cannot be set blank");
				  return;
			  }else
			  {
				  $("#endDateslider_err").text("");
			  }	 
			var extensionArray=["JPG","JPEG","PNG"];
			var filePath = $("#filehomeslideradvertise").val();
			var splitFilePath=filePath.split(".");
			var extention=splitFilePath[splitFilePath.length-1];
			if(extensionArray.includes(extention.toUpperCase()))
			{
					$("#pageloader").css("display", "block");
					$("#bodydiv").css("opacity", ".5");
					$("#homeslideradvertise").submit();
			}else
			{
				$("#filehomeslideradvertise_err").text("file type should be one of them  JPG,JPEG,PNG.");
			}
			
		  }else{
			  $("#filehomeslideradvertise_err").text("Please choose the file before submit");
		  }
		  
	  });
 
 
 $("#submit-photo-home-marquee-advertise").click(function(){
	  
	  if($("#filehomemarqueeadvertise").val().trim()!="")
	  {
		  
		  $("#filehomemarqueeadvertise_err").text("");
		  if($("#startDatemarquee").val().trim()=="")
		  {
			  $("#startDatemarquee_err").text("Start date cannot be set blank");
			  return;
		  }else
		  {
			  $("#startDatemarquee_err").text("");
		  }	  
		  if($("#endDatemarquee").val().trim()=="")
		  {
			  $("#endDatemarquee_err").text("End date cannot be set blank");
			  return;
		  }else
		  {
			  $("#endDatemarquee_err").text("");
		  }	
		  
		var extensionArray=["JPG","JPEG","PNG"];
		var filePath = $("#filehomemarqueeadvertise").val();
		var splitFilePath=filePath.split(".");
		var extention=splitFilePath[splitFilePath.length-1];
		if(extensionArray.includes(extention.toUpperCase()))
		{
				$("#pageloader").css("display", "block");
				$("#bodydiv").css("opacity", ".5");
				$("#homemarqueeadvertise").submit();
		}else
		{
			$("#filehomemarqueeadvertise_err").text("file type should be one of them  JPG,JPEG,PNG.");
		}
		
	  }else{
		  $("#filehomemarqueeadvertise_err").text("Please choose the file before submit");
	  }
	  
 });
 

 
 $("#submit-photo-view-profile-advertise").click(function(){
	  
	  if($("#fileviewprofileadvertise").val().trim()!="")
	  {
		  
		  $("#fileviewprofileadvertise_err").text("");
		  if($("#startdateviewpro").val().trim()=="")
		  {
			  $("#startdateviewpro_err").text("Start date cannot be set blank");
			  return;
		  }else
		  {
			  $("#startdateviewpro_err").text("");
		  }	  
		  if($("#endDateviewpro").val().trim()=="")
		  {
			  $("#endDateviewpro_err").text("End date cannot be set blank");
			  return;
		  }else
		  {
			  $("#endDateviewpro_err").text("");
		  }	
		  
		  
		var extensionArray=["JPG","JPEG","PNG"];
		var filePath = $("#fileviewprofileadvertise").val();
		var splitFilePath=filePath.split(".");
		var extention=splitFilePath[splitFilePath.length-1];
		if(extensionArray.includes(extention.toUpperCase()))
		{
				$("#pageloader").css("display", "block");
				$("#bodydiv").css("opacity", ".5");
				$("#viewprofileadvertise").submit();
		}else
		{
			$("#fileviewprofileadvertise_err").text("file type should be one of them  JPG,JPEG,PNG.");
		}
		
	  }else{
		  $("#fileviewprofileadvertise_err").text("Please choose the file before submit");
	  }
	  
});
 
 
 $("#submit-photo-search-page-advertise").click(function(){
	  
	  if($("#filesearchpageadvertise").val().trim()!="")
	  {
		  
		  $("#filesearchpageadvertise_err").text("");
		  if($("#startdatesearch").val().trim()=="")
		  {
			  $("#startdatesearch_err").text("Start date cannot be set blank");
			  return;
		  }else
		  {
			  $("#startdatesearch_err").text("");
		  }	  
		  if($("#endDatesearch").val().trim()=="")
		  {
			  $("#endDatesearch_err").text("End date cannot be set blank");
			  return;
		  }else
		  {
			  $("#endDatesearch_err").text("");
		  }	
		  
		  
		var extensionArray=["JPG","JPEG","PNG"];
		var filePath = $("#filesearchpageadvertise").val();
		var splitFilePath=filePath.split(".");
		var extention=splitFilePath[splitFilePath.length-1];
		if(extensionArray.includes(extention.toUpperCase()))
		{
				$("#pageloader").css("display", "block");
				$("#bodydiv").css("opacity", ".5");
				$("#searchpageadvertise").submit();
		}else
		{
			$("#filesearchpageadvertise_err").text("file type should be one of them  JPG,JPEG,PNG.");
		}
		
	  }else{
		  $("#filesearchpageadvertise_err").text("Please choose the file before submit");
	  }
	  
});

 
 $(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip();   
	});
 
 
 
});