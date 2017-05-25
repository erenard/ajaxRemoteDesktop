package org.httpRemoteDesktop;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class RobotService {

	private Robot robot;
	private Rectangle fullResolution;
	private Rectangle resolution;

	public RobotService() {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice graphicsDevice = ge.getDefaultScreenDevice();
			this.robot = new Robot(graphicsDevice);
			int width = graphicsDevice.getDisplayMode().getWidth();
			int height = graphicsDevice.getDisplayMode().getHeight();
			this.fullResolution = new Rectangle(width, height);
			this.resolution = new Rectangle(width, height);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public boolean getScreenshot(OutputStream outputStream) throws IOException {
		BufferedImage bufferedImage = robot.createScreenCapture(fullResolution);
		if(!fullResolution.equals(resolution)) {
			AffineTransform affineTransform = AffineTransform.getScaleInstance(resolution.getWidth() / fullResolution.getWidth(), resolution.getHeight() / fullResolution.getHeight());
			AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			bufferedImage = affineTransformOp.filter(bufferedImage, null);
		}
		boolean result = ImageIO.write(bufferedImage, "png", outputStream);
		return result;
	}

	public void setResolution(Integer width, Integer height) {
		this.resolution = new Rectangle(width, height);
	}

	public void mouseDown(Integer width, Integer height, Boolean isLeftClick) {
		width = (int) ((width * fullResolution.getWidth()) / resolution.getWidth());
		height = (int) ((height * fullResolution.getHeight()) / resolution.getHeight());
		robot.mouseMove(width, height);
		if(isLeftClick) robot.mousePress(InputEvent.BUTTON1_MASK);
		else robot.mousePress(InputEvent.BUTTON3_MASK);
	}

	public void mouseUp(Integer width, Integer height, Boolean isLeftClick) {
		width = (int) ((width * fullResolution.getWidth()) / resolution.getWidth());
		height = (int) ((height * fullResolution.getHeight()) / resolution.getHeight());
		robot.mouseMove(width, height);
		if(isLeftClick) robot.mouseRelease(InputEvent.BUTTON1_MASK);
		else robot.mouseRelease(InputEvent.BUTTON3_MASK);
	}

	private Integer convertKeyCode(Integer keyCode) {
		System.out.println(keyCode);
		switch(keyCode) {
		case 8:
			keyCode = KeyEvent.VK_BACK_SPACE;
			break;
		case 9:
			keyCode = KeyEvent.VK_TAB;
			break;
		case 13:
			keyCode = KeyEvent.VK_ENTER;
			break;
		case 44:
			keyCode = KeyEvent.VK_PRINTSCREEN;
			break;
		case 45:
			keyCode = KeyEvent.VK_INSERT;
			break;
		case 46:
			keyCode = KeyEvent.VK_DELETE;
			break;
		}
		return keyCode;
	}

	public void keyDown(Integer keyCode) {
		keyCode = convertKeyCode(keyCode);
		robot.keyPress(keyCode);
	}

	public void keyUp(Integer keyCode) {
		keyCode = convertKeyCode(keyCode);
		robot.keyRelease(keyCode);
	}
}
