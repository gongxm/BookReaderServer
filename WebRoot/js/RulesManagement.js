function checkElement(id) {
	var node = document.getElementById(id);
	var value = node.value;
	if (id == "url") {
		if (value == "请输入URL") {
			node.value = "";
		}
	} else if (id == "startIndex") {
		if (value == "请输入startIndex")
			node.value = "";
	} else if (id == "endIndex") {
		if (value == "请输入endIndex")
			node.value = "";
	} else if (id == "startStr") {
		if (value == "请输入startStr")
			node.value = "";
	} else if (id == "endStr") {
		if (value == "请输入endStr")
			node.value = "";
	}else if (id == "regex") {
		if (value == "请输入regex")
			node.value = "";
	}else if (id == "repeat") {
		if (value == "请输入repeat [0 | 1]")
			node.value = "";
	}else if (id == "current") {
		if (value == "请输入current [0 | 1]")
			node.value = "";
	}
}


function reCheckElement(id) {
	var node = document.getElementById(id);
	var value = node.value;
	if (id == "url") {
		if (value == "") {
			node.value = "请输入URL";
		}
	} else if (id == "startIndex") {
		if (value == "")
			node.value = "请输入startIndex";
	} else if (id == "endIndex") {
		if (value == "")
			node.value = "请输入endIndex";
	} else if (id == "startStr") {
		if (value == "")
			node.value = "请输入startStr";
	} else if (id == "endStr") {
		if (value == "")
			node.value = "请输入endStr";
	}else if (id == "regex") {
		if (value == "")
			node.value = "请输入regex";
	}else if (id == "repeat") {
		if (value == "")
			node.value = "请输入repeat [0 | 1]";
	}else if (id == "current") {
		if (value == "")
			node.value = "请输入current [0 | 1]";
	}
}