package cjminecraft.engine.util;

import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;

public class GLError {
	
	public static final boolean GL_DEBUG = true;
	
	public static String glErrorToString(int error) {
		switch(error) {
			case 0x0500: return "GL_INVALID_ENUM";
			case 0x0501: return "GL_INVALID_VALUE";
			case 0x0502: return "GL_INVALID_OPERATION";
			case 0x0503: return "GL_STACK_OVERFLOW";
			case 0x0504: return "GL_STACK_UNDERFLOW";
			case 0x0505: return "GL_OUT_OF_MEMORY";
			case 0x0506: return "GL_INVALID_FRAMEBUFFER_OPERATION";
			case 0x0507: return "GL_CONTEXT_LOST";
			case 0x8031: return "GL_TABLE_TOO_LARGE";
		}
		return "Unknown error";
	}
	
	public static boolean glLogCall(StackTraceElement[] stack) {
		int error = glGetError();
		if(error != GL_NO_ERROR) {
			// TODO: error
			System.err.println("[OpenGL Error] (" + glErrorToString(error) + "): " + stack[2].getClassName() + "." + stack[2].getMethodName() + " (" + stack[2].getFileName() + ":" + stack[2].getLineNumber() + ")");
			for(int i = 3; i < stack.length; i++) {
				System.err.println("\t\t\t " + stack[i].getClassName() + "." + stack[i].getMethodName() + " (" + stack[i].getFileName() + ":" + stack[i].getLineNumber() + ")");
			}
			return false;
		}
		return true;
	}
	
	public static interface glCallFuncT<T> {
		T glCall();
	}
	
	public static interface glCallFunc {
		void glCall();
	}
	
	public static <T> T glCallT(glCallFuncT<T> func) {
		StackTraceElement[] ele = Thread.currentThread().getStackTrace();
		T t = func.glCall();
		if(!GL_DEBUG) return t;
		if(!glLogCall(ele)) {
			assert(false);
		}
		return t;
	}
	
	public static void glCall(glCallFunc func) {
		StackTraceElement[] ele = Thread.currentThread().getStackTrace();
		func.glCall();
		if(!GL_DEBUG) return;
		if(!glLogCall(ele)) {
			assert(false);
		}
	}
	
}