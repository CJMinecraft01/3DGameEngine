package cjminecraft.engine.shaders;

import static org.lwjgl.opengl.GL20.*;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * Represents a uniform variable in a shader file
 * 
 * @author CJMinecraft
 *
 */
public class UniformVariable {

	private String name;
	private int id;

	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	protected UniformVariable(String name) {
		this.name = name;
	}

	/**
	 * Initialise a new uniform variable with the given name. The uniform
	 * variable is automatically linked to the program id
	 * 
	 * @param name
	 *            The name of the uniform variable
	 * @param programId
	 *            The id of the shader program
	 */
	protected UniformVariable(String name, int programId) {
		this.name = name;
		linkToProgram(programId);
	}

	/**
	 * Link the uniform variable to the correct shader program
	 * 
	 * @param programId
	 *            The id of the shader program
	 * @return The updated uniform variable
	 */
	public UniformVariable linkToProgram(int programId) {
		this.id = glGetUniformLocation(programId, this.name);
		return this;
	}

	public UniformVariable loadFloat(float value) {
		glUniform1f(this.id, value);
		return this;
	}

	public UniformVariable loadInt(int value) {
		glUniform1i(this.id, value);
		return this;
	}

	public UniformVariable loadBoolean(boolean value) {
		glUniform1f(this.id, value ? 1 : 0);
		return this;
	}

	public UniformVariable loadVector(Vector2f vector) {
		glUniform2f(this.id, vector.x, vector.y);
		return this;
	}

	public UniformVariable loadVector(Vector3f vector) {
		glUniform3f(this.id, vector.x, vector.y, vector.z);
		return this;
	}

	public UniformVariable loadVector(Vector4f vector) {
		glUniform4f(this.id, vector.x, vector.y, vector.z, vector.w);
		return this;
	}

	public UniformVariable loadMatrix(Matrix3f matrix) {
		glUniformMatrix3fv(this.id, false, new float[] { matrix.m00, matrix.m01, matrix.m02, matrix.m10, matrix.m11,
				matrix.m12, matrix.m20, matrix.m21, matrix.m22 });
		return this;
	}

	public UniformVariable loadMatrix(Matrix4f matrix) {
		glUniformMatrix4fv(this.id, false,
				new float[] { matrix.m00(), matrix.m01(), matrix.m02(), matrix.m03(), matrix.m10(), matrix.m11(),
						matrix.m12(), matrix.m13(), matrix.m20(), matrix.m21(), matrix.m22(), matrix.m23(),
						matrix.m30(), matrix.m31(), matrix.m32(), matrix.m33() });
		return this;
	}

	/**
	 * @return the location of the uniform variable in the shader program
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name of the uniform variable
	 */
	public String getName() {
		return name;
	}

}
