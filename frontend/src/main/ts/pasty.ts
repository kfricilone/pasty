$(function () {
	$("#title").on("click", function () {
		window.location.href = "/";
	});
	$("#newBtn").on("click", function () {
		window.location.href = "/";
	});
	$("#editBtn").on("click", function () {
		window.location.href = "/doc/" + $("#key").val() + "/edit";
	});
	$("#shareBtn").on("click", function () {
		navigator.clipboard.writeText(window.location.href);
	});
	$("#saveBtn").on("click", function () {
		$.ajax({
			type: "POST",
			url: "/doc",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				lang: $("#lang option:selected").val(),
				text: $("#text").val()
			}),
			dataType: "json",
			success: function (data) {
				window.location.href = data.redirect
			}
		});
	});
});
