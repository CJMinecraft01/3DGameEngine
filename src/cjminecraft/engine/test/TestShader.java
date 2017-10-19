package cjminecraft.engine.test;

import cjminecraft.engine.shaders.Shader;
import cjminecraft.engine.shaders.uniforms.UniformVec3;

public class TestShader extends Shader {
	
	public static final UniformVec3 COLOUR = new UniformVec3("colour");

	public TestShader() {
		super("", "position");
		super.storeAllUniformLocations(COLOUR);
	}

}
