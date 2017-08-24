//进入输入框时校验数据
function checkElement(id) {
	var node = document.getElementById(id);
	var value = node.value;
	if (id == "url") {
		if (value == "请输入URL") {
			node.value = "";
		}
	} else if (id == "rulesName") {
		if (value == "请输入规则名称") {
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
	} else if (id == "rulesName") {
		if (value == "") {
			node.value = "请输入规则名称";
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
	} else if (id == "rulesName") {
		if (value == "请输入规则名称") {
			alert("请输入规则名称");
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
	var rulesName = checkData("rulesName");
	if (rulesName == undefined) {
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

/*
 * function addRules() { var url = checkData("url"); if (url == undefined) {
 * return; } var startIndex = checkData("startIndex"); if (startIndex ==
 * undefined) { return; } var endIndex = checkData("endIndex"); if (endIndex ==
 * undefined) { return; } var startStr = checkData("startStr"); if (startStr ==
 * undefined) { return; } var endStr = checkData("endStr"); if (endStr ==
 * undefined) { return; } var regex = checkData("regex"); if (regex ==
 * undefined) { return; }
 * 
 * var fm = document.getElementById("rules-form");
 * 
 * var fd = new FormData(fm)
 * 
 * var xhr = new XMLHttpRequest();
 * 
 * xhr.onreadystatechange=function(){ if(xhr.readyState==4){ eval("var
 * json="+xhr.responseText) alert(json.errmsg) } }
 * 
 * xhr.open("post","/BookReaderServer/addRules");
 * 
 * xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
 * 
 * xhr.send(fd); }
 */

// 编辑规则内容
function editRules(node) {
	var value = node.value;
	var trNode = node.parentElement.parentElement;
	var tdArr = trNode.children;
	if (value == "编辑") {
		node.value = "完成";
		var id = tdArr[0].innerText;
		var rulesName = tdArr[1].innerText;
		var url = tdArr[2].innerText;
		var startIndex = tdArr[3].innerText;
		var endIndex = tdArr[4].innerText;
		var startStr = tdArr[5].innerText;
		var endStr = tdArr[6].innerText;
		var regex = tdArr[7].innerText;
		var repeat = tdArr[8].innerText;
		var current = tdArr[9].innerText;
		
		tdArr[0].innerHTML = id;
		tdArr[1].innerHTML = "<input type='text' id='mRulesName' value='"
				+ rulesName + "'>";
		tdArr[2].innerHTML = "<input type='text' id='mUrl' value='" + url
				+ "'>";
		tdArr[3].innerHTML = "<input type='text' id='mStartIndex' value='"
				+ startIndex + "'>";
		tdArr[4].innerHTML = "<input type='text' id='mEndIndex' value='"
				+ endIndex + "'>";
		tdArr[5].innerHTML = "<input type='text' id='mStartStr' value='"
				+ startStr + "'>";
		tdArr[6].innerHTML = "<input type='text' id='mEndStr' value='" + endStr
				+ "'>";
		tdArr[7].innerHTML = "<input type='text' id='mRegex' value='" + regex
				+ "'>";
		if (repeat == '是') {
			tdArr[8].innerHTML = "<select name='repeat' id='mRepeat'><option value='true' selected='selected'>是</option><option value='false'>否</option></select>";
		} else {
			tdArr[8].innerHTML = "<select name='repeat' id='mRepeat'><option value='false' selected='selected'>否</option><option value='true'>是</option></select>";
		}
		if (current == '是') {
			tdArr[9].innerHTML = "<select name='current' id='mCurrent'><option value='true' selected='selected'>是</option><option value='false'>否</option></select>";
		} else {
			tdArr[9].innerHTML = "<select name='current' id='mCurrent'><option value='false' selected='selected'>否</option><option value='true'>是</option></select>";
		}

	} else {
		var id = tdArr[0].innerText;
		var rulesName = $("#mRulesName").val();
		var url = $("#mUrl").val();
		var startIndex = $("#mStartIndex").val();
		var endIndex = $("#mEndIndex").val();
		var startStr = $("#mStartStr").val();
		var endStr = $("#mEndStr").val();
		var regex = $("#mRegex").val();
		var repeat = $("#mRepeat").val();
		var current = $("#mCurrent").val();
		if (rulesName == "" || url == "" || startIndex == "" || endIndex == ""
				|| startStr == "" || endStr == "" || regex == "") {
			alert("数据不能为空！");
			return;
		}
		
		node.value = "编辑";
		if (confirm("是否修改该规则？")) {
			var value = "&rulesName=" + encodeURIComponent(rulesName) + "&url=" + encodeURIComponent(url) + "&startIndex="
			+ startIndex + "&endIndex=" + endIndex + "&startStr="
			+ encodeURIComponent(startStr) + "&endStr="
			+ encodeURIComponent(endStr) + "&regex="
			+ encodeURIComponent(regex) + "&repeat=" + repeat + "&current="
			+ current;
			location.href = "/BookReaderServer/operateRules?operate=update&id="
					+ id + value;
		} else {
			location.href = "/BookReaderServer/rulesManagement";
		}
	}
}

function deleteRules(node) {
	var trNode = node.parentElement.parentElement;
	var tdArr = trNode.children;
	var id = tdArr[0].innerText;
	var rulesName = tdArr[1].innerText;
	var url = tdArr[2].innerText;
	var startIndex = tdArr[3].innerText;
	var endIndex = tdArr[4].innerText;
	var startStr = tdArr[5].innerText;
	var endStr = tdArr[6].innerText;
	var regex = tdArr[7].innerText;
	var repeatText = tdArr[8].innerText;
	var repeat = false;
	if (repeatText == '是') {
		repeat = true;
	}
	var currentText = tdArr[9].innerText;
	var current = false;
	if (currentText == '是') {
		current = true;
	}
	if (confirm("是否删除该规则？")) {
		var value = "&rulesName=" + encodeURIComponent(rulesName) +"&url=" + encodeURIComponent(url) + "&startIndex="
				+ startIndex + "&endIndex=" + endIndex + "&startStr="
				+ encodeURIComponent(startStr) + "&endStr="
				+ encodeURIComponent(endStr) + "&regex="
				+ encodeURIComponent(regex) + "&repeat=" + repeat + "&current="
				+ current;
		location.href = "/BookReaderServer/operateRules?operate=delete&id="
				+ id + value;
	}
}