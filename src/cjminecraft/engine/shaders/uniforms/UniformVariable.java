package cjminecraft.engine.shaders.uniforms;

import static org.lwjgl.opengl.GL20.*;

/**
 * Represents a uniform variable in a shader file
 * 
 * @author CJMinecraft
 *
 */
public abstract class UniformVariable<T> {

	protected String name;
	protected int id;

	/**
	 * Initialise a new uniform variable with the given name. Be sure to link
	 * the program using {@link #linkToProgram(int)}
	 * 
	 * @param name
	 *            The name of the uniform variable
	 */
	public UniformVariable(String name) {
		this.name = name;
	}

	/**
	 * Link the uniform variable to the correct shader program
	 * 
	 * @param programId
	 *            The id of the shader program
	 * @return The updated uniform variable
	 */
	public UniformVariable<T> linkToProgram(int programId) {
		this.id = glGetUniformLocation(programId, this.name);
		return this;
	}
	
	public abstract UniformVariable<T> loadValue(T value);

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
