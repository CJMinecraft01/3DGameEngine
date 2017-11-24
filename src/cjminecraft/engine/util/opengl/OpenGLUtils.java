package cjminecraft.engine.util.opengl;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLUtils {

	private static boolean wireframe = false;

	public static void enableCulling() {
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
	}

	public static void disableCulling() {
		glDisable(GL_CULL_FACE);
	}
	
	public static void toggleWireframeMode() {
		if(wireframe) {
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			wireframe = false;
		} else {
			glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
			wireframe = true;
		}
	}

}
