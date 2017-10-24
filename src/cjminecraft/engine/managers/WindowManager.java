package cjminecraft.engine.managers;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import cjminecraft.engine.Engine;
import cjminecraft.engine.util.IManager;

/**
 * The window manager, handling everything to do with the window. The width,
 * height and title is loaded from {@link Engine#LAUNCH_PATH_FILE}
 * 
 * @author CJMinecraft
 *
 */
public class WindowManager implements IManager {

	/**
	 * The instance of the manager
	 */
	private static WindowManager instance = new WindowManager();

	private long windowId;
	private String title;
	private int width, height;

	@Override
	public void preInit() throws Exception {
		this.width = Integer.parseInt(Engine.getOption("width"));
		this.height = Integer.parseInt(Engine.getOption("height"));
		this.title = Engine.getOption("title");

		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("Unable to initailise GLFW");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, Boolean.valueOf(Engine.getOption("resizable")) ? GLFW_TRUE : GLFW_FALSE);
//		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
//		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
//		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		
		this.windowId = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
		if (this.windowId == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		try (MemoryStack stack = stackPush()) {
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(this.windowId, (vidmode.width() - this.width) / 2, (vidmode.height() - this.height) / 2);
		}

		glfwMakeContextCurrent(this.windowId);

		glfwSwapInterval(1);

		glfwShowWindow(this.windowId);
		
		GL.createCapabilities();

		glClearColor(0, 0, 0, 1);
	}

	@Override
	public void init() throws Exception {
	}

	@Override
	public void postInit() throws Exception {
	}

	@Override
	public void loop() throws Exception {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		/*
		glBegin(GL_QUADS);
		glVertex2d(-0.5F, 0.5F);
		glVertex2d(0.5F, 0.5F);
		glVertex2d(0.5F, -0.5F);
		glVertex2d(-0.5F, -0.5F);
		glEnd();
		*/
	}

	@Override
	public void cleanUp() throws Exception {
	}

	/**
	 * @return the initial width of the window. TODO make the width update when
	 *         requested
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the initial height of the window. TODO make the height update
	 *         when requested
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the id of the {@link GLFW} window
	 */
	public long getWindowId() {
		return windowId;
	}

	/**
	 * @return the instance of the window manager
	 */
	public static WindowManager getInstance() {
		return instance;
	}

}
