$(() => {
	$("#title").on("click", () => {
		window.location.href = "/";
	});
	$("#newBtn").on("click", () => {
		window.location.href = "/";
	});
	$("#editBtn").on("click", () => {
		window.location.href = "/doc/" + $("#key").val() + "/edit";
	});
	$("#shareBtn").on("click", () => {
		navigator.clipboard.writeText(window.location.href);
	});
	$("#saveBtn").on("click", () => {
		$.ajax({
			type: "POST",
			url: "/doc",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				lang: $("#lang option:selected").val(),
				text: $("#text").val()
			}),
			dataType: "json",
			success: data => {
				window.location.href = data.redirect
			}
		});
	});
});
