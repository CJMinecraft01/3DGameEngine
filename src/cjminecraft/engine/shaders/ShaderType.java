package cjminecraft.engine.shaders;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL40.*;

public enum ShaderType {
	
	VERTEX(GL_VERTEX_SHADER, "vertex"),
	TESSELATION_CONTROL(GL_TESS_CONTROL_SHADER, "tesselation_control"),
	TESSELATION_EVALUATION(GL_TESS_EVALUATION_SHADER, "tesselation_evaluation"),
	GEOMETRY(GL_GEOMETRY_SHADER, "geometry"),
	FRAGMENT(GL_FRAGMENT_SHADER, "fragment");
	
	private int type;
	private String name;
	
	private ShaderType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

}
