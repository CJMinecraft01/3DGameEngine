package cjminecraft.engine.shaders.uniforms;

import static cjminecraft.engine.util.opengl.GLError.glCall;
import static org.lwjgl.opengl.GL20.glUniformMatrix3fv;

import org.joml.Matrix3f;

/**
 * Represents a uniform variable in a shader file which has a {@link Matrix3f}
 * value
 * 
 * @author CJMinecraft
 *
 */
public class UniformMat3 extends UniformVariable<Matrix3f> {

	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformMat3(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Matrix3f> loadValue(Matrix3f value) {
		glCall(() -> glUniformMatrix3fv(this.id, false, new float[] { value.m00, value.m01, value.m02, value.m10, value.m11,
				value.m12, value.m20, value.m21, value.m22 }));
		return this;
	}

}
