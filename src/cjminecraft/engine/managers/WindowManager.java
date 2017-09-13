package cjminecraft.engine.managers;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import cjminecraft.engine.Engine;
import cjminecraft.engine.util.IManager;

public class WindowManager implements IManager {

	private static WindowManager instance = new WindowManager();
	
	private long windowId;
	private String title;
	private int width, height;

	@Override
	public void preInit() throws Exception {
		this.width = Integer.valueOf(Engine.getInstance().getOption("width"));
		this.height = Integer.valueOf(Engine.getInstance().getOption("height"));
		String title = Engine.getInstance().getOption("title");
		if(title.contains("#format")) {
			title = title.substring("#format".length() + 1); //Remove first bracket
			title = title.substring(0, title.length() - 1); //Remove last bracket
			String[] details = title.split(",");
			List<Object> args = new ArrayList<Object>();
			for(String obj : details)
				args.add(obj);
			args.remove(0);
			this.title = LanguageManager.getInstance().format(details[0], args.toArray());
		} else {
			this.title = title;
		}
		
		GLFWErrorCallback.createPrint(System.err).set();

		if (!glfwInit())
			throw new IllegalStateException("Unable to initailise GLFW");

		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE,
				Boolean.valueOf(Engine.getInstance().getOption("resizable")) ? GLFW_TRUE : GLFW_FALSE);
		
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
	}

	@Override
	public void init() throws Exception {}

	@Override
	public void postInit() throws Exception {}

	@Override
	public void loop() throws Exception {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void cleanUp() throws Exception {}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public long getWindowId() {
		return windowId;
	}
	
	public static WindowManager getInstance() {
		return instance;
	}

}
