package cjminecraft.engine.shaders;

import static org.lwjgl.opengl.GL20.*;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class UniformVariable {

	private String name;
	private int id;

	protected UniformVariable(String name) {
		this.name = name;
	}
	
	protected UniformVariable(String name, int programId) {
		this.name = name;
		linkVariableToProgram(programId);
	}

	public UniformVariable linkVariableToProgram(int programId) {
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

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
