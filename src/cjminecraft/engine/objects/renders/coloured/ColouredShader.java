package cjminecraft.engine.objects.renders.coloured;

import cjminecraft.engine.shaders.Shader;
import cjminecraft.engine.shaders.uniforms.UniformMat4;
import cjminecraft.engine.shaders.uniforms.UniformVec4;

public class ColouredShader extends Shader {

	public static final UniformVec4 COLOUR = new UniformVec4("colour");
	public static final UniformMat4 TRANSFORMATION_MATRIX = new UniformMat4("transformationMatrix");
	public static final UniformMat4 VIEW_MATRIX = new UniformMat4("viewMatrix");
	public static final UniformMat4 PROJECTION_MATRIX = new UniformMat4("projectionMatrix");
	
	public ColouredShader() {
		super("", "position");
		super.storeAllUniformLocations(COLOUR, VIEW_MATRIX, PROJECTION_MATRIX, TRANSFORMATION_MATRIX);
	}

}
