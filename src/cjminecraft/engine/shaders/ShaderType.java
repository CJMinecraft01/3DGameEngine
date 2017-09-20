package cjminecraft.engine.shaders;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL40.*;

/**
 * All of the different shader types you can implement
 * 
 * @author CJMinecraft
 *
 */
public enum ShaderType {

	VERTEX(GL_VERTEX_SHADER, "vertex"), TESSELATION_CONTROL(GL_TESS_CONTROL_SHADER,
			"tesselation_control"), TESSELATION_EVALUATION(GL_TESS_EVALUATION_SHADER,
					"tesselation_evaluation"), GEOMETRY(GL_GEOMETRY_SHADER,
							"geometry"), FRAGMENT(GL_FRAGMENT_SHADER, "fragment");

	private int type;
	private String name;

	/**
	 * Initialise a new shader type
	 * 
	 * @param type
	 *            The integer representation of the shader type
	 * @param name
	 *            The name of the shader type
	 */
	private ShaderType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	/**
	 * @return the integer representation of the shader type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the name of the shader type
	 */
	public String getName() {
		return name;
	}

}
