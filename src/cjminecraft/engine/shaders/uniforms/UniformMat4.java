package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

import org.joml.Matrix4f;

public class UniformMat4 extends UniformVariable<Matrix4f> {
	
	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformMat4(String name) {
		super(name);
	}

	@Override
	public UniformVariable<Matrix4f> loadValue(Matrix4f value) {
		glUniformMatrix4fv(this.id, false,
				new float[] { value.m00(), value.m01(), value.m02(), value.m03(), value.m10(), value.m11(),
						value.m12(), value.m13(), value.m20(), value.m21(), value.m22(), value.m23(),
						value.m30(), value.m31(), value.m32(), value.m33() });
		return this;
	}

}
