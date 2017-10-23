package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform1f;
import static cjminecraft.engine.util.GLError.glCall;

/**
 * Represents a uniform variable in a shader file which has a float value
 * 
 * @author CJMinecraft
 *
 */
public class UniformFloat extends UniformVariable<Float> {

	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformFloat(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Float> loadValue(Float value) {
		glCall(() -> glUniform1f(this.id, value));
		return this;
	}

}
