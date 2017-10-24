package cjminecraft.engine.test;

import cjminecraft.engine.shaders.Shader;
import cjminecraft.engine.shaders.uniforms.UniformMat4;
import cjminecraft.engine.shaders.uniforms.UniformVec3;

public class TestShader extends Shader {
	
	public static final UniformVec3 COLOUR = new UniformVec3("colour");
	public static final UniformMat4 VIEW_MATRIX = new UniformMat4("viewMatrix");
	public static final UniformMat4 PROJECTION_MATRIX = new UniformMat4("projectionMatrix");
	public static final UniformMat4 TRANSFORMATION_MATRIX = new UniformMat4("transformationMatrix");

	public TestShader() {
		super("", "position");
		super.storeAllUniformLocations(COLOUR, VIEW_MATRIX, PROJECTION_MATRIX, TRANSFORMATION_MATRIX);
	}

}
