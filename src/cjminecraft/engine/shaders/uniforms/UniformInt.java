package cjminecraft.engine.shaders.uniforms;

import static cjminecraft.engine.util.opengl.GLError.glCall;
import static org.lwjgl.opengl.GL20.glUniform1i;

/**
 * Represents a uniform variable in a shader file which has an integer value
 * 
 * @author CJMinecraft
 *
 */
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
		glCall(() -> glUniform1i(this.id, value));
		return this;
	}

}
