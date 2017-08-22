//进入输入框时校验数据
function checkElement(id) {
	var node = document.getElementById(id);
	var value = node.value;
	if (id == "url") {
		if (value == "请输入URL") {
			node.value = "";
		}
	} else if (id == "startIndex") {
		if (value == "请输入startIndex") {
			node.value = "";
		}
	} else if (id == "endIndex") {
		if (value == "请输入endIndex") {
			node.value = "";
		}
	} else if (id == "startStr") {
		if (value == "请输入startStr") {
			node.value = "";
		}
	} else if (id == "endStr") {
		if (value == "请输入endStr") {
			node.value = "";
		}
	} else if (id == "regex") {
		if (value == "请输入regex") {
			node.value = "";
		}
	}
}

// 离开输入框时校验数据
function reCheckElement(id) {
	var node = document.getElementById(id);
	var value = node.value;
	if (id == "url") {
		if (value == "") {
			node.value = "请输入URL";
		}
	} else if (id == "startIndex") {
		if (value == "") {
			node.value = "请输入startIndex";
		}
	} else if (id == "endIndex") {
		if (value == "") {
			node.value = "请输入endIndex";
		}
	} else if (id == "startStr") {
		if (value == "") {
			node.value = "请输入startStr";
		}
	} else if (id == "endStr") {
		if (value == "") {
			node.value = "请输入endStr";
		}
	} else if (id == "regex") {
		if (value == "") {
			node.value = "请输入regex";
		}
	}
}

// 校验数据
function checkData(id) {
	var node = document.getElementById(id);
	var value = node.value;
	if (id == "url") {
		if (value == "请输入URL") {
			alert("请输入采集URL!");
		} else {
			return value;
		}
	} else if (id == "startIndex") {
		if (value == "请输入startIndex") {
			alert("请输入开始索引!");
			return;
		}
		var reg = /^\d+$/;
		if (!reg.test(value)) {
			alert("请输入正确的开始索引,必须是整数!");
		} else {
			return value;
		}
	} else if (id == "endIndex") {
		if (value == "请输入endIndex") {
			alert("请输入结束索引!");
			return;
		}
		var reg = /^\d+$/;
		if (!reg.test(value)) {
			alert("请输入正确的结束索引,必须是整数!");
		} else {
			return value;
		}
	} else if (id == "startStr") {
		if (value == "请输入startStr") {
			alert("请输入开始区域!");
		} else {
			return value;
		}
	} else if (id == "endStr") {
		if (value == "请输入endStr") {
			alert("请输入结束区域!");
		} else {
			return value;
		}
	} else if (id == "regex") {
		if (value == "请输入regex") {
			alert("请输入正则表达式!");
		} else {
			return value;
		}
	}
}

// 添加规则
function addRules() {
	var url = checkData("url");
	if (url == undefined) {
		return false;
	}
	var startIndex = checkData("startIndex");
	if (startIndex == undefined) {
		return false;
	}
	var endIndex = checkData("endIndex");
	if (endIndex == undefined) {
		return false;
	}
	var startStr = checkData("startStr");
	if (startStr == undefined) {
		return false;
	}
	var endStr = checkData("endStr");
	if (endStr == undefined) {
		return false;
	}
	var regex = checkData("regex");
	if (regex == undefined) {
		return false;
	}

	return true;
}

/*function addRules() {
	var url = checkData("url");
	if (url == undefined) {
		return;
	}
	var startIndex = checkData("startIndex");
	if (startIndex == undefined) {
		return;
	}
	var endIndex = checkData("endIndex");
	if (endIndex == undefined) {
		return;
	}
	var startStr = checkData("startStr");
	if (startStr == undefined) {
		return;
	}
	var endStr = checkData("endStr");
	if (endStr == undefined) {
		return;
	}
	var regex = checkData("regex");
	if (regex == undefined) {
		return;
	}
	
	var fm = document.getElementById("rules-form");
	
	var fd = new FormData(fm)

	var xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			eval("var json="+xhr.responseText)
			alert(json.errmsg)
			
		}
	}
	
	xhr.open("post","/BookReaderServer/addRules");
	
	xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	
	xhr.send(fd);
}*/
