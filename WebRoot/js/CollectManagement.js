function collect(node){
	var value = node.value;
	var trNode = node.parentElement.parentElement;
	var tdArr = trNode.children;
	var id = tdArr[0].innerText;
	var rulesName = tdArr[1].innerText;

}