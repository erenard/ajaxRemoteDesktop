<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page language="java"
		contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="ISO-8859-1" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml"
		xmlns:jsp="http://java.sun.com/JSP/Page"
		xmlns:c="http://java.sun.com/jsp/jstl/core">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<link href="style.css" rel="stylesheet" type="text/css" />
		<title>httpRemoteAccess</title>
		<script type='text/javascript' src='/js/prototype.js'></script>
		<script type='text/javascript' src='/dwr/engine.js'></script>
		<script type='text/javascript' src='/dwr/interface/DesktopController.js'></script>
		<script type="text/javascript">
		function resize() {
			var width = $('imageContainer').getWidth();
			var height = $('imageContainer').getHeight();
			DesktopController.setResolution(width, height);
		}
		function mouseDown(event) {
			event.stop();
			DesktopController.mouseDown(event.pointerX(), event.pointerY(), event.isLeftClick());
		}
		function mouseUp(event) {
			event.stop();
			DesktopController.mouseUp(event.pointerX(), event.pointerY(), event.isLeftClick());
		}
		function keyDown(event) {
			event.stop();
			DesktopController.keyDown(event.keyCode);
		}
		function keyUp(event) {
			event.stop();
			DesktopController.keyUp(event.keyCode);
		}
		var isScreenReady = true;
		function refresh() {
			if(!isScreenReady) return;
			isScreenReady = false;
			$('screenshot').src = "screenshot.png?frame=" + new Date();
		}
		function readyScreen(event) {
			isScreenReady = true;
		}
		window.onload = function() {
			Event.observe('screenshot', 'load', readyScreen);
			Event.observe('screenshot', 'mousedown', mouseDown);
			Event.observe('screenshot', 'mouseup', mouseUp);
			Event.observe(window, 'resize', resize);
			Event.observe(window, 'keydown', keyDown);
			Event.observe(window, 'keyup', keyUp);
			
			resize();
		}
		</script>
	</head>
	<body oncontextmenu="return false;">
		<div id="imageContainer"><img id="screenshot"/></div>
	</body>
	</html>
</jsp:root>