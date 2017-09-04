function collect(thisObj) {
	var $td = $(thisObj).parents('tr').children('td');
	var id = $td.eq(0).text();
	$(thisObj).attr('disabled', "true");// 添加disabled属性

	$.ajax({
		url : "startCollect",
		type : "POST",
		data : "{id:" + id + "}",
		contentType : 'application/json',// 请求的内容类型
		success : function(data, textStatus) {
			if ("success" == textStatus) {
				if (!data.errcode) {
					alert("请求出错!")
				} else if (data.errcode == 1) {
					alert("已经开始采集了!")
				}else{
					alert(data.errmsg)
				}
			}
		}
	});

}