package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform1f;

public class UniformBoolean extends UniformVariable<Boolean> {
	
	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformBoolean(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Boolean> loadValue(Boolean value) {
		glUniform1f(this.id, value ? 1 : 0);
		return this;
	}

}
