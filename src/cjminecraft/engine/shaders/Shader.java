package cjminecraft.engine.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import cjminecraft.engine.shaders.uniforms.UniformVariable;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * The basic shader class. To add a shader, simply create a new text file saving
 * it the name of the shader or using the prefix in the constructor, with the
 * prefix on with the name of the shader with the first letter capitalised with
 * the file extension of .glsl
 * 
 * @author CJMinecraft
 *
 */
public abstract class Shader {

	protected int programId;
	protected HashMap<ShaderType, Integer> shaders = new HashMap<ShaderType, Integer>();

	/**
	 * A simple shader. To add a shader, simply create a new text file saving it
	 * the name of the shader with the prefix on the start, ensuring the name of
	 * the shader has the first letter capitalised and with the file extension
	 * of .glsl <br>
	 * Example: <code>prefixFragment.glsl</code>
	 * 
	 * @param shaderPrefix
	 *            The prefix before the name of the shader
	 * @param attributes
	 *            The attributes to bind
	 */
	public Shader(String shaderPrefix, String... attributes) {
		this.programId = glCreateProgram();
		for (ShaderType type : ShaderType.values()) {
			try {
				String filePath = "/"
						+ Class.forName(new Exception().getStackTrace()[1].getClassName()).getPackage().getName()
								.replace(".", "/")
						+ "/"
						+ (shaderPrefix.isEmpty() ? type.getName()
								: shaderPrefix + type.getName().substring(0, 1).toUpperCase()
										+ type.getName().substring(1))
						+ ".glsl";
				if (Class.class.getResourceAsStream(filePath) != null) {
					this.shaders.put(type, loadShader(filePath, type.getType()));
					System.out.println(Class.forName(new Exception().getStackTrace()[1].getClassName()).getSimpleName()
							+ ": Found Shader: " + type.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Iterator<Entry<ShaderType, Integer>> iterator = this.shaders.entrySet().iterator();
		while (iterator.hasNext())
			glAttachShader(this.programId, iterator.next().getValue());
		bindAttributes(attributes);
		glLinkProgram(this.programId);
		iterator = this.shaders.entrySet().iterator();
		while (iterator.hasNext()) {
			int id = iterator.next().getValue();
			glDetachShader(this.programId, id);
			glDeleteShader(id);
		}
	}

	/**
	 * Bind all of the attrubtes to the shader program
	 * 
	 * @param attributes
	 *            The name of all the attributes
	 */
	protected void bindAttributes(String... attributes) {
		for (int i = 0; i < attributes.length; i++)
			glBindAttribLocation(this.programId, i, attributes[i]);
	}

	/**
	 * Store all of the uniform variables, ensuring to then validate the program
	 * afterwards
	 * 
	 * @param variables
	 *            The uniform variables
	 */
	protected void storeAllUniformLocations(UniformVariable<?>... variables) {
		storeSomeUniformLocations(variables);
		validateProgram();
	}

	/**
	 * Store all of the uniform variables. Make sure to validate the program
	 * once all uniform locations are stored
	 * 
	 * @param variables
	 *            The uniform variables
	 */
	protected void storeSomeUniformLocations(UniformVariable<?>... variables) {
		for (UniformVariable<?> variable : variables)
			variable.linkToProgram(this.programId);
	}

	/**
	 * Validate the program, saying it is ready to be used
	 */
	protected void validateProgram() {
		glValidateProgram(this.programId);
	}

	/**
	 * Start the shader
	 */
	public void start() {
		glUseProgram(this.programId);
	}

	/**
	 * Stop the shader
	 */
	public void stop() {
		glUseProgram(0);
	}

	/**
	 * Clean up all the mess you made
	 */
	public void cleanUp() {
		stop();
		glDeleteProgram(this.programId);
	}

	/**
	 * Load an actual shader file with the correct type. The type is found when
	 * using a {@link ShaderType#getType()}
	 * 
	 * @param file
	 *            The path to the shader file
	 * @param type
	 *            The type of file - use {@link ShaderType#getType()}
	 * @return The program id of the shader
	 */
	private static int loadShader(String file, int type) {
		StringBuilder shaderSource = new StringBuilder();
		try {
			InputStream in = Class.class.getResourceAsStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null)
				shaderSource.append(line).append("//\n");
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = glCreateShader(type);
		glShaderSource(shaderID, shaderSource);
		glCompileShader(shaderID);
		if (glGetShaderi(shaderID, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println(
					"[" + new Exception().getStackTrace()[1].getClassName() + "]" + glGetShaderInfoLog(shaderID, 500));
			System.err.println(
					new Exception().getStackTrace()[1].getClassName().substring(37) + ": Could not compile shader!");
			System.exit(-1);
		}
		return shaderID;
	}

}
