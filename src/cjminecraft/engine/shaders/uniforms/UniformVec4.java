package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform4f;

import org.joml.Vector4f;

/**
 * Represents a uniform variable in a shader file which has a {@link Vector4f}
 * value
 * 
 * @author CJMinecraft
 *
 */
public class UniformVec4 extends UniformVariable<Vector4f> {

	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformVec4(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Vector4f> loadValue(Vector4f value) {
		glUniform4f(this.id, value.x, value.y, value.z, value.w);
		return this;
	}

}
