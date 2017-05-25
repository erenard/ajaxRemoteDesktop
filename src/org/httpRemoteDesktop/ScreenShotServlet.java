package org.httpRemoteDesktop;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ScreenShotServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static RobotService robotService = (RobotService) ApplicationContextHolder.getContext().getBean("robotService");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/png");
		OutputStream outputStream = response.getOutputStream();
		robotService.getScreenshot(outputStream);
		outputStream.flush();
		response.flushBuffer();
	}

}
