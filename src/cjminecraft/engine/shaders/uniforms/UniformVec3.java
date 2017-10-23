package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniform3f;
import static cjminecraft.engine.util.GLError.glCall;

import org.joml.Vector3f;

/**
 * Represents a uniform variable in a shader file which has a {@link Vector3f}
 * value
 * 
 * @author CJMinecraft
 *
 */
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
		glCall(() -> glUniform3f(this.id, value.x, value.y, value.z));
		return this;
	}

}
