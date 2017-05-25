package org.httpRemoteDesktop;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.proxy.dwr.Util;
import org.directwebremoting.spring.SpringCreator;

@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "desktopController"))
public class DesktopController {
	private RobotService robotService;
	
	public void setRobotService(RobotService robotService) {
		this.robotService = robotService;
	}
	
	@RemoteMethod
	public void setResolution(Integer width, Integer height) {
		robotService.setResolution(width, height);
		refresh();
	}
	
	@RemoteMethod
	public void mouseDown(Integer width, Integer height, Boolean isLeftClick) {
		robotService.mouseDown(width, height, isLeftClick);
		refresh();
	}
	
	@RemoteMethod
	public void mouseUp(Integer width, Integer height, Boolean isLeftClick) {
		robotService.mouseUp(width, height, isLeftClick);
		refresh();
	}
	
	@RemoteMethod
	public void keyDown(Integer keyCode) {
		robotService.keyDown(keyCode);
		refresh();
	}
	
	@RemoteMethod
	public void keyUp(Integer keyCode) {
		robotService.keyUp(keyCode);
		refresh();
	}
	
	private void refresh() {
		WebContext webContext = WebContextFactory.get();
		Util util = new Util(webContext.getScriptSession());
		util.addFunctionCall("refresh");
	}
}