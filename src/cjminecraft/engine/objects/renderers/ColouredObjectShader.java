package cjminecraft.engine.objects.renderers;

import cjminecraft.engine.shaders.Shader;
import cjminecraft.engine.shaders.uniforms.UniformVec4;

class ColouredObjectShader extends Shader {

	public static final UniformVec4 COLOUR = new UniformVec4("colour");
	
	public ColouredObjectShader() {
		super("colouredObject", "position");
		super.storeAllUniformLocations(COLOUR);
	}

}
