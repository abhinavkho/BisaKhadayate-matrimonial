document.addEventListener("DOMContentLoaded", function(event) {

	$("#submitContactUs").click(function() {

		for (var i = 0; $("#contactForm")[0].length > i; i++) {
			var id = $("#contactForm")[0][i].id;
			if ($("#" + id).val().trim() == "") {

				$("#" + id + "_err").text("cannot be set blank")
				return false;
			} else {
				$("#" + id + "_err").text("")
			}
		}
		$("#pageloader").css("display", "block");
		$("#bodydiv").css("opacity", ".5");
		$("#contactForm").submit();

	});

});
