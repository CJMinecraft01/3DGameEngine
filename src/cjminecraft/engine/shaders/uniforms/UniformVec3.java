package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform3f;

import org.joml.Vector3f;

public class UniformVec3 extends UniformVariable<Vector3f> {
	
	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformVec3(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Vector3f> loadValue(Vector3f value) {
		glUniform3f(this.id, value.x, value.y, value.z);
		return this;
	}

}
