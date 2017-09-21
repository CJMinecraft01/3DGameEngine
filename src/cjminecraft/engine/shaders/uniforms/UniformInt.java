package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform1i;

public class UniformInt extends UniformVariable<Integer> {
	
	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformInt(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Integer> loadValue(Integer value) {
		glUniform1i(this.id, value);
		return this;
	}

}
