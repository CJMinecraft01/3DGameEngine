package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform2f;

import org.joml.Vector2f;

public class UniformVec2 extends UniformVariable<Vector2f> {
	
	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformVec2(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Vector2f> loadValue(Vector2f value) {
		glUniform2f(this.id, value.x, value.y);
		return this;
	}

}
