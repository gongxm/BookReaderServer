// 定义居中显示方法
$(document).ready(
		function() {
			jQuery.fn.extend({
				center : function(width, height) {
					return $(this).css(
							"left",
							($(window).width() - width) / 2
									+ $(window).scrollLeft()).css(
							"top",
							($(window).height() - height) / 2
									+ $(window).scrollTop())
							.css("width", width).css("height", height);
				}
			});
		});

// 表单提交
$(document).ready(function() {

	$("#close").click(function() {
		$("#floatdiv").hide();
		$("#over").hide();
	});

	$("#edit").click(function() {
		
		var id = $("#id").val();
		
		// 根据ID获取到当前要编辑的规则内容
		$.ajax({
			url : "showContentRules",
			type : "POST",
			data : "{id:" + id + "}",
			contentType : 'application/json',// 请求的内容类型
			success : function(data, textStatus) {
				if ("success" == textStatus) {
					if (data.errcode == 1) {
						$("#over").show();// 展现遮罩层屏幕
						$("#floatdiv").show().center(800, 600);// 展现悬浮框
						
						var rules = data.result;
						
						$("#c_id").val(rules.id);
						$("#c_start").val(rules.startStr);
						$("#c_end").val(rules.endStr);
					}
				}
			}
		});
	});

	$("#cancel").click(function() {
		window.location.href = 'showAllRules';
	});

	$("#bookRulesForm").submit(function() { // 

		var FormData = $("form[name=bookRulesForm]").serializeArray(); // 转换为Json格式数据

		$.ajax({
			url : "updateBookRules",
			type : "POST",
			data : FormData,
			success : function(data, textStatus) {
				if ("success" == textStatus) {
					if (data.errcode == 1) {
						alert("操作成功!")
						window.location.href = 'showAllRules';
					} else {
						alert("操作失败!")
					}
				} else {
					alert("请求失败,请重试!")
				}
			}
		});
		return false; // 设置为 false 这样表单提交就不会页面跳转
	});
});