package cjminecraft.engine.shaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public abstract class Shader {

	protected int programId;
	protected HashMap<ShaderType, Integer> shaders = new HashMap<ShaderType, Integer>();

	public Shader() {
		this("");
	}
	
	public Shader(String shaderPrefix) {
		for (ShaderType type : ShaderType.values()) {
			try {
				String filePath = "/" + Class.forName(new Exception().getStackTrace()[1].getClassName()).getPackage()
						.getName().replace(".", "/") + "/" + (shaderPrefix.isEmpty() ? type.getName() : shaderPrefix + type.getName().substring(0, 1).toUpperCase() + type.getName().substring(1)) + ".glsl";
				if (Class.class.getResourceAsStream(filePath) != null) {
					this.shaders.put(type, loadShader(filePath, type.getType()));
					System.out.println(new Exception().getStackTrace()[1].getClassName().substring(37)
							+ ": Found Shader: " + type.getName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.programId = glCreateProgram();
		Iterator<Entry<ShaderType, Integer>> iterator = this.shaders.entrySet().iterator();
		while (iterator.hasNext())
			glAttachShader(this.programId, iterator.next().getValue());
		bindAttributes();
		glLinkProgram(this.programId);
		glValidateProgram(this.programId);
		getAllUniformLocations();
	}

	protected abstract void bindAttributes();

	protected abstract void getAllUniformLocations();

	protected UniformVariable getUniformVariable(String name) {
		return new UniformVariable(name).linkVariableToProgram(this.programId);
	}

	public void start() {
		glUseProgram(this.programId);
	}

	public void stop() {
		glUseProgram(0);
	}

	public void cleanUp() {
		stop();
		Iterator<Entry<ShaderType, Integer>> iterator = this.shaders.entrySet().iterator();
		while (iterator.hasNext()) {
			int id = iterator.next().getValue();
			glDetachShader(this.programId, id);
			glDeleteShader(id);
		}
		glDeleteProgram(this.programId);
	}

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

	protected void bindAttribute(int attribute, String variableName) {
		glBindAttribLocation(this.programId, attribute, variableName);
	}

}
