function changeImplementation() {
	pathArray = location.href.split( '/' );
	protocol = pathArray[0];
	host = pathArray[2];
	url = protocol + '//' + host;
	impl = document.getElementById("impl");
	window.location = url + "/" + impl.value;
}
