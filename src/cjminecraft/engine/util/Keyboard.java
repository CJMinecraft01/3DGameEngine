package cjminecraft.engine.util;

import static org.lwjgl.glfw.GLFW.*;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.HashMap;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import cjminecraft.engine.managers.WindowManager;

public class Keyboard extends GLFWKeyCallback {
	
	private static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, Boolean> keyFlags = new HashMap<Integer, Boolean>();
	
	private Keyboard() {
	}
	
	public static void initialiseKeyboard() {
		glfwSetKeyCallback(WindowManager.getInstance().getWindowId(), new Keyboard());
		try {
			Class<?> clazz = GLFW.class;
			for(Field field : clazz.getDeclaredFields()) {
				if (field.getName().startsWith("GLFW_KEY_")) {
					keys.put(field.getInt(clazz), false);
					keyFlags.put(field.getInt(clazz), false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isKeyDown(int key) {
		return keys.get(key);
	}
	
	public static boolean isKeyDown(int key, boolean togglable) {
		if(!togglable)
			return isKeyDown(key);
		if(!keyFlags.get(key) && isKeyDown(key)) {
			keyFlags.put(key, true);
			return true;
		}
		return false;
	}

	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		keys.keySet().forEach(k -> {
			if(k == key) {
				if(action == GLFW_PRESS) {
					keys.put(k, true);
				} else if (action == GLFW_RELEASE) {
					keys.put(k, false);
					if(keyFlags.get(k))
						keyFlags.put(k, false);
				}
			}
		});
	}

}
